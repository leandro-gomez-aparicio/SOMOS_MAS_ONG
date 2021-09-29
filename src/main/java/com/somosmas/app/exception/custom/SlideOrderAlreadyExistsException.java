package com.somosmas.app.exception.custom;

public class SlideOrderAlreadyExistsException extends Exception{
    private static final String ERROR_MESSAGE="Slide order already exists";

    public SlideOrderAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }
}
