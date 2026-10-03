package com.ecommerce.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @KafkaListener(topics = "payment-completed", groupId = "notification-service")
    public void consume(PaymentCompletedEvent e) {
        System.out.printf("NOTIFICATION: payment %s for order %s amount %.2f%n", e.status(), e.orderId(), e.amount());
    }
}
