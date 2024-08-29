package com.infuse.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1940868465389492629L;

	public BusinessException(String message) {
		super(message);
	}
}
