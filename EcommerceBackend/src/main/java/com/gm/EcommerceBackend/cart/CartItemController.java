package com.gm.EcommerceBackend.cart;

import com.gm.EcommerceBackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("")
    public ResponseEntity<String> saveCartItem (@RequestBody CartItemDTO cartItemDTO) throws ResourceNotFoundException {
        cartItemService.saveCartItem(cartItemDTO);
        return ResponseEntity.ok().body("Item saved successfully");
    }
}
