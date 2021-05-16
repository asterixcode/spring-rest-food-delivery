package com.asterixcode.asterixfoodapi.api.controller;

import com.asterixcode.asterixfoodapi.api.model.KitchenXmlWrapper;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    // return kitchen list as JSON (Spring standard)
    @GetMapping
    public List<Kitchen> listAll() {
        return kitchenRepository.listAll();
    }

    // return kitchen list as XML (as example)
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchenXmlWrapper listAllXml() {
        return new KitchenXmlWrapper(kitchenRepository.listAll());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> getBy(@PathVariable("kitchenId") Long id){
        Kitchen kitchen = kitchenRepository.getBy(id);

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
        return kitchenRepository.add(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){

        Kitchen kitchenCurrent = kitchenRepository.getBy(kitchenId);

        if (kitchenCurrent != null) {
            //kitchenCurrent.setName(kitchen.getName()); ..., instead, using BeanUtils below
            BeanUtils.copyProperties(kitchen, kitchenCurrent, "id"); //update all fields, ignoring id
            kitchenRepository.add(kitchenCurrent);
            return ResponseEntity.ok(kitchenCurrent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId){
        try {
            Kitchen kitchen = kitchenRepository.getBy(kitchenId);

            if (kitchen != null) {
                kitchenRepository.remove(kitchen);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build(); //404 Not Found if kitchenId not found
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
            // return 409 Conflict when the kitchenToDelete is FK on Restaurant table
            // (when one Restaurant is linked to a kitchenToDelete/the kitchen belongs (is FK) to a restaurant)
        }
    }
}
