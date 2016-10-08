package com.ngproject.api.controller.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -6193906243861548120L;
	
	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String message) {
		super(message);
	}

}
