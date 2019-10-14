package com.ss.lms.exception;

public class ApplicationErrors {
	
	private String message;
	private String statusCode;

	public ApplicationErrors(String message, String statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
