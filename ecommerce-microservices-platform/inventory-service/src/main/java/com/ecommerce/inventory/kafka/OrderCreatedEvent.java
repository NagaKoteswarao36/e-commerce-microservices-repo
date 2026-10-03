package com.ecommerce.inventory.kafka;

public record OrderCreatedEvent(Long orderId, Long productId, int quantity, double amount) {
}
