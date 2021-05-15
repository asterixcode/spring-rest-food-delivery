package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> listAll();
    Kitchen getBy(Long id);
    Kitchen add(Kitchen kitchen);
    void remove(Kitchen kitchen);
}
