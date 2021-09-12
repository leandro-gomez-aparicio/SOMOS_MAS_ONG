package com.somosmas.app.exception.custom;

import java.text.MessageFormat;

public class CategoryAlreadyExistException extends Exception{

    private static final String ERROR_MESSAGE = "Category already exist: {0}";
    
    public CategoryAlreadyExistException(String name) {
    	super(MessageFormat.format(ERROR_MESSAGE, name));
    }
	
}
