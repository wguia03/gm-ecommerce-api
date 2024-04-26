package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
