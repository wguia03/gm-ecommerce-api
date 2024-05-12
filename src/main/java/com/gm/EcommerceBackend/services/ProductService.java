package com.gm.EcommerceBackend.services;

import com.gm.EcommerceBackend.entities.Product;
import com.gm.EcommerceBackend.entities.ProductCategory;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.ProductDTO;
import com.gm.EcommerceBackend.repositories.ProductCategoryRepository;
import com.gm.EcommerceBackend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public Product findProduct(int id) throws ResourceNotFoundException {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not available"));
    }

    public List<Product> findProductsByName(String name) {
        return productRepository.findAllByNameStartsWithIgnoreCase(name);
    }

    public List<Product> findProductsByCategoryName(String catName) {
        return productRepository.findByCategoryName(catName);
    }

    public Product saveProduct(ProductDTO productDTO) throws ResourceNotFoundException {

        ProductCategory productCategory = productCategoryRepository.findById(productDTO.category_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product category is not available"));

        Product product = Product.builder()
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .stock_quantity(productDTO.stock_quantity())
                .image_url(productDTO.image_url())
                .productCategory(productCategory)
                .build();

        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductDTO productDTO) throws ResourceNotFoundException {

        Product existingProduct = this.findProduct(id);

        ProductCategory productCategory = productCategoryRepository.findById(productDTO.category_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product category is not available"));

        existingProduct.setName(productDTO.name());
        existingProduct.setDescription(productDTO.description());
        existingProduct.setPrice(productDTO.price());
        existingProduct.setStock_quantity(productDTO.stock_quantity());
        existingProduct.setImage_url(productDTO.image_url());
        existingProduct.setProductCategory(productCategory);

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id) throws ResourceNotFoundException {

        Product product = this.findProduct(id);
        productRepository.delete(product);
    }

    public Product modifyProductStock(int id, int quantity) throws ResourceNotFoundException {

        Product product = this.findProduct(id);
        int updatedStock = product.getStock_quantity() + quantity;

        if (updatedStock < 0){
            throw new IllegalArgumentException("Stock quantity cannot be a negative value");
        }

        product.setStock_quantity(updatedStock);
        return productRepository.save(product);
    }

    public boolean checkProductStock(int id, int quantity) throws ResourceNotFoundException {
        Product product = this.findProduct(id);
        int updatedStock = product.getStock_quantity() - quantity;
        return updatedStock >= 0;
    }
}
