package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryRepository r;

    public InventoryController(InventoryRepository r) {
        this.r = r;
    }

    @GetMapping
    public List<Inventory> all() {
        return r.findAll();
    }

    @GetMapping("/{productId}")
    public Inventory get(@PathVariable Long productId) {
        return r.findByProductId(productId).orElseThrow(() -> new IllegalArgumentException("Inventory not found"));
    }

    @GetMapping("/health")
    public String health() {
        return "inventory-service UP";
    }
}
