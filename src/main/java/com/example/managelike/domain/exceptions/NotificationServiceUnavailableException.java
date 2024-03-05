package com.example.managelike.domain.exceptions;

public class NotificationServiceUnavailableException extends RuntimeException {

    public NotificationServiceUnavailableException(String message) {
        super(message);
    }

    public NotificationServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
