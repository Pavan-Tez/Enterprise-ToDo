package com.enterprise.todo.repository;

import com.enterprise.todo.model.User;

public interface UserRepository {
    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
