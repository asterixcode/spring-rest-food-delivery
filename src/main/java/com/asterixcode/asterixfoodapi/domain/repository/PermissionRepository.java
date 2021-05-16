package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.Permission;
import java.util.List;

public interface PermissionRepository {

    List<Permission> listAll();
    Permission getBy(Long id);
    Permission add(Permission permission);
    void remove(Permission permission);
}
