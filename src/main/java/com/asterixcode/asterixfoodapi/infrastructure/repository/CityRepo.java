package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.City;
import com.asterixcode.asterixfoodapi.domain.repository.CityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepo implements CityRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<City> listAll() {
        return manager.createQuery("from city", City.class).getResultList();
    }

    @Override
    public City add(City city) {
        return manager.merge(city);
    }

    @Override
    public void remove(City city) {

    }
}
