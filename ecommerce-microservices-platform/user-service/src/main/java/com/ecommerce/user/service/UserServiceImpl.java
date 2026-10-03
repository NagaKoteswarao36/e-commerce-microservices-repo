package com.ecommerce.user.service;

import com.ecommerce.user.dto.*;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository r) {
        repo = r;
    }

    public UserResponse create(UserRequest r) {
        if (repo.existsByEmail(r.email())) throw new IllegalArgumentException("Email already exists");
        User u = new User();
        u.setName(r.name());
        u.setEmail(r.email());
        u.setPhone(r.phone());
        return map(repo.save(u));
    }

    public UserResponse get(Long id) {
        return repo.findById(id).map(this::map).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserResponse> getAll() {
        return repo.findAll().stream().map(this::map).toList();
    }

    private UserResponse map(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getPhone(), u.getCreatedAt());
    }
}
