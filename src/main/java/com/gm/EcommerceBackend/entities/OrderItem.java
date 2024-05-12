package com.gm.EcommerceBackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double item_price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
