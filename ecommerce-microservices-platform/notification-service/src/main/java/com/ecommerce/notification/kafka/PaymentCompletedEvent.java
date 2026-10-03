package com.ecommerce.notification.kafka;

public record PaymentCompletedEvent(Long orderId, double amount, String status) {
}
