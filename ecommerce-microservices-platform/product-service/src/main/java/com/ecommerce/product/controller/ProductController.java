package com.ecommerce.product.controller;

import com.ecommerce.product.dto.*;
import com.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService s;

    public ProductController(ProductService s) {
        this.s = s;
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest r) {
        return s.create(r);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable("id") Long id) {
        return s.get(id);
    }

    @GetMapping
    public List<ProductResponse> all() {
        return s.all();
    }

    @GetMapping("/health")
    public String health() {
        return "product-service UP";
    }
}
