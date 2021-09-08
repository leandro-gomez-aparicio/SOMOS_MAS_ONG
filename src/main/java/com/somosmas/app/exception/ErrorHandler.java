package com.somosmas.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Invalid credentials.";
    private static final String VALIDATIONS_ERROR_MESSAGE = "Validation error.";

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<Object> userAlreadyExistException(HttpServletRequest request, UserAlreadyExistException exception) {
        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> dataAccessException(HttpServletRequest request, NoSuchElementException exception) {
        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> authenticationDeniedException(HttpServletRequest request, BadCredentialsException exception) {
        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(INVALID_CREDENTIALS_ERROR_MESSAGE, HttpStatus.UNAUTHORIZED.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> constraintViolationException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result=exception.getBindingResult();
        List<FieldError> fieldErrors=result.getFieldErrors();
        ErrorInfo errorInfo = new ErrorInfo(VALIDATIONS_ERROR_MESSAGE, HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        for(FieldError fieldError:fieldErrors){
            errorInfo.addFieldError(fieldError);
        }
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception exception) {
        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
