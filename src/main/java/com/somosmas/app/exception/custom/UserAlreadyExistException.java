package com.somosmas.app.exception.custom;

import java.text.MessageFormat;

public class UserAlreadyExistException extends Exception {

    private static final String ERROR_MESSAGE = "User already exist: {0}";

    public UserAlreadyExistException(String username) {
        super(MessageFormat.format(ERROR_MESSAGE, username));
    }

}
