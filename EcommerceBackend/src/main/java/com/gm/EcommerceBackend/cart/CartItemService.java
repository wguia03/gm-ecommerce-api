package com.gm.EcommerceBackend.cart;

import com.gm.EcommerceBackend.exception.ResourceNotFoundException;
import com.gm.EcommerceBackend.product.Product;
import com.gm.EcommerceBackend.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public void saveCartItem(CartItemDTO cartItemDTO) throws ResourceNotFoundException {

        Optional<Cart> cart = cartRepository.findById(cartItemDTO.cart_id());

        if(cart.isEmpty()){
            throw new ResourceNotFoundException("Cart is not available");
        }

        Optional<Product> product = productRepository.findById(cartItemDTO.product_id());

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product is not available");
        }

        CartItem cartItem = CartItem.builder()
                .quantity(cartItemDTO.quantity())
                .item_price(product.get().getPrice() * cartItemDTO.quantity())
                .cart(cart.get())
                .product(product.get()).build();

        cartItemRepository.save(cartItem);

        cart.get().setTotal_price(cart.get().getTotal_price() + cartItem.getItem_price());
        cartRepository.save(cart.get());
    }

    public void deleteCartItem(int cartItemId) throws ResourceNotFoundException {

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if(cartItem.isEmpty()){
            throw new ResourceNotFoundException("Cart item is not available");
        }

        Optional<Cart> cart = cartRepository.findById(cartItem.get().getCart().getId());

        if(cart.isEmpty()){
            throw new ResourceNotFoundException("Cart is not available");
        }

        cart.get().setTotal_price(cart.get().getTotal_price() - cartItem.get().getItem_price());
        cartRepository.save(cart.get());

        cartItemRepository.deleteById(cartItemId);
    }
}
