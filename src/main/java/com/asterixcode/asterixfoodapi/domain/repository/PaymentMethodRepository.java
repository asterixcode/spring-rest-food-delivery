package com.asterixcode.asterixfoodapi.domain.repository;

import com.asterixcode.asterixfoodapi.domain.model.PaymentMethod;
import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> listAll();
    PaymentMethod getBy(Long id);
    PaymentMethod add(PaymentMethod paymentMethod);
    void remove(PaymentMethod paymentMethod);
}
