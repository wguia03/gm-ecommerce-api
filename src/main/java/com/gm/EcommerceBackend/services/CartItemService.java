package com.gm.EcommerceBackend.services;

import com.gm.EcommerceBackend.entities.Cart;
import com.gm.EcommerceBackend.entities.CartItem;
import com.gm.EcommerceBackend.entities.Product;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.CartItemDTO;
import com.gm.EcommerceBackend.repositories.CartItemRepository;
import com.gm.EcommerceBackend.repositories.CartRepository;
import com.gm.EcommerceBackend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public void saveCartItem(CartItemDTO cartItemDTO) throws ResourceNotFoundException {

        Cart cart = cartRepository.findById(cartItemDTO.cart_id())
                .orElseThrow(() -> new ResourceNotFoundException("Cart is not available"));

        Product product = productRepository.findById(cartItemDTO.product_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product is not available"));

        CartItem cartItem = CartItem.builder()
                .quantity(cartItemDTO.quantity())
                .item_price(product.getPrice() * cartItemDTO.quantity())
                .cart(cart)
                .product(product).build();

        cart.setTotal_price(cart.getTotal_price() + cartItem.getItem_price());
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    public void deleteCartItem(int cartItemId) throws ResourceNotFoundException {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item is not available"));

        Cart cart = cartRepository.findById(cartItem.getCart().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart is not available"));

        cart.setTotal_price(cart.getTotal_price() - cartItem.getItem_price());
        cartRepository.save(cart);
        cartItemRepository.deleteById(cartItemId);
    }
}
