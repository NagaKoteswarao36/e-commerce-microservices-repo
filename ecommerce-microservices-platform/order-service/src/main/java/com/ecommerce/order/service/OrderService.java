package com.ecommerce.order.service;

import com.ecommerce.order.dto.*;

import java.util.*;

public interface OrderService {
    OrderResponse create(OrderRequest r);

    OrderResponse get(Long id);

    List<OrderResponse> all();
}
