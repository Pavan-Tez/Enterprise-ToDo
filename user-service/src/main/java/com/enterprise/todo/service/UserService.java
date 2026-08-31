package com.enterprise.todo.service;

import com.enterprise.todo.dto.request.LoginRequest;
import com.enterprise.todo.dto.request.RegisterUserRequest;
import com.enterprise.todo.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterUserRequest request);
    UserResponse login(LoginRequest request);
}
