package com.infuse.api.model.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Objeto com as informações da exceção")
public class ResponseError {

	@Schema(description = "Mensagem do erro", example = "Preenchimento inválido do formulário")
	private String message;

	@Schema(description = "Campos inválidos de um formulário", example = "O campo CPF é inválido")
	private List<ObjectInvalidResponse> invalidFields;

	@Schema(description = "Descrição do status HTTP", example = "Bad Request")
	private String status;

	@Schema(description = "Código do erro", example = "400")
	private int code;

	public ResponseError() {
	}

	public ResponseError(String message, List<ObjectInvalidResponse> invalidFields, String status, int code) {
		this.message = message;
		this.invalidFields = invalidFields;
		this.status = status;
		this.code = code;
	}

	public ResponseError(String messageError, String httpStatus, int code) {
		this.message = messageError;
		this.status = httpStatus;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ObjectInvalidResponse> getInvalidFields() {
		return invalidFields;
	}

	public void setInvalidFields(List<ObjectInvalidResponse> invalidFields) {
		this.invalidFields = invalidFields;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}