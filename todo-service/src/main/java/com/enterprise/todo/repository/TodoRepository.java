package com.enterprise.todo.repository;

import com.enterprise.todo.model.Todo;

import java.util.List;

public interface TodoRepository {

    Todo save(Todo todo);

    Todo findByIdAndUserId(Long todoId, Long userId);

    List<Todo> findAllByUserId(Long userId);

    void update(Todo todo);

    void deleteByIdAndUserId(Long todoId, Long userId);
}