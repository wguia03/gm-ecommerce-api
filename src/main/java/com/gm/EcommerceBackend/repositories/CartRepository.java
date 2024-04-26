package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
