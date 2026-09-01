package com.enterprise.todo.dto.response;

import com.enterprise.todo.model.TodoStatus;

public class TodoResponse {
    
    private Long id;
    private String title;
    private String description;
    private TodoStatus status;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
