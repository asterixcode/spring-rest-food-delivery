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
        // 1st- get the kitchen id inside of the restaurant sent
        Long kitchenId = restaurant.getKitchen().getId();

        // 2nd- check if the kitchen exists by the above id
        Kitchen kitchen = kitchenRepository.getBy(kitchenId);

        // 3rd- condition result for the above search/check
        if(kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Kitchen with Id %d does not exist", kitchenId));
            // the EntityNotFoundException will be thrown in case kitchen does not exists,
            // and Exception has to be handled in the controller with a try/catch
        }
        // set the kitchen found to the restaurant sent
        restaurant.setKitchen(kitchen);

        // add the restaurant sent to restaurant table
        return restaurantRepository.add(restaurant);
    }
}
