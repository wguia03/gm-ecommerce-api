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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public Product findProduct(Integer id) throws ResourceNotFoundException {

        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }

        return product.get();
    }

    public List<Product> findProductsByName(String name){
        return productRepository.findAllByNameStartsWithIgnoreCase(name);
    }

    public List<Product> findProductsByCategoryName(String catName){
        return productRepository.findByCategoryName(catName);
    }

    public void saveProduct(ProductDTO productDTO) throws ResourceNotFoundException {

        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productDTO.category_id());

        if(productCategory.isEmpty()){
            throw new ResourceNotFoundException("Category not found");
        }

        Product product = Product.builder()
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .stock_quantity(productDTO.stock_quantity())
                .image_url(productDTO.image_url())
                .productCategory(productCategory.get())
                .build();
        productRepository.save(product);
    }

    public void updateProduct(Integer id, ProductDTO productDTO) throws ResourceNotFoundException {

        Optional<Product> existingProduct = productRepository.findById(id);

        if(existingProduct.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }

        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productDTO.category_id());

        if(productCategory.isEmpty()){
            throw new ResourceNotFoundException("Category not found");
        }

        existingProduct.get().setName(productDTO.name());
        existingProduct.get().setDescription(productDTO.description());
        existingProduct.get().setPrice(productDTO.price());
        existingProduct.get().setStock_quantity(productDTO.stock_quantity());
        existingProduct.get().setImage_url(productDTO.image_url());
        existingProduct.get().setProductCategory(productCategory.get());

        productRepository.save(existingProduct.get());
    }

    public void deleteProduct(Integer id) throws ResourceNotFoundException{

        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }

        productRepository.delete(product.get());
    }
}
