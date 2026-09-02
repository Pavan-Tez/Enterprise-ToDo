package com.enterprise.todo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.enterprise.todo.dto.request.LoginRequest;
import com.enterprise.todo.dto.request.RegisterUserRequest;
import com.enterprise.todo.dto.response.UserResponse;
import com.enterprise.todo.exception.ServiceException;
import com.enterprise.todo.model.User;
import com.enterprise.todo.repository.UserRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();

        userService.setUserRepository(userRepository);
        userService.setPasswordEncoder(passwordEncoder);
    }

    @Test
    public void register_validUser_shouldRegisterUserRequest() {
        // Test implementation for registering a valid user

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setPassword("password123");
        request.setEmail("testuser@example.com");
        request.setName("Test User");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername(request.getUsername());
        savedUser.setEmail(request.getEmail());
        savedUser.setName(request.getName());

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponse response = userService.register(request);

        assertNotNull(response);
        assertEquals(Long.valueOf(1L), response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("testuser@example.com", response.getEmail());
        assertEquals("Test User", response.getName());

        verify(userRepository).existsByUsername("testuser");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test(expected = ServiceException.class)
    public void register_emptyUsername_shouldThrowException() {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("");
        request.setPassword("password123");

        userService.register(request);
    }

    @Test(expected = ServiceException.class)
    public void register_emptyPassword_shouldThrowException() {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setPassword("");

        userService.register(request);
    }

    @Test(expected = ServiceException.class)
    public void register_duplicateUsername_shouldThrowException() {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setPassword("password123");

        when(userRepository.existsByUsername("testuser"))
                .thenReturn(true);

        userService.register(request);
    }

    @Test
    public void register_shouldHashPasswordBeforeSaving() {

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setPassword("password123");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        userService.register(request);

        verify(passwordEncoder).encode("password123");

        verify(userRepository).save(argThat(user ->
                "hashedPassword".equals(user.getPasswordHash())
        ));
    }

    @Test
    public void login_validCredentials_shouldReturnUser() {

        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("password123");

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPasswordHash("hashedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches("password123", "hashedPassword"))
                .thenReturn(true);

        UserResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals(Long.valueOf(1L), response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("Test User", response.getName());
        assertEquals("test@example.com", response.getEmail());

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).matches("password123", "hashedPassword");
    }

    @Test(expected = ServiceException.class)
    public void login_userNotFound_shouldThrowException() {

        LoginRequest request = new LoginRequest();
        request.setUsername("unknown");
        request.setPassword("password123");

        when(userRepository.findByUsername("unknown"))
                .thenReturn(null);

        userService.login(request);

        verify(userRepository).findByUsername("unknown");
        verifyZeroInteractions(passwordEncoder);
    }

    @Test(expected = ServiceException.class)
    public void login_invalidPassword_shouldThrowException() {

        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("wrongPassword");

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("hashedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches("wrongPassword", "hashedPassword"))
                .thenReturn(false);

        userService.login(request);
    }

    @Test
    public void findById_existingUser_shouldReturnUser() {

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPasswordHash("hashedPassword");

        when(userRepository.findById(1L)).thenReturn(user);

        UserResponse response = userService.findById(1L);

        assertNotNull(response);
        assertEquals(Long.valueOf(1L), response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("Test User", response.getName());
        assertEquals("test@example.com", response.getEmail());

        verify(userRepository).findById(1L);
    }

    @Test(expected = ServiceException.class)
    public void findById_userNotFound_shouldThrowException() {

        when(userRepository.findById(1L)).thenReturn(null);

        userService.findById(1L);

        verify(userRepository).findById(1L);
    }
}
