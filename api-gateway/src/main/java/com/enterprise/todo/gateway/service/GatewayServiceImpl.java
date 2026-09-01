package com.enterprise.todo.gateway.service;

import org.springframework.web.client.RestTemplate;

import com.enterprise.todo.gateway.config.GatewayProperties;
import com.enterprise.todo.gateway.dto.LoginRequest;
import com.enterprise.todo.gateway.dto.UserResponse;

public class GatewayServiceImpl implements GatewayService {
    
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;


    public GatewayServiceImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public String getUserServiceHealth() {
        String url = gatewayProperties.getUserServiceUrl() + "/health";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getTodoServiceHealth() {
        String url = gatewayProperties.getTodoServiceUrl() + "/health";
        return restTemplate.getForObject(url, String.class);
    }

   @Override
    public UserResponse authenticate(LoginRequest request) {

        String url =
                gatewayProperties.getUserServiceUrl()
                + "/users/authenticate";

        return restTemplate.postForObject(
                url,
                request,
                UserResponse.class
        );
    }
}
