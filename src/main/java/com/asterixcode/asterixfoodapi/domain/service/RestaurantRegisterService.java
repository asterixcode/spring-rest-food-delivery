package com.asterixcode.asterixfoodapi.domain.service;

import com.asterixcode.asterixfoodapi.domain.exception.EntityNotFoundException;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.model.Restaurant;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import com.asterixcode.asterixfoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant){
        Long kitchenId = restaurant.getKitchen().getId();

        // 1st - check if the kitchen exists
        Kitchen kitchen = kitchenRepository.getBy(kitchenId);
        if(kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Kitchen with Id %d does not exist", kitchenId));
            // the EntityNotFoundException will be thrown in case kitchen does not exists,
            // and Exception has to be handled in the controller with a try/catch
        }
        restaurant.setKitchen(kitchen);

        return restaurantRepository.add(restaurant);
    }
}
