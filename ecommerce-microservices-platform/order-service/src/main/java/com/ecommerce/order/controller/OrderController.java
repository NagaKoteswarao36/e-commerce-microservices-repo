package com.ecommerce.order.controller;

import com.ecommerce.order.dto.*;
import com.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService s;

    public OrderController(OrderService s) {
        this.s = s;
    }

    @PostMapping
    public OrderResponse create(@Valid @RequestBody OrderRequest r) {
        return s.create(r);
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable("id") Long id) {
        return s.get(id);
    }

    @GetMapping
    public List<OrderResponse> all() {
        return s.all();
    }

    @GetMapping("/health")
    public String health() {
        return "order-service UP";
    }
}
