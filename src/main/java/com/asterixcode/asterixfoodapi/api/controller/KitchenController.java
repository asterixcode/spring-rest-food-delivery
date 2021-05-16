package com.asterixcode.asterixfoodapi.api.controller;

import com.asterixcode.asterixfoodapi.domain.exception.EntityInUseException;
import com.asterixcode.asterixfoodapi.domain.exception.EntityNotFoundException;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import com.asterixcode.asterixfoodapi.domain.service.KitchenRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/kitchens")
public class KitchenController {

    // repository only for consulting
    @Autowired
    private KitchenRepository kitchenRepository;

    // any modification has to be through the Service
    @Autowired
    private KitchenRegisterService kitchenRegisterService;

    @GetMapping
    public List<Kitchen> listAll() {
        return kitchenRepository.listAll();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> getBy(@PathVariable("kitchenId") Long id){
        // consult the repo to check if exists
        Kitchen kitchen = kitchenRepository.getBy(id);

        // if found: 200 OK, if not found: 404 Not Found
        if (kitchen != null){
            return ResponseEntity.ok(kitchen);
        }
        return ResponseEntity.notFound().build(); //returning not found without any body!

//        return ResponseEntity.status(HttpStatus.OK).body(kitchen);

//        return ResponseEntity.ok(kitchen); //same as above, but as a "shortcut"

//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://api.asterixfood.local:8080/kitchens");
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen){
        return kitchenRegisterService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){
        Kitchen kitchenCurrent = kitchenRepository.getBy(kitchenId); // repository only for consulting

        if (kitchenCurrent != null) {
            //kitchenCurrent.setName(kitchen.getName()); ..., instead, using BeanUtils below
            BeanUtils.copyProperties(kitchen, kitchenCurrent, "id"); //update all fields, ignoring id
            kitchenCurrent = kitchenRegisterService.save(kitchenCurrent); // any modification has to be through the Service
            return ResponseEntity.ok(kitchenCurrent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId){
        try {
            kitchenRegisterService.delete(kitchenId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); //404 Not Found

        } catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); //409 Conflict
        }

//        } catch (DataIntegrityViolationException e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//            // return 409 Conflict when the kitchenToDelete is FK on Restaurant table
//            // (when one Restaurant is linked to a kitchenToDelete/the kitchen belongs (is FK) to a restaurant)
//            // this is an Infrastructure Exception
//        }
    }
}
