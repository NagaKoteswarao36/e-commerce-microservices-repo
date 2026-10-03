package com.ecommerce.product.service;

import com.ecommerce.product.dto.*;

import java.util.*;

public interface ProductService {
    ProductResponse create(ProductRequest r);

    ProductResponse get(Long id);

    List<ProductResponse> all();
}
