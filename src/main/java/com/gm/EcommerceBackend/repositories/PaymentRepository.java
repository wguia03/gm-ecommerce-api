package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
