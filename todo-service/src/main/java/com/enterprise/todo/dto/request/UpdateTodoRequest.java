package com.enterprise.todo.dto.request;

import com.enterprise.todo.model.TodoStatus;

public class UpdateTodoRequest {
    private String title;
    private String description;
    private TodoStatus status;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TodoStatus getStatus() {
        return status;
    }
    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    
}
