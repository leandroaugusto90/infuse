package com.infuse.api.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1940868465389492629L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable throwable) {
		super(message, throwable);

	}
}
