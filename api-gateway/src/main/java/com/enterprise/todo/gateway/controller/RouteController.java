package com.enterprise.todo.gateway.controller;

import com.enterprise.todo.gateway.config.GatewayProperties;
import com.enterprise.todo.gateway.service.GatewayProxyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouteController {

    private GatewayProxyService gatewayProxyService;
    private GatewayProperties gatewayProperties;

    public void setGatewayProxyService(
            GatewayProxyService gatewayProxyService) {
        this.gatewayProxyService = gatewayProxyService;
    }

    public void setGatewayProperties(
            GatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }


    @RequestMapping(value = "/users/**",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
    )
    public void userService(
            HttpServletRequest request,
            HttpServletResponse response) {


        gatewayProxyService.forward(
                request,
                response,
                gatewayProperties.getUserServiceUrl(),
                null,
                null);
    }

    @RequestMapping(value = "/todos/**",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
    )
    public void todoService(
            HttpServletRequest request,
            HttpServletResponse response) throws java.io.IOException {

        HttpSession session = request.getSession(false);
        Object userId = session == null ? null : session.getAttribute("userId");
        Object username = session == null ? null : session.getAttribute("username");

        if (!(userId instanceof Long)) {
            response.sendRedirect(request.getContextPath() + "/users/login");
            return;
        }

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        gatewayProxyService.forward(
                request,
                response,
                gatewayProperties.getTodoServiceUrl(),
                (Long) userId,
                username instanceof String ? (String) username : null);
    }
}
