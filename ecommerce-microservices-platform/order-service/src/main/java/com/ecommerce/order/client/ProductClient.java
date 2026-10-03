package com.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    ProductView getProduct(@PathVariable("id") Long id);

    record ProductView(Long id,
                       String name,
                       String description,
                       double price,
                       int stock) {
    }
}
