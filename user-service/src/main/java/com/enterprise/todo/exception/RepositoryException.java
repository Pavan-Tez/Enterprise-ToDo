package com.enterprise.todo.exception;

public class RepositoryException extends ApplicationException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
