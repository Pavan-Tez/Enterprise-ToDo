package com.enterprise.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.enterprise.todo.dto.request.LoginRequest;
import com.enterprise.todo.dto.request.RegisterUserRequest;
import com.enterprise.todo.dto.response.UserResponse;
import com.enterprise.todo.exception.ServiceException;
import com.enterprise.todo.model.User;
import com.enterprise.todo.repository.UserRepository;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse register(RegisterUserRequest request) {
        // TODO Auto-generated method stub
        logger.info("Processing registration for user={}",request.getUsername());

        if(request.getUsername() == null || request.getUsername().isEmpty()){
            logger.warn("Registration failed: username is empty");
            throw new ServiceException("Username cannot be empty");
        }
        if(request.getPassword() == null || request.getPassword().isEmpty()){
            logger.warn("Registration failed: password is empty");
            throw new ServiceException("Password cannot be empty");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            logger.warn("Registration rejected: username already exists");
            throw new ServiceException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        //we will replace this in the future with a hashed password
        //user.setPasswordHash(request.getPassword());

        user.setPasswordHash(
            passwordEncoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with id={}", savedUser.getId());

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setUsername(savedUser.getUsername());
        return response;
        
    }

    @Override
    public UserResponse login(LoginRequest request) {
        
        logger.info("Processing login for user={}", request.getUsername());

        User user = userRepository.findByUsername(request.getUsername());

        if(user == null){
            logger.warn("Login failed for user={} - user not found", request.getUsername());
            throw new ServiceException("User not found");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            logger.warn("Login failed: invalid credentials");
            throw new ServiceException("Invalid username or password");
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }

    @Override
    public UserResponse findById(Long userId) {
        User user = userRepository.findById(userId);

        if(user == null){
            throw new ServiceException("User not found");
        }
        logger.info("User found with id={}", userId);
        UserResponse response = new UserResponse();
       
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        
        return response;
    }
    
}
