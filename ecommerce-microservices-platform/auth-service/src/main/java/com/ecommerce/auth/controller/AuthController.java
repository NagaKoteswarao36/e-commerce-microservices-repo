package com.ecommerce.auth.controller;

import com.ecommerce.auth.dto.*;
import com.ecommerce.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager manager;
    private final JwtService jwt;

    public AuthController(AuthenticationManager manager, JwtService jwt) {
        this.manager = manager;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        return ResponseEntity.ok(new LoginResponse(jwt.generateToken(req.username()), "Bearer"));
    }

    @GetMapping("/health")
    public String health() {
        return "auth-service UP";
    }
}
