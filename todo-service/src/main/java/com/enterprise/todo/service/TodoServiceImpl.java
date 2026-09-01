package com.enterprise.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enterprise.todo.dto.request.CreateTodoRequest;
import com.enterprise.todo.dto.request.UpdateTodoRequest;
import com.enterprise.todo.dto.response.TodoResponse;
import com.enterprise.todo.exception.RepositoryException;
import com.enterprise.todo.exception.ServiceException;
import com.enterprise.todo.exception.TodoNotFoundException;
import com.enterprise.todo.model.Todo;
import com.enterprise.todo.model.TodoStatus;
import com.enterprise.todo.repository.TodoRepository;

public class TodoServiceImpl implements TodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);

    private TodoRepository todoRepository;

    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponse createTodo(CreateTodoRequest createTodoRequest, Long userId) {
        LOGGER.info("Creating a new todo for user with ID: {}", userId);
        // Implementation for creating a new todo
        
        try {
            Todo todo = new Todo();

            todo.setUserId(userId);
            todo.setTitle(createTodoRequest.getTitle());
            todo.setDescription(createTodoRequest.getDescription());
            todo.setStatus(TodoStatus.TODO);

            TodoResponse response = new TodoResponse();

            Todo savedTodo = todoRepository.save(todo);
            response.setId(savedTodo.getId());
            response.setTitle(savedTodo.getTitle());
            response.setDescription(savedTodo.getDescription());
            response.setStatus(savedTodo.getStatus());

            return response;

            } catch (RepositoryException e) {
                LOGGER.error("Failed to create todo for userId={}", userId, e);

                throw new ServiceException(
                    "Unable to create todo",
                    e
                );
            }
        
    }

    @Override
    public TodoResponse getTodo(Long todoId, Long userId) {
       LOGGER.info("Retrieving todo with ID: {} for user with ID: {}", todoId, userId);
        // Implementation for retrieving a specific todo
        try {
            Todo todo = todoRepository.findByIdAndUserId(todoId, userId);
            if (todo == null) {
                throw new TodoNotFoundException("Todo not found for the given ID and user");
            }

            TodoResponse response = new TodoResponse();
            response.setId(todo.getId());
            response.setTitle(todo.getTitle());
            response.setDescription(todo.getDescription());
            response.setStatus(todo.getStatus());

            return response;

        } catch (RepositoryException e) {
            LOGGER.error("Failed to retrieve todo with ID: {} for userId={}", todoId, userId, e);

            throw new ServiceException(
                "Unable to retrieve todo",
                e
            );
        }
    }

    @Override
    public List<TodoResponse> getTodos(Long userId) {
        LOGGER.info("Retrieving all todos for user with ID: {}", userId);
        // Implementation for retrieving all todos for a user
        try {
            List<Todo> todos = todoRepository.findAllByUserId(userId);
            return todos.stream().map(todo -> {
                TodoResponse response = new TodoResponse();
                response.setId(todo.getId());
                response.setTitle(todo.getTitle());
                response.setDescription(todo.getDescription());
                response.setStatus(todo.getStatus());
                return response;
            }).collect(Collectors.toList());
        } catch (RepositoryException e) {
            LOGGER.error("Failed to retrieve todos for userId={}", userId, e);

            throw new ServiceException(
                "Unable to retrieve todos",
                e
            );
        }
    }

    @Override
    public TodoResponse updateTodo(Long todoId, Long userId, UpdateTodoRequest updateTodoRequest) {
        LOGGER.info("Updating todo with ID: {} for user with ID: {}", todoId, userId);

        try {
            Todo todo = todoRepository.findByIdAndUserId(todoId, userId);
            if (todo == null) {
                throw new TodoNotFoundException("Todo not found for the given ID and user");
            }

            todo.setTitle(updateTodoRequest.getTitle());
            todo.setDescription(updateTodoRequest.getDescription());
            todo.setStatus(updateTodoRequest.getStatus());

            todoRepository.update(todo);

            TodoResponse response = new TodoResponse();
            response.setId(todo.getId());
            response.setTitle(todo.getTitle());
            response.setDescription(todo.getDescription());
            response.setStatus(todo.getStatus());

            return response;

        } catch (RepositoryException e) {
            LOGGER.error("Failed to update todo with ID: {} for userId={}", todoId, userId, e);

            throw new ServiceException(
                "Unable to update todo",
                e
            );
        }
    }

    @Override
    public void deleteTodo(Long todoId, Long userId) {
        
        LOGGER.info("Deleting todo with ID: {} for user with ID: {}", todoId, userId);

        try {
            Todo todo = todoRepository.findByIdAndUserId(todoId, userId);
            if (todo == null) {
                throw new TodoNotFoundException("Todo not found for the given ID and user");
            }

            todoRepository.deleteByIdAndUserId(todoId, userId);

        } catch (RepositoryException e) {
            LOGGER.error("Failed to delete todo with ID: {} for userId={}", todoId, userId, e);

            throw new ServiceException(
                "Unable to delete todo",
                e
            );
        }
    }
    
}
