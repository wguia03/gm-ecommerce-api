package com.gm.EcommerceBackend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseOrderDTO(
        @NotNull Long userId,
        @NotBlank String deliveryBranch
) {
}
