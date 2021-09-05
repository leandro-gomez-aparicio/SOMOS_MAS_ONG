package com.somosmas.app.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfo {

	@JsonProperty("message")
	private String message;
	@JsonProperty("status_code")
	private int statusCode;
	@JsonProperty("uri")
	private String uriRequested;

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(String message, int statusCode, String uriRequested) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.uriRequested = uriRequested;
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

}
