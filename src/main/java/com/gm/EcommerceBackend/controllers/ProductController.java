package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.Product;
import com.gm.EcommerceBackend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/public/products/{name}")
    public ResponseEntity<List<Product>> findAllByName(@PathVariable String name){
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

    @GetMapping("/public/products/byCategory/{catName}")
    public ResponseEntity<List<Product>> findAllByCategoryName(@PathVariable String catName){
        return ResponseEntity.ok(productService.findProductsByCategoryName(catName));
    }
}
