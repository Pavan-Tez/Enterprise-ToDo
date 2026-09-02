package com.enterprise.todo.gateway.controller;



import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.enterprise.todo.gateway.config.GatewayProperties;
import com.enterprise.todo.gateway.service.GatewayProxyService;

import static org.mockito.Mockito.*;

public class RouteControllerTest {

    @Mock
    private GatewayProxyService gatewayProxyService;

    @Mock
    private GatewayProperties gatewayProperties;

    @Mock
    private javax.servlet.http.HttpServletRequest request;

    @Mock
    private javax.servlet.http.HttpServletResponse response;

    @Mock
    private HttpSession session;

    private RouteController routeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        routeController = new RouteController();
        routeController.setGatewayProxyService(gatewayProxyService);
        routeController.setGatewayProperties(gatewayProperties);
    }

    @Test
    public void userService_shouldForwardToUserService() {

        when(gatewayProperties.getUserServiceUrl())
                .thenReturn("http://localhost:8081");

        routeController.userService(request, response);

        verify(gatewayProxyService).forward(
                request,
                response,
                "http://localhost:8081",
                null,
                null
        );
    }

    @Test
    public void todoService_authenticatedUser_shouldForwardWithUserDetails()
            throws Exception {

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn(10L);
        when(session.getAttribute("username")).thenReturn("testuser");

        when(gatewayProperties.getTodoServiceUrl())
                .thenReturn("http://localhost:8082");

        routeController.todoService(request, response);

        verify(response).setHeader(
                "Cache-Control",
                "no-store, no-cache, must-revalidate, max-age=0"
        );

        verify(response).setHeader(
                "Pragma",
                "no-cache"
        );

        verify(gatewayProxyService).forward(
                request,
                response,
                "http://localhost:8082",
                10L,
                "testuser"
        );
    }

    @Test
    public void todoService_noSession_shouldRedirectToLogin()
            throws Exception {

        when(request.getSession(false)).thenReturn(null);
        when(request.getContextPath()).thenReturn("");

        routeController.todoService(request, response);

        verify(response).sendRedirect("/users/login");

        verifyZeroInteractions(gatewayProxyService);
    }

    @Test
    public void todoService_invalidUserId_shouldRedirectToLogin()
            throws Exception {

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn("10");

        when(request.getContextPath()).thenReturn("");

        routeController.todoService(request, response);

        verify(response).sendRedirect("/users/login");

        verifyZeroInteractions(gatewayProxyService);
    }
}
