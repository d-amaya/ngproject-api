package com.ngproject.api.controller.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7814819134440418851L;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		super(message);
	}

}
