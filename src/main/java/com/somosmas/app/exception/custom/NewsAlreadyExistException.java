package com.somosmas.app.exception.custom;

import java.text.MessageFormat;

public class NewsAlreadyExistException extends Exception {

	private static final String ERROR_MESSAGE = "News already exist: {0}";

	public NewsAlreadyExistException(String name) {
		super(MessageFormat.format(ERROR_MESSAGE, name));
	}
}
