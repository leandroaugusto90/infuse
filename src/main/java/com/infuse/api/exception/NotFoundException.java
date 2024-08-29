package com.infuse.api.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3394874707419731121L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
