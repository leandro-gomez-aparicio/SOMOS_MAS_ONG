package com.somosmas.app.exception;

public class ConstraintViolationException extends Exception{
    private static final String ERROR_MESSAGE = "Constraint Violation";
    public ConstraintViolationException() {
        super(ERROR_MESSAGE);
    }

    public ConstraintViolationException(String message) {
        super(message);
    }
}
