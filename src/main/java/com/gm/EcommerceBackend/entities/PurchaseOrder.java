package com.gm.EcommerceBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "paymentMethodId", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private boolean isPaid = false;

    @ManyToOne
    @JoinColumn(name = "orderStatusId")
    private OrderStatus orderStatus;

    private Double totalPrice;

    @Column(nullable = false)
    private Date orderDate;

    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "deliveryBranchId", nullable = false)
    private DeliveryBranch deliveryBranch;
}
