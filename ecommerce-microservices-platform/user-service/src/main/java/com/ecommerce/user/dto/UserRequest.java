package com.ecommerce.user.dto;

import jakarta.validation.constraints.*;

public record UserRequest(@NotBlank String name,
                          @Email @NotBlank String email,
                          String phone) {
}
