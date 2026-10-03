package com.ecommerce.payment.kafka;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {
    private final PaymentRepository repo;
    private final PaymentProducer producer;

    public PaymentConsumer(PaymentRepository r, PaymentProducer p) {
        repo = r;
        producer = p;
    }

    @KafkaListener(topics = "order-created", groupId = "payment-service")
    public void consume(OrderCreatedEvent e) {
        Payment p = new Payment();
        p.setOrderId(e.orderId());
        p.setAmount(e.amount());
        p.setStatus("COMPLETED");
        repo.save(p);
        producer.publish(new PaymentProducer.PaymentEvent(e.orderId(), e.amount(), "COMPLETED"));
    }
}
