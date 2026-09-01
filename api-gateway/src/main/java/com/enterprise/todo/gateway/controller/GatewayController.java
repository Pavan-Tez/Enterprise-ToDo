package com.enterprise.todo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.todo.gateway.service.GatewayService;

@RestController
public class GatewayController {

        private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping("/gateway/user-health")
    public String userServiceHealth() {
        return gatewayService.getUserServiceHealth();
    }

    @GetMapping("/gateway/todo-health")
    public String todoServiceHealth() {
        return gatewayService.getTodoServiceHealth();
    }
    
    @GetMapping("/health")
    public String getGatewayStatus() {
        return "API Gateway is running.";
    }
}
