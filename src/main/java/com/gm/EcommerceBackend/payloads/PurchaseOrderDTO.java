package com.gm.EcommerceBackend.payloads;

import com.gm.EcommerceBackend.entities.DeliveryBranchEnum;
import com.gm.EcommerceBackend.entities.PaymentMethodEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseOrderDTO(
        @NotNull Long userId,
        @NotBlank String paymentMethod,
        @NotBlank String deliveryBranch
) {
}
