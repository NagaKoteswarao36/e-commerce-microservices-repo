package com.ecommerce.user.controller;

import com.ecommerce.user.dto.*;
import com.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService s;

    public UserController(UserService s) {
        this.s = s;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest r) {
        return s.create(r);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable("id") Long id) {
        return s.get(id);
    }

    @GetMapping
    public List<UserResponse> all() {
        return s.getAll();
    }

    @GetMapping("/health")
    public String health() {
        return "user-service UP";
    }
}
