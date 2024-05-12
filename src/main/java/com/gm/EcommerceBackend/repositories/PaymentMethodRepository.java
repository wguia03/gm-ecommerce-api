package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.PaymentMethod;
import com.gm.EcommerceBackend.entities.PaymentMethodEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    PaymentMethod findByName(PaymentMethodEnum name);
}
