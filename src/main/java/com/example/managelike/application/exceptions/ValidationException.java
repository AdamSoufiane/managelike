package com.example.managelike.application.exceptions;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {

    private final Errors errors;

    public ValidationException(String message) {
        super(message);
        this.errors = null;
    }

    public ValidationException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
