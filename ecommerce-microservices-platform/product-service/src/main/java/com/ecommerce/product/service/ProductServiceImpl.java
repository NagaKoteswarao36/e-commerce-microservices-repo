package com.ecommerce.product.service;

import com.ecommerce.product.dto.*;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository r) {
        repo = r;
    }

    public ProductResponse create(ProductRequest r) {
        Product p = new Product();
        p.setName(r.name());
        p.setDescription(r.description());
        p.setPrice(r.price());
        p.setStock(r.stock());
        return map(repo.save(p));
    }

    //@Cacheable(value = "products", key = "#p0")
    public ProductResponse get(Long id) {
        return repo.findById(id).map(this::map).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    public List<ProductResponse> all() {
        return repo.findAll().stream().map(this::map).toList();
    }

    private ProductResponse map(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock());
    }
}
