package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.PurchaseOrder;
import com.gm.EcommerceBackend.exceptions.NotImplementedException;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.PurchaseOrderDTO;
import com.gm.EcommerceBackend.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody @Valid PurchaseOrderDTO order) throws NotImplementedException, ResourceNotFoundException {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/orders/status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(orderService.getOrderStatus(id));
    }
}
