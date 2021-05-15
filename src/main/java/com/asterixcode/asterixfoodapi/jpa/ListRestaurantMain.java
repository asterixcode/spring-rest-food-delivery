package com.asterixcode.asterixfoodapi.jpa;

import com.asterixcode.asterixfoodapi.AsterixfoodApiApplication;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.model.Restaurant;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import com.asterixcode.asterixfoodapi.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ListRestaurantMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AsterixfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurants = applicationContext.getBean(RestaurantRepository.class);

        List<Restaurant> restaurantsAll = restaurants.listAll();

        for (Restaurant restaurant : restaurantsAll){
            System.out.printf("%s - %f - %s\n",
                    restaurant.getName(),
                    restaurant.getDeliveryFee(),
                    restaurant.getKitchen().getName());
        }
    }
}
