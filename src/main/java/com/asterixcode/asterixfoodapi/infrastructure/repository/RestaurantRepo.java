package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.Restaurant;
import com.asterixcode.asterixfoodapi.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestaurantRepo implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> listAll() {
        return manager.createQuery("from Restaurant", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant getBy(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant add(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    @Override
    public void remove(Restaurant restaurant) {
        restaurant = getBy(restaurant.getId());
        manager.remove(restaurant);
    }
}
