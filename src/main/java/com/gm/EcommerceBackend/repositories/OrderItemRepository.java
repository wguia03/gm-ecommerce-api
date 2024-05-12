package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
