package com.enterprise.todo.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GatewayProxyService {
    void forward(
            HttpServletRequest request,
            HttpServletResponse response,
            String targetBaseUrl,
            Long authenticatedUserId);
}
