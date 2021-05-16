package com.asterixcode.asterixfoodapi.api.controller;

import com.asterixcode.asterixfoodapi.api.model.KitchenXmlWrapper;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public Kitchen getBy(@PathVariable("kitchenId") Long id){
        return kitchenRepository.getBy(id);
    }
}
