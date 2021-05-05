package com.asterixcode.asterixfoodapi.controller;

import com.asterixcode.asterixfoodapi.model.Client;
import com.asterixcode.asterixfoodapi.service.ActivationClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {

    private ActivationClientService activationClientService;

    public MyFirstController(ActivationClientService activationClientService) {
        this.activationClientService = activationClientService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Client asterix = new Client("Asterix", "asterix@code.com", "99998888");

        activationClientService.activate(asterix);

        return "Hello!";
    }
}
