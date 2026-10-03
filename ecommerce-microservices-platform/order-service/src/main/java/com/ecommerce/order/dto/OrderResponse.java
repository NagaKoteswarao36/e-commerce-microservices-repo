package com.ecommerce.order.dto;

import java.time.LocalDateTime;

public record OrderResponse(Long id,
                            Long userId,
                            Long productId,
                            int quantity,
                            double totalAmount,
                            String status,
                            LocalDateTime createdAt) {
}
