package com.ecommerce.order.service;

import com.ecommerce.order.client.*;
import com.ecommerce.order.dto.*;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repo;
    private final UserClient user;
    private final ProductClient product;
    private final KafkaTemplate<String, Object> kafka;

    public OrderServiceImpl(OrderRepository r, UserClient u, ProductClient p, KafkaTemplate<String, Object> k) {
        repo = r;
        user = u;
        product = p;
        kafka = k;
    }

    @CircuitBreaker(name = "productClient", fallbackMethod = "createFallback")
    public OrderResponse create(OrderRequest r) {
        user.getUser(r.userId());
        var p = product.getProduct(r.productId());
        if (p.stock() < r.quantity()) throw new IllegalArgumentException("Insufficient product stock");
        Order o = new Order();
        o.setUserId(r.userId());
        o.setProductId(r.productId());
        o.setQuantity(r.quantity());
        o.setTotalAmount(p.price() * r.quantity());
        o.setStatus("CREATED");
        Order saved = repo.save(o);
        kafka.send("order-created", saved.getId().toString(), new OrderEvent(saved.getId(), saved.getProductId(), saved.getQuantity(), saved.getTotalAmount()));
        return map(saved);
    }

    public OrderResponse get(Long id) {
        return repo.findById(id).map(this::map).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    public OrderResponse createFallback(OrderRequest r, Throwable t) {
        throw new IllegalStateException("Order creation temporarily unavailable", t);
    }

    public List<OrderResponse> all() {
        return repo.findAll().stream().map(this::map).toList();
    }

    private OrderResponse map(Order o) {
        return new OrderResponse(o.getId(), o.getUserId(), o.getProductId(), o.getQuantity(), o.getTotalAmount(), o.getStatus(), o.getCreatedAt());
    }

    public record OrderEvent(Long orderId, Long productId, int quantity, double amount) {
    }
}
