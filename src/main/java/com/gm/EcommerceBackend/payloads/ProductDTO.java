package com.gm.EcommerceBackend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        @NotBlank String name,
        String description,
        @NotNull Double price,
        @NotNull @Positive Integer stock_quantity,
        String image_url,
        @NotNull Integer category_id) {
}
