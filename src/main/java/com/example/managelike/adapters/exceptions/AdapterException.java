package com.example.managelike.adapters.exceptions;

import lombok.NoArgsConstructor;

/**
 * AdapterException is used to handle exceptions that occur within the adapter layer, such as communication issues with external services.
 */
@NoArgsConstructor
public class AdapterException extends RuntimeException {

    public AdapterException(String message) {
        super(message);
    }

    public AdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterException(Throwable cause) {
        super(cause);
    }
}
