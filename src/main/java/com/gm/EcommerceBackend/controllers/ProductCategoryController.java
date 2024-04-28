package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.ProductCategory;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.ProductCategoryDTO;
import com.gm.EcommerceBackend.payloads.ResponseMessage;
import com.gm.EcommerceBackend.services.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("public/productCategories")
    public ResponseEntity<List<ProductCategory>> findProductCategory() {
        return ResponseEntity.ok(productCategoryService.findAllProductCategories());
    }

    @GetMapping("/public/productCategories/{id}")
    public ResponseEntity<ProductCategory> findProductCategory(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productCategoryService.findProductCategory(id));
    }

    @PostMapping("/admin/productCategories")
    public ResponseEntity<ResponseMessage> saveProductCategory(@RequestBody @Valid ProductCategoryDTO productCategoryDTO) throws ResourceNotFoundException {
        productCategoryService.saveProductCategory(productCategoryDTO);
        return ResponseEntity.ok(new ResponseMessage("Product category saved successfully"));
    }

    @PutMapping("/admin/productCategories/{id}")
    public ResponseEntity<ResponseMessage> updateProductCategory(@PathVariable int id, @RequestBody @Valid ProductCategoryDTO productCategoryDTO) throws ResourceNotFoundException {
        productCategoryService.updateProductCategory(id, productCategoryDTO);
        return ResponseEntity.ok(new ResponseMessage("Product category updated successfully"));
    }

    @DeleteMapping("/admin/productCategories/{id}")
    public ResponseEntity<ResponseMessage> deleteProductCategory(@PathVariable int id) throws ResourceNotFoundException {
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.ok(new ResponseMessage("Product category deleted successfully"));
    }
}
