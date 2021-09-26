package com.somosmas.app.exception.custom;

import java.text.MessageFormat;

public class OperationAccessDeniedException extends Exception {

    private static final String ERROR_MESSAGE = "Access denied for {0} operation. Reason: {1}";

    public OperationAccessDeniedException(String operation, String reason) {
        super(MessageFormat.format(ERROR_MESSAGE, operation, reason));
    }

}