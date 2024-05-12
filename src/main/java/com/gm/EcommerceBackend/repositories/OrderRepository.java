package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
