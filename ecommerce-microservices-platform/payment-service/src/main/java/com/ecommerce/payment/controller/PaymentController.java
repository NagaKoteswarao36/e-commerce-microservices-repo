package com.ecommerce.payment.controller;

import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentRepository r;

    public PaymentController(PaymentRepository r) {
        this.r = r;
    }

    @GetMapping
    public List<PaymentResponse> all() {
        return r.findAll().stream().map(p -> new PaymentResponse(p.getId(), p.getOrderId(), p.getAmount(), p.getStatus())).toList();
    }

    @GetMapping("/health")
    public String health() {
        return "payment-service UP";
    }
}
