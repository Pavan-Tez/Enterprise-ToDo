package com.enterprise.todo.gateway.controller;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.enterprise.todo.gateway.dto.LoginRequest;
import com.enterprise.todo.gateway.dto.UserResponse;
import com.enterprise.todo.gateway.service.GatewayService;

import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private GatewayService gatewayService;

    @Mock
    private HttpSession session;

    private AuthController authController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authController = new AuthController(gatewayService);
    }

    @Test
    public void login_validCredentials_shouldCreateSessionAndRedirect() {

        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("password123");

        UserResponse user = new UserResponse();
        user.setId(1L);
        user.setUsername("testuser");

        when(gatewayService.authenticate(request))
                .thenReturn(user);

        String result = authController.login(request, session);

        assertEquals("redirect:/todos", result);

        verify(gatewayService).authenticate(request);
        verify(session).setAttribute("userId", 1L);
        verify(session).setAttribute("username", "testuser");
    }

    @Test
    public void login_invalidCredentials_shouldRedirectToLoginWithError() {

        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("wrong");

        when(gatewayService.authenticate(request))
                .thenThrow(mock(HttpClientErrorException.class));

        String result = authController.login(request, session);

        assertEquals(
                "redirect:/users/login?error=Invalid+username+or+password",
                result
        );

        verify(gatewayService).authenticate(request);
        verifyZeroInteractions(session);
    }

    @Test
    public void logout_shouldInvalidateSessionAndRedirect() {

        String result = authController.logout(session, mock(javax.servlet.http.HttpServletResponse.class));

        assertEquals(
                "redirect:/users/login?notification=logged-out",
                result
        );

        verify(session).invalidate();
    }
}