package com.gm.EcommerceBackend.services;

import com.gm.EcommerceBackend.entities.Payment;
import com.gm.EcommerceBackend.entities.PaymentMethodEnum;
import com.gm.EcommerceBackend.exceptions.ResourceNotFoundException;
import com.gm.EcommerceBackend.repositories.PaymentMethodRepository;
import com.gm.EcommerceBackend.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public Payment payOrder(Integer paymentId, String paymentMethod) throws ResourceNotFoundException {

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        if(paymentMethod.equals("CASH")){
            payment.setPaymentMethod(paymentMethodRepository.findByName(PaymentMethodEnum.valueOf("CASH")));
        } else if(paymentMethod.equals("ONLINE")){
            payment.setPaymentMethod(paymentMethodRepository.findByName(PaymentMethodEnum.valueOf("ONLINE")));
        } else{
            throw new ResourceNotFoundException("Payment method does not exist");
        }

        payment.setPaid(true);
        payment.setPaymentDate(new Date());
        return paymentRepository.save(payment);
    }
}