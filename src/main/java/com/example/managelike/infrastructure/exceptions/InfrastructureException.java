package com.example.managelike.infrastructure.exceptions;

import lombok.NoArgsConstructor;

/**
 * InfrastructureException is used to handle exceptions that are specific to the infrastructure layer,
 * such as database access errors.
 */
@NoArgsConstructor
public class InfrastructureException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InfrastructureException(String message) {
        super(message);
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfrastructureException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
