package com.ecommerce.user.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email = e;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String p) {
        phone = p;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
