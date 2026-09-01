package com.enterprise.todo.gateway.service;

import com.enterprise.todo.gateway.dto.LoginRequest;
import com.enterprise.todo.gateway.dto.UserResponse;

public interface GatewayService {
    String getUserServiceHealth();

    String getTodoServiceHealth();

    UserResponse authenticate(LoginRequest request);
}
