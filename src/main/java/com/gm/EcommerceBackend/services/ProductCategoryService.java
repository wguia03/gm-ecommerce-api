package com.gm.EcommerceBackend.services;

import com.gm.EcommerceBackend.entities.ProductCategory;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.ProductCategoryDTO;
import com.gm.EcommerceBackend.repositories.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory findById(Integer id) throws ResourceNotFoundException {
        return productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not found"));
    }

    public List<ProductCategory> findAll(){
        return productCategoryRepository.findAll();
    }

    public void saveProductCategory(ProductCategoryDTO productCategoryDTO) throws ResourceNotFoundException {

        ProductCategory parentCategory = null;

        if (productCategoryDTO.parent_id() != null) {
            parentCategory = this.findById(productCategoryDTO.parent_id());
        }

        ProductCategory productCategory = ProductCategory.builder()
                .name(productCategoryDTO.name())
                .parentCategory(parentCategory)
                .build();

        productCategoryRepository.save(productCategory);
    }

    public void updateProductCategory(int id, ProductCategoryDTO productCategoryDTO) throws ResourceNotFoundException {

        ProductCategory existingProduct = findById(id);
        ProductCategory parentCategory = null;

        if (productCategoryDTO.parent_id() != null) {
            parentCategory = this.findById(productCategoryDTO.parent_id());
        }

        existingProduct.setName(productCategoryDTO.name());
        existingProduct.setParentCategory(parentCategory);

        productCategoryRepository.save(existingProduct);
    }

    public void deleteProductCategory(int id) throws ResourceNotFoundException {
        productCategoryRepository.deleteById(id);
    }
}
