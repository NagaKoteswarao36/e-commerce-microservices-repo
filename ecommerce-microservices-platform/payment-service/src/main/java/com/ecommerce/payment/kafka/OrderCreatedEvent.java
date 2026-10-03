package com.ecommerce.payment.kafka;

public record OrderCreatedEvent(Long orderId, Long productId, int quantity, double amount) {
}
