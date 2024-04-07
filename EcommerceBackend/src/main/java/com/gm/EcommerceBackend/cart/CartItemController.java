package com.gm.EcommerceBackend.cart;

import com.gm.EcommerceBackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem (@PathVariable int id) throws ResourceNotFoundException{
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok().body("Item deleted succesfully");
    }
}
