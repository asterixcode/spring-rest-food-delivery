package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> listAll();
    City add(City city);
    void remove(City city);
}
