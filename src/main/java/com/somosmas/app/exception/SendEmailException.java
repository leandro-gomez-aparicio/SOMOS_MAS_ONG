package com.somosmas.app.exception;

public class SendEmailException extends Exception {

    private static final String ERROR_MESSAGE = "Fail to send the email.";

    public SendEmailException() {
        super(ERROR_MESSAGE);
    }
}
