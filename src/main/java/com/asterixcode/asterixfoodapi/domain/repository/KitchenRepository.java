package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KitchenRepository {

    List<Kitchen> listAll();
    List<Kitchen> getBy(String name);
    Kitchen getBy(Long id);
    Kitchen add(Kitchen kitchen);
    void remove(Long id);
}
