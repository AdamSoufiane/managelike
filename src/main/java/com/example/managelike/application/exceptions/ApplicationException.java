package com.example.managelike.application.exceptions;

import java.io.Serializable;

/**
 * ApplicationException is used to handle exceptions that arise within the application layer,
 * such as use case execution failures.
 */
public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
