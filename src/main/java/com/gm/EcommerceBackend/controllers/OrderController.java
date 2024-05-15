package com.gm.EcommerceBackend.controllers;

import com.gm.EcommerceBackend.entities.Payment;
import com.gm.EcommerceBackend.entities.PurchaseOrder;
import com.gm.EcommerceBackend.exceptions.NotImplementedException;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.payloads.PurchaseOrderDTO;
import com.gm.EcommerceBackend.payloads.ResponseMessage;
import com.gm.EcommerceBackend.services.OrderService;
import com.gm.EcommerceBackend.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @GetMapping("/admin/orders")
    public ResponseEntity<List<PurchaseOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/user/orders")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody @Valid PurchaseOrderDTO order) throws NotImplementedException, ResourceNotFoundException {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/auth/orders/status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(orderService.getOrderStatus(id));
    }

    @PostMapping("/user/orders/payment/{paymentId}")
    public ResponseEntity<Payment> payOrder(@PathVariable Integer paymentId, @RequestParam String paymentMethod) throws ResourceNotFoundException {
        return ResponseEntity.ok(paymentService.payOrder(paymentId, paymentMethod));
    }

    @PutMapping("/admin/orders/{id}")
    public ResponseEntity<ResponseMessage> updateOrderStatus(@PathVariable Integer id, @RequestParam String status) throws ResourceNotFoundException {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(new ResponseMessage("Order status updated successfully"));
    }
}
