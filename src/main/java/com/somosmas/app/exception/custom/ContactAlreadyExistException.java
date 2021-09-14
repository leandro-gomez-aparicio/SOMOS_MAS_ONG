package com.somosmas.app.exception.custom;

import java.text.MessageFormat;

public class ContactAlreadyExistException extends Exception{
    private static final String ERROR_MESSAGE = "Contact already exist: {0}";
    public ContactAlreadyExistException(String email) {
        super(MessageFormat.format(ERROR_MESSAGE, email));
    }
}
