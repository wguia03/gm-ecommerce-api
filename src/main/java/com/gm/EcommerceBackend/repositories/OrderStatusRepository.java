package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.OrderStatus;
import com.gm.EcommerceBackend.entities.OrderStatusEnum;
import com.gm.EcommerceBackend.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    OrderStatus findByStatus(OrderStatusEnum status);
}
