package com.ecommerce.order.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    Long productId;
    int quantity;
    double totalAmount;
    String status;
    LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long v) {
        userId = v;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double v) {
        totalAmount = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String v) {
        status = v;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
