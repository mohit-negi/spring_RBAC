package com.astute.RBAC.exception;

public class AuthenticatedFailureException extends RuntimeException {
    public AuthenticatedFailureException(String message) {
        super(message);
    }

    public AuthenticatedFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}