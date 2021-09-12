package com.somosmas.app.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorInfo {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("uri")
    private String uriRequested;
    @JsonProperty("errors")
    private List<String> errors;

    public ErrorInfo() {
        super();
    }

    public ErrorInfo(String message, int statusCode, String uriRequested) {
        super();
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.errors = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUriRequested() {
        return uriRequested;
    }

    public void setUriRequested(String uriRequested) {
        this.uriRequested = uriRequested;
    }

    public void addFieldError(FieldError fieldError) {
        errors.add(fieldError.getDefaultMessage());
    }
}
