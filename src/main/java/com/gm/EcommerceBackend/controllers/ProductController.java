package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.Product;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.ProductDTO;
import com.gm.EcommerceBackend.payloads.ResponseMessage;
import com.gm.EcommerceBackend.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/public/products/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.findProduct(id));
    }

    @GetMapping("/public/products/byName/{name}")
    public ResponseEntity<List<Product>> findAllByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

    @GetMapping("/public/products/byCategory/{catName}")
    public ResponseEntity<List<Product>> findAllByCategoryName(@PathVariable String catName) {
        return ResponseEntity.ok(productService.findProductsByCategoryName(catName));
    }

    @PostMapping("/admin/products")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDTO productDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody @Valid ProductDTO productDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @PatchMapping("/admin/products/modifyStock/{id}/{quantity}")
    public ResponseEntity<Product> modifyProductStock(@PathVariable int id, @PathVariable @NotNull int quantity) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.modifyProductStock(id, quantity));
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable int id) throws ResourceNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseMessage("Product deleted successfully"));
    }
}
