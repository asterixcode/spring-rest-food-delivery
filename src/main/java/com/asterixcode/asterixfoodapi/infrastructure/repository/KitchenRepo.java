package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class KitchenRepo implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> listAll(){
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Override
    public Kitchen getBy(Long id){
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public Kitchen add(Kitchen kitchen){
        return manager.merge(kitchen);
    }

    @Transactional
    @Override
    public void remove(Kitchen kitchen){
        kitchen = getBy(kitchen.getId());
        manager.remove(kitchen);
    }
}
