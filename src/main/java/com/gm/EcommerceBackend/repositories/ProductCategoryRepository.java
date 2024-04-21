package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
