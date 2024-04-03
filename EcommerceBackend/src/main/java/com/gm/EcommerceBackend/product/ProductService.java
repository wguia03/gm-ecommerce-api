package com.gm.EcommerceBackend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findProductsByName(String name){
        return productRepository.findAllByNameStartsWithIgnoreCase(name);
    }

    public List<Product> findProductsByCategoryName(String catName){
        return productRepository.findByCategoryName(catName);
    }
}
