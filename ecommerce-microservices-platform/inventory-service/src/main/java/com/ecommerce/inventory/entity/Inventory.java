package com.ecommerce.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    Long productId;
    int quantity;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long v) {
        productId = v;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int v) {
        quantity = v;
    }
}
