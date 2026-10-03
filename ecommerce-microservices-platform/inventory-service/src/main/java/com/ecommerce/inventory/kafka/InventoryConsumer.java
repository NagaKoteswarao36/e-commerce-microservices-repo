package com.ecommerce.inventory.kafka;

import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryConsumer {
    private final InventoryRepository repo;

    public InventoryConsumer(InventoryRepository r) {
        repo = r;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-service")
    public void consume(OrderCreatedEvent e) {
        Inventory i = repo.findByProductId(e.productId()).orElseGet(() -> {
            Inventory n = new Inventory();
            n.setProductId(e.productId());
            n.setQuantity(100);
            return n;
        });
        i.setQuantity(Math.max(0, i.getQuantity() - e.quantity()));
        repo.save(i);
    }
}
