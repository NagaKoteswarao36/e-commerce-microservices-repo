package com.ecommerce.payment.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {
    private final KafkaTemplate<String, Object> k;

    public PaymentProducer(KafkaTemplate<String, Object> k) {
        this.k = k;
    }

    public void publish(PaymentEvent e) {
        k.send("payment-completed", e.orderId().toString(), e);
    }

    public record PaymentEvent(Long orderId, double amount, String status) {
    }
}
