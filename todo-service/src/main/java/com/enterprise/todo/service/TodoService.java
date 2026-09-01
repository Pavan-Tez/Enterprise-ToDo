package com.enterprise.todo.service;

import java.util.List;

import com.enterprise.todo.dto.request.CreateTodoRequest;
import com.enterprise.todo.dto.request.UpdateTodoRequest;
import com.enterprise.todo.dto.response.TodoResponse;


public interface TodoService {
       
    TodoResponse createTodo(CreateTodoRequest createTodoRequest, Long userId);

    TodoResponse getTodo(Long todoId, Long userId);

    List<TodoResponse> getTodos(Long userId);

    TodoResponse updateTodo(Long todoId, Long userId, UpdateTodoRequest updateTodoRequest);

    void deleteTodo(Long todoId, Long userId);
}
