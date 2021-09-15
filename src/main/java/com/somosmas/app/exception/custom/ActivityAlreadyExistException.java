package com.somosmas.app.exception.custom;

import java.text.MessageFormat;

public class ActivityAlreadyExistException extends Exception{
	
	private static final String ERROR_MESSAGE = "Activity already exist: {0}";
    
    public ActivityAlreadyExistException(String name) {
    	super(MessageFormat.format(ERROR_MESSAGE, name));
    }

}
