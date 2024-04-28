package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.payloads.CartItemDTO;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.services.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("")
    public ResponseEntity<String> saveCartItem (@RequestBody @Valid CartItemDTO cartItemDTO) throws ResourceNotFoundException {
        cartItemService.saveCartItem(cartItemDTO);
        return ResponseEntity.ok().body("Item saved successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem (@PathVariable int id) throws ResourceNotFoundException{
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok().body("Item deleted successfully");
    }
}
