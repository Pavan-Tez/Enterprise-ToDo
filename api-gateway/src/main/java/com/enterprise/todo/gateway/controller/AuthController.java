package com.enterprise.todo.gateway.controller;

import com.enterprise.todo.gateway.dto.LoginRequest;
import com.enterprise.todo.gateway.dto.UserResponse;
import com.enterprise.todo.gateway.service.GatewayService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class AuthController {

    private final GatewayService gatewayService;

    @Autowired
    public AuthController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping("/users/login")
    public String login(LoginRequest request, HttpSession session) {

        UserResponse user;
        try {
            user = gatewayService.authenticate(request);
        } catch (HttpClientErrorException ex) {
            return "redirect:/users/login?error=Invalid+username+or+password";
        }

        session.setAttribute("userId", user.getId());

        return "redirect:/todos";
    }

    @PostMapping("/users/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login?notification=logged-out";
    }
    
}
