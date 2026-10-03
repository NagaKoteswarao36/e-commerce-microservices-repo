package com.ecommerce.payment.dto;

public record PaymentResponse(Long id, Long orderId, double amount, String status) {
}
