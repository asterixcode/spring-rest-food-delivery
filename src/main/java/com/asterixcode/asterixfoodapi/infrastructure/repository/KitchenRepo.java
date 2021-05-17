package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class KitchenRepo implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> listAll(){
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Override
    public List<Kitchen> getBy(String name) {
        return manager
                .createQuery("from Kitchen where name like :name", Kitchen.class) //JPQL; returning class for the getResultList()
                .setParameter("name", "%" + name + "%") //binding the parameter name (%name% for the like)
                .getResultList();
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
    public void remove(Long id){
        Kitchen kitchen = getBy(id);

        if (kitchen == null){
            throw new EmptyResultDataAccessException(1); // 1 => expected size; 1 = at least one kitchen (as getting by id)
            // it's an exception from Spring Framework, when trying to remove something and this doesn't exist.
        }
        manager.remove(kitchen);
    }
}
