package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.Permission;
import com.asterixcode.asterixfoodapi.domain.repository.PermissionRepository;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissionRepo implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> listAll() {
        return manager.createQuery("from Permission", Permission.class).getResultList();
    }

    @Override
    public Permission getBy(Long id) {
        return manager.find(Permission.class, id);
    }

    @Override
    public Permission add(Permission permission) {
        return manager.merge(permission);
    }

    @Override
    public void remove(Permission permission) {
        permission = getBy(permission.getId());
        manager.remove(permission);
    }
}
