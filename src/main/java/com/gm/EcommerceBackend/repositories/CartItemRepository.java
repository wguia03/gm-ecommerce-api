package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.CartItem;
import com.gm.EcommerceBackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
    List<CartItem> findByCartId(int cartId);
}
