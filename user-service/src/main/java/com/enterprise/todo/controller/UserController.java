package com.enterprise.todo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enterprise.todo.dto.request.LoginRequest;
import com.enterprise.todo.dto.request.RegisterUserRequest;
import com.enterprise.todo.dto.response.UserResponse;
import com.enterprise.todo.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("Displaying login page");
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginRequest request, HttpSession session) {

        logger.info("Login request received for user={}", request.getUsername());

        UserResponse userResponse = userService.login(request);

        session.setAttribute("userId", userResponse.getId());

        logger.info("User logged in successfully with id={}", userResponse.getId());

        return "redirect:/todos";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        logger.info("Displaying registration page");
        return "register";
    }

    @PostMapping("/register")
    public String register(RegisterUserRequest request){
        logger.info("Registering user: {}", request.getUsername());
        userService.register(request);
        return "redirect:/users/login";
    }  
}
