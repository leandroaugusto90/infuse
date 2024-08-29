package com.infuse.api.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Valid
public class OrderRequestDTO {

	@NotNull(message = "código do cliente não pode ser nulo")
	private Long customerCode;
	@NotNull(message = "Número controle não pode ser nulo")
	private Long controlNumber;
	private LocalDateTime registrationDate = LocalDateTime.now();
	@NotBlank(message = "Nome do produto não pode ser null")
	private String productName;
	@NotNull(message = "Valor do produto não pode ser nulo")
	private BigDecimal productValue;
	private Integer productQuantity = 1;

	public OrderRequestDTO() {
	}

	public OrderRequestDTO(Long customerCode, Long controlNumber, LocalDateTime registrationDate,
			String productName, BigDecimal productValue, Integer productQuantity) {
		this.customerCode = customerCode;
		this.controlNumber = controlNumber;
		this.registrationDate = registrationDate;
		this.productName = productName;
		this.productValue = productValue;
		this.productQuantity = productQuantity;
	}

	public Long getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(Long customerCode) {
		this.customerCode = customerCode;
	}

	public Long getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(Long controlNumber) {
		this.controlNumber = controlNumber;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductValue() {
		return productValue;
	}

	public void setProductValue(BigDecimal productValue) {
		this.productValue = productValue;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Integer getProductQuantity() {
		if (productQuantity == 0) {
			productQuantity = 1;
		}
		return productQuantity;
	}

}
