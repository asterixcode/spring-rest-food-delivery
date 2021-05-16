package com.asterixcode.asterixfoodapi.api.controller;

import com.asterixcode.asterixfoodapi.domain.exception.EntityNotFoundException;
import com.asterixcode.asterixfoodapi.domain.model.Restaurant;
import com.asterixcode.asterixfoodapi.domain.repository.RestaurantRepository;
import com.asterixcode.asterixfoodapi.domain.service.RestaurantRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public List<Restaurant> listAll(){
        return restaurantRepository.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getBy(@PathVariable("id") Long id){
        // 1st - consult the repo to check if exists
        // 2nd - if found: 200 OK + body; if not found: 404 Not Found (without a body)
        Restaurant restaurant = restaurantRepository.getBy(id);
        if (restaurant != null){
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant){
        try {
            // save and assign
            restaurant = restaurantRegisterService.save(restaurant);

            // return
            return ResponseEntity
                    .status(HttpStatus.CREATED) // 201 Created
                    .body(restaurant);  // with a body
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .badRequest() // 400 Bad Request
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId,
                                    @RequestBody Restaurant restaurant){
        try {
            // 1st- check the repository if restaurant exist by id
            Restaurant restaurantCurrent = restaurantRepository.getBy(restaurantId);

            if (restaurantCurrent != null) {
                BeanUtils.copyProperties(restaurant, restaurantCurrent, "id");
                restaurantRegisterService.save(restaurantCurrent);
                return ResponseEntity.ok(restaurantCurrent); // 200 OK
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        }

        catch (EntityNotFoundException e) {
            return ResponseEntity
                    .badRequest() // 400 Bad Request
                    .body(e.getMessage()); // kitchen_id does not exists
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> updatePartial(@PathVariable Long restaurantId,
                                           @RequestBody Map<String, Object> fields){
        // check if id belongs to a restaurant
        Restaurant restaurantCurrent = restaurantRepository.getBy(restaurantId);
        // if not, 404 Not Found
        if (restaurantCurrent == null){
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        merge(fields, restaurantCurrent);

        return update(restaurantId, restaurantCurrent);
    }

    private void merge(Map<String, Object> fieldsOrigin, Restaurant restaurantDestiny) {
        // ** 1st:
        // we need to convert the field to its specific type (String, BigDecimal, other objects...)
        // ObjectMapper from jackson -> responsible for Serializations (Java objects to JSON, JSON to Java obj)
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert the origin fields/data from the Map to a type=Restaurant.class
        // Create an instance of type Restaurant.class using as base the Map with origin data/fields
        Restaurant restaurantOrigin = objectMapper.convertValue(fieldsOrigin, Restaurant.class);

        // ** 2nd:
        // with the values from the JSON converted and matching the fields of a Restaurant.class,
        // now we need to assign/merge/patch only the values that was updated.
        // It's not possible to merge all at once, we need to check and merge only updated values,
        // this way we need the origin fields and
        // get the fields from restaurantOrigin and set into the restaurantDestiny dinamically
        fieldsOrigin.forEach((propertyName, propertyValue) -> {
            // 1st: findField = return an instance of a field
            Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
            field.setAccessible(true);

            // 2nd: getField = get the value of that field finded above
            Object newValue = ReflectionUtils.getField(field, restaurantOrigin);

            System.out.println(propertyName + " = " + propertyValue + " = " + newValue);
            // 3rd: set the newValue of the field (already converted to its correct field type)
            // into the restaurantDestiny
            ReflectionUtils.setField(field, restaurantDestiny, newValue);
        });

    }
}
