package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.CartItem;
import com.gm.EcommerceBackend.payloads.CartItemDTO;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.services.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable int cartId) throws ResourceNotFoundException {
        return ResponseEntity.ok(cartItemService.getAllCartItemsByCartId(cartId));
    }

    @PostMapping("")
    public ResponseEntity<CartItem> saveCartItem (@RequestBody @Valid CartItemDTO cartItemDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(cartItemService.saveCartItem(cartItemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem (@PathVariable int id) throws ResourceNotFoundException{
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok().body("Item deleted successfully");
    }
}
