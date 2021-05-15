package com.asterixcode.asterixfoodapi.jpa;

import com.asterixcode.asterixfoodapi.AsterixfoodApiApplication;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AddKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AsterixfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brazilian");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("Thai");

        kitchenRepository.add(kitchen1);
        kitchenRepository.add(kitchen2);

    }
}
