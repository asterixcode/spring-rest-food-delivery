package com.asterixcode.asterixfoodapi.infrastructure.repository;

import com.asterixcode.asterixfoodapi.domain.model.PaymentMethod;
import com.asterixcode.asterixfoodapi.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PaymentMethodRepo implements PaymentMethodRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentMethod> listAll() {
        return manager.createQuery("from PaymentMethod", PaymentMethod.class).getResultList();
    }

    @Override
    public PaymentMethod getBy(Long id) {
        return manager.find(PaymentMethod.class, id);
    }

    @Transactional
    @Override
    public PaymentMethod add(PaymentMethod paymentMethod) {
        return manager.merge(paymentMethod);
    }

    @Transactional
    @Override
    public void remove(PaymentMethod paymentMethod) {
        paymentMethod = getBy(paymentMethod.getId());
        manager.remove(paymentMethod);
    }
}
