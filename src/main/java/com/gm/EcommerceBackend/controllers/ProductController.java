package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.Product;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.ResponseMessage;
import com.gm.EcommerceBackend.payloads.ProductDTO;
import com.gm.EcommerceBackend.services.ProductService;
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
    public ResponseEntity<Product> findProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.findProduct(id));
    }

    @GetMapping("/public/products/byName/{name}")
    public ResponseEntity<List<Product>> findAllByName(@PathVariable String name){
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

    @GetMapping("/public/products/byCategory/{catName}")
    public ResponseEntity<List<Product>> findAllByCategoryName(@PathVariable String catName){
        return ResponseEntity.ok(productService.findProductsByCategoryName(catName));
    }

    @PostMapping("/admin/products")
    public ResponseEntity<ResponseMessage> saveProduct(@RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        productService.saveProduct(productDTO);
        return ResponseEntity.ok(new ResponseMessage("Product saved successfully"));
    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ResponseMessage> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(new ResponseMessage("Product updated successfully"));
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseMessage("Product deleted successfully"));
    }
}
