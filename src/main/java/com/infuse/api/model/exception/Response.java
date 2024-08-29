package com.infuse.api.model.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Response", description = "Objeto de resposta para as exceções")
public class Response<T> {

	private T data;

	@Schema(description = "informações do erro")
	private ResponseError error;

	@Schema(description = "Data e hora do erro")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();

	public Response() {
	}

	public Response(T data, ResponseError error) {
		this.data = data;
		this.error = error;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void addDataResponse(T data) {
		setData(data);
	}

	public void addErrorMsgToResponse(String messageError, HttpStatus httpStatus) {
		setError(new ResponseError(messageError, httpStatus.getReasonPhrase(), httpStatus.value()));
	}

	public void addErrorsObjectInvalidToResponse(String messageError,
			List<ObjectInvalidResponse> objectInvalidResponses, HttpStatus statusCode) {
		setError(new ResponseError(messageError, objectInvalidResponses,
				HttpStatus.valueOf(statusCode.value()).getReasonPhrase(), statusCode.value()));
	}
}
