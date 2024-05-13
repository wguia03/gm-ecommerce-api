package com.gm.EcommerceBackend.services;

import com.gm.EcommerceBackend.entities.Payment;
import com.gm.EcommerceBackend.entities.PaymentMethod;
import com.gm.EcommerceBackend.entities.PaymentMethodEnum;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.repositories.PaymentMethodRepository;
import com.gm.EcommerceBackend.repositories.PaymentRepository;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PayPalHttpClient payPalHttpClient;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    private OrderRequest buildOrderRequest(int paymentId) throws ResourceNotFoundException {
        log.info("Building order request for paymentId: " + paymentId);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment id not found"));
        String totalPrice = String.valueOf(payment.getTotalPrice());

        AmountWithBreakdown amount = new AmountWithBreakdown().currencyCode("USD").value(totalPrice);
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amount);
        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);

        log.info("Order request built successfully");
        return orderRequest;
    }

    public String createOrder(int paymentId) throws ResourceNotFoundException {
        log.info("Creating order for paymentId: " + paymentId);
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.requestBody(buildOrderRequest(paymentId));

        try {
            // Call PayPal to set up a transaction
            HttpResponse<Order> response = payPalHttpClient.execute(request);
            log.info("Order ID: " + response.result().id());
            return response.result().id();
        } catch (IOException ioe) {
            log.error("Exception: " + ioe.getMessage());
            return null;
        }
    }

    public Payment captureOrder(String orderId, int paymentId) throws ResourceNotFoundException, IOException {
        log.info("Capturing order for orderId: " + orderId + " and paymentId: " + paymentId);
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);

        HttpResponse<Order> response = payPalHttpClient.execute(request);

        log.info("Order ID: " + response.result().id());
        log.info("Capture ID: " + response.result().purchaseUnits().get(0).payments().captures().get(0).id());

        if (response.result().status().equals("COMPLETED")) {
            Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment id not found"));

            PaymentMethod paymentMethod = paymentMethodRepository.findByName(PaymentMethodEnum.valueOf("PAYPAL"));

            payment.setPaid(true);
            payment.setPaymentDate(new Date());
            payment.setPaymentMethod(paymentMethod);

            log.info("Order captured successfully");
            return paymentRepository.save(payment);
        } else {
            log.error("Order not completed");
            throw new RuntimeException("Order not completed");
        }
    }

    public Payment createPaypalPayment(Integer paymentId) throws ResourceNotFoundException, IOException {
        log.info("Creating PayPal payment for paymentId: " + paymentId);
        String orderId = this.createOrder(paymentId);
        return this.captureOrder(orderId, paymentId);
    }
}