package com.ecommerce.payment.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long orderId;
    double amount;
    String status;
    LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long v) {
        orderId = v;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double v) {
        amount = v;
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
