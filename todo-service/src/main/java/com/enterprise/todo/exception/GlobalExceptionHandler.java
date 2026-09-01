package com.enterprise.todo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TodoNotFoundException.class)
    public ModelAndView handleTodoNotFoundException(
            TodoNotFoundException ex) {

        LOGGER.warn("Todo not found: {}", ex.getMessage());

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("status", HttpStatus.NOT_FOUND.value());

        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(
            ServiceException ex) {

        LOGGER.error("Service error", ex);

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject(
                "message",
                "Unable to process your request"
        );

        modelAndView.addObject(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return modelAndView;
    }

    @ExceptionHandler(RepositoryException.class)
    public ModelAndView handleRepositoryException(
            RepositoryException ex) {

        LOGGER.error("Repository error", ex);

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject(
                "message",
                "A database error occurred"
        );

        modelAndView.addObject(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {

        LOGGER.error("Unexpected error occurred", ex);

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject(
                "message",
                "An unexpected error occurred"
        );

        modelAndView.addObject(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return modelAndView;
    }
}