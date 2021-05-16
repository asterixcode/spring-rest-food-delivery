package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> listAll();
    State getBy(Long id);
    State add(State state);
    void remove(State state);
}
