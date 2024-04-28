package com.gm.EcommerceBackend.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemDTO(@NotNull Integer product_id,
                          @NotNull Integer cart_id,
                          @NotNull @Positive Integer quantity) {
}
