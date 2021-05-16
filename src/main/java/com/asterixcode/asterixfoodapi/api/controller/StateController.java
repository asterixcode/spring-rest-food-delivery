package com.asterixcode.asterixfoodapi.api.controller;

import com.asterixcode.asterixfoodapi.domain.model.State;
import com.asterixcode.asterixfoodapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<State> listAll(){
        return stateRepository.listAll();
    }
}
