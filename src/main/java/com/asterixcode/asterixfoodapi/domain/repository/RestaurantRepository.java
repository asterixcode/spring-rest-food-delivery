package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> listAll();
    Restaurant getBy(Long id);
    Restaurant add(Restaurant restaurant);
    void remove(Restaurant restaurant);
}
