package com.somosmas.app.exception;

public class AuthenticationDeniedException extends Exception {
    private static final String ERROR_MESSAGE = "Invalid credentials";
    public AuthenticationDeniedException() {
        super(ERROR_MESSAGE);
    }


}
