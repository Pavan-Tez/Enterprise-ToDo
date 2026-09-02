package com.enterprise.todo.gateway.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.enterprise.todo.gateway.config.GatewayProperties;
import com.enterprise.todo.gateway.dto.LoginRequest;
import com.enterprise.todo.gateway.dto.UserResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GatewayServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GatewayProperties gatewayProperties;

    private GatewayServiceImpl gatewayService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        gatewayService = new GatewayServiceImpl(
                restTemplate,
                gatewayProperties
        );
    }

    @Test
    public void getUserServiceHealth_shouldReturnHealthResponse() {

        when(gatewayProperties.getUserServiceUrl())
                .thenReturn("http://localhost:8081");

        when(restTemplate.getForObject(
                "http://localhost:8081/health",
                String.class))
                .thenReturn("UP");

        String result = gatewayService.getUserServiceHealth();

        assertEquals("UP", result);

        verify(restTemplate).getForObject(
                "http://localhost:8081/health",
                String.class
        );
    }

    @Test
    public void getTodoServiceHealth_shouldReturnHealthResponse() {

        when(gatewayProperties.getTodoServiceUrl())
                .thenReturn("http://localhost:8082");

        when(restTemplate.getForObject(
                "http://localhost:8082/health",
                String.class))
                .thenReturn("UP");

        String result = gatewayService.getTodoServiceHealth();

        assertEquals("UP", result);

        verify(restTemplate).getForObject(
                "http://localhost:8082/health",
                String.class
        );
    }

    @Test
    public void authenticate_shouldCallUserServiceAndReturnUser() {

        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("password123");

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setId(1L);
        expectedResponse.setUsername("testuser");

        when(gatewayProperties.getUserServiceUrl())
                .thenReturn("http://localhost:8081");

        when(restTemplate.postForObject(
                "http://localhost:8081/users/authenticate",
                request,
                UserResponse.class))
                .thenReturn(expectedResponse);

        UserResponse result = gatewayService.authenticate(request);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("testuser", result.getUsername());

        verify(restTemplate).postForObject(
                "http://localhost:8081/users/authenticate",
                request,
                UserResponse.class
        );
    }
}