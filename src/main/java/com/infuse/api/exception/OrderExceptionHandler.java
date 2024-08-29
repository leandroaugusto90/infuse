package com.infuse.api.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infuse.api.model.exception.ObjectInvalidResponse;
import com.infuse.api.model.exception.Response;

@ControllerAdvice
@RestControllerAdvice
public class OrderExceptionHandler<T> extends ResponseEntityExceptionHandler {

	private static final HttpStatus HTTP_STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;
	private static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
	private static final HttpStatus HTTP_STATUS_CONFLICT = HttpStatus.CONFLICT;
	private static final HttpStatus HTTP_STATUS_TOO_MANY_REQUESTS = HttpStatus.TOO_MANY_REQUESTS;
	private static final HttpStatus HTTP_STATUS_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

	private final MessageSource messageSource;

	OrderExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(value = { BusinessException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Response<T>> handleBusinessException(BusinessException businessException) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(businessException.getMessage(), HTTP_STATUS_BAD_REQUEST);
		return buildResponseEntity(response, HTTP_STATUS_BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Response<T>> handleNotFoundException(NotFoundException notFoundException) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(notFoundException.getMessage(), HTTP_STATUS_NOT_FOUND);
		return buildResponseEntity(response, HTTP_STATUS_NOT_FOUND);
	}

	@ExceptionHandler(value = { HttpClientErrorException.Conflict.class })
	protected ResponseEntity<Response<T>> handleConflictException(HttpClientErrorException exception) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage(), HTTP_STATUS_CONFLICT);
		return buildResponseEntity(response, HTTP_STATUS_CONFLICT);
	}

	@ExceptionHandler(value = { HttpClientErrorException.TooManyRequests.class })
	protected ResponseEntity<Response<T>> handleTooManyRequestException(HttpClientErrorException exception) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage(), HTTP_STATUS_TOO_MANY_REQUESTS);
		return buildResponseEntity(response, HTTP_STATUS_TOO_MANY_REQUESTS);
	}

	@ExceptionHandler(value = { ServerErrorException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Response<T>> handleInternalServerErrorException(ServerErrorException exception) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage(), HTTP_STATUS_INTERNAL_SERVER_ERROR);
		return buildResponseEntity(response, HTTP_STATUS_INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatus statusCode, @NonNull WebRequest request) {

		List<ObjectInvalidResponse> objectInvalidResponses = new ArrayList<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			objectInvalidResponses.add(new ObjectInvalidResponse(fieldError.getObjectName(), fieldError.getField(),
					fieldError.getRejectedValue(),
					messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())));
		}

		Response<T> response = new Response<>();
		response.addErrorsObjectInvalidToResponse("Erro de validação do campo "
				+ objectInvalidResponses.get(0).getField() + ": " + objectInvalidResponses.get(0).getMessage(),
				objectInvalidResponses, statusCode);
		return new ResponseEntity<>(response, statusCode);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<Response<T>>> handle(ConstraintViolationException constraintViolationException) {
		List<Response<T>> listResponse = new ArrayList<>();
		
	    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
	    
	    if (!violations.isEmpty()) {
	        violations.forEach(violation -> {
	        	Response<T> response = new Response<>();
	        	response.addErrorMsgToResponse(violation.getMessage(), HTTP_STATUS_BAD_REQUEST);
	        	Response<T> body = buildResponseEntity(response, HTTP_STATUS_BAD_REQUEST).getBody();
	        	listResponse.add(body);
	        });
	    }
		return buildResponseEntityList(listResponse, HTTP_STATUS_BAD_REQUEST);
	 }

	private ResponseEntity<Response<T>> buildResponseEntity(Response<T> response, HttpStatus httpStatus) {
		return new ResponseEntity<>(response, httpStatus);
	}
	
	private ResponseEntity<List<Response<T>>> buildResponseEntityList(List<Response<T>> responseList, HttpStatus httpStatus) {
		return new ResponseEntity<>(responseList, httpStatus);
	}
}
