package com.ecommerce.product.dto;

import jakarta.validation.constraints.*;

public record ProductRequest(@NotBlank String name, String description, @Positive double price,
                             @PositiveOrZero int stock) {
}
