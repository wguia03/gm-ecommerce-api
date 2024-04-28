package com.gm.EcommerceBackend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCategoryDTO(
        @NotBlank String name,
        Integer parent_id) {
}
