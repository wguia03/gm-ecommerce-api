package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByNameStartsWithIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.productCategory.name = :categoryName")
    List<Product> findByCategoryName(String categoryName);

}
