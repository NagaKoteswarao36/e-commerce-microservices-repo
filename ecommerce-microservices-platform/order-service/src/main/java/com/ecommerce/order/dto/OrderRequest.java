package com.ecommerce.order.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
//import org.antlr.v4.runtime.misc.NotNull;


public record OrderRequest(@NotNull Long userId,
                           @NotNull Long productId,
                           @Positive int quantity) {
}
