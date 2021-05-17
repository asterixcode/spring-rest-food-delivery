package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.State;
import com.asterixcode.asterixfoodapi.domain.repository.StateRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StateRepo implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> listAll() {
        return manager.createQuery("from state", State.class).getResultList();
    }

    @Override
    public State getBy(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State add(State state) {
        return manager.merge(state);
    }

    @Override
    public void remove(Long id) {

    }
}
