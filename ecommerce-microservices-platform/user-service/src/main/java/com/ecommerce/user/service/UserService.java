package com.ecommerce.user.service;

import com.ecommerce.user.dto.*;

import java.util.*;

public interface UserService {
    UserResponse create(UserRequest r);

    UserResponse get(Long id);

    List<UserResponse> getAll();
}
