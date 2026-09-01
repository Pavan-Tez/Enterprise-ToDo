package com.enterprise.todo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.enterprise.todo.dto.request.CreateTodoRequest;
import com.enterprise.todo.dto.request.UpdateTodoRequest;
import com.enterprise.todo.dto.response.TodoResponse;
import com.enterprise.todo.service.TodoService;

@RequestMapping("/todos")
public class TodoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private TodoService todoService;

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreateTodoForm() {

        LOGGER.info("Displaying create todo page");

        return new ModelAndView("create-todo");
    }

    @RequestMapping(value ="/create", method = RequestMethod.POST)
    public ModelAndView createTodo(@ModelAttribute CreateTodoRequest createTodoRequest, HttpSession session) {
        LOGGER.info("Handling request to create a new todo");
        // Implementation for handling the request to create a new todo

        Long userId = (Long) session.getAttribute("userId");
        LOGGER.info("Create todo request for userId={}",userId);

        todoService.createTodo(createTodoRequest, userId);

        return new ModelAndView("redirect:/todos");
        
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTodos(HttpSession session) {
        LOGGER.info("Handling request to get todos");
        // Implementation for handling the request to get todos

        Long userId = (Long) session.getAttribute("userId");
        LOGGER.info("Get todos request for userId={}",userId);

        List<TodoResponse> todoResponse = todoService.getTodos(userId);

        ModelAndView modelAndView = new ModelAndView("todos");
        modelAndView.addObject("todos", todoResponse);

        return modelAndView;
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.GET)
    public ModelAndView getTodoById(@PathVariable Long todoId, HttpSession session) {
        LOGGER.info("Handling request to get todo by ID: {}", todoId);
        // Implementation for handling the request to get a todo by ID

        Long userId = (Long) session.getAttribute("userId");
        LOGGER.info("Get todo by ID request for userId={}",userId);

        TodoResponse todoResponse = todoService.getTodo(todoId, userId);

        ModelAndView modelAndView = new ModelAndView("todo");
        modelAndView.addObject("todo", todoResponse);

        return modelAndView;
    }

    @RequestMapping(value = "/{todoId}/update", method = RequestMethod.POST)
    public ModelAndView updateTodo(@PathVariable Long todoId, @ModelAttribute UpdateTodoRequest updateTodoRequest, HttpSession session) {
        LOGGER.info("Handling request to update todo with ID: {}", todoId);
        // Implementation for handling the request to update a todo

        Long userId = (Long) session.getAttribute("userId");
        LOGGER.info("Update todo request for userId={}, todoId={}", userId, todoId);

        todoService.updateTodo(todoId, userId, updateTodoRequest);

        return new ModelAndView("redirect:/todos");
    }

    @RequestMapping(value = "/{todoId}/delete", method = RequestMethod.POST)
    public ModelAndView deleteTodo(@PathVariable Long todoId, HttpSession session) {
        LOGGER.info("Handling request to delete todo with ID: {}", todoId);
        // Implementation for handling the request to delete a todo

        Long userId = (Long) session.getAttribute("userId");
        LOGGER.info("Delete todo request for userId={}, todoId={}", userId, todoId);

        todoService.deleteTodo(todoId, userId);

        return new ModelAndView("redirect:/todos");
    }
}
