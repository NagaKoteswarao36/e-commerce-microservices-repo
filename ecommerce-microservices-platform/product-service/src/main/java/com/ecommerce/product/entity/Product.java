package com.ecommerce.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    double price;
    int stock;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String v) {
        name = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String v) {
        description = v;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double v) {
        price = v;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int v) {
        stock = v;
    }
}
