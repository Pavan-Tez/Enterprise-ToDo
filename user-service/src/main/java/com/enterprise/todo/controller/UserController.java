package com.enterprise.todo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

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

        logger.info("userService is null: {}", userService == null);
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
        UserResponse createdUser = userService.register(request);
        return "redirect:/users/login?notification=account-created&userId="
                + createdUser.getId();
    }  

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        logger.info("User logout requested");

        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {

        logger.info("Profile page requested");

        Long userId = (Long) session.getAttribute("userId");

        UserResponse user = userService.findById(userId);

        ModelAndView model = new ModelAndView("profile");

        model.addObject("user", user);

        return model;
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<UserResponse> authenticate(@RequestBody LoginRequest request){

        logger.info("Authenticating user: {}", request.getUsername());

        try {
            UserResponse userResponse = userService.login(request);

            logger.info("User authenticated successfully with id={}", userResponse.getId());

            return ResponseEntity.ok(userResponse);
        } catch (com.enterprise.todo.exception.ServiceException ex) {
            logger.warn("Authentication failed for user={}", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
