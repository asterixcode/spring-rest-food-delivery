package com.asterixcode.asterixfoodapi.api.controller;

import com.asterixcode.asterixfoodapi.domain.exception.EntityNotFoundException;
import com.asterixcode.asterixfoodapi.domain.model.Restaurant;
import com.asterixcode.asterixfoodapi.domain.repository.RestaurantRepository;
import com.asterixcode.asterixfoodapi.domain.service.RestaurantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
