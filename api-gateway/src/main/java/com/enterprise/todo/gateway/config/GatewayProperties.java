package com.enterprise.todo.gateway.config;

public class GatewayProperties {
    
    private String userServiceUrl;
    private String todoServiceUrl;
    public String getUserServiceUrl() {
        return userServiceUrl;
    }
    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
    public String getTodoServiceUrl() {
        return todoServiceUrl;
    }
    public void setTodoServiceUrl(String todoServiceUrl) {
        this.todoServiceUrl = todoServiceUrl;
    }

    // Getters and setters
    
}
