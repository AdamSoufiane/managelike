package com.example.managelike.domain.exceptions;

import lombok.Getter;

/**
 * DomainException is used to handle exceptions that are specific to the domain layer,
 * such as domain rule violations.
 */
@Getter
public class DomainException extends RuntimeException {

    private final String errorCode;

    public DomainException(String message) {
        super(message);
        this.errorCode = "UNSPECIFIED";
    }

    public DomainException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "UNSPECIFIED";
    }

    public DomainException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "DomainException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", cause=" + getCause() +
                '}';
    }
}