package com.enterprise.todo.gateway.service;

import org.springframework.web.client.RestTemplate;

import com.enterprise.todo.gateway.config.GatewayProperties;

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
}
