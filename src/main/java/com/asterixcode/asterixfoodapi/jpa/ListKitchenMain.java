package com.asterixcode.asterixfoodapi.jpa;

import com.asterixcode.asterixfoodapi.AsterixfoodApiApplication;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ListKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AsterixfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchens = applicationContext.getBean(KitchenRepository.class);

        List<Kitchen> kitchensAll = kitchens.listAll();

        for (Kitchen kitchen : kitchensAll){
            System.out.println(kitchen.getName());
        }
    }
}
