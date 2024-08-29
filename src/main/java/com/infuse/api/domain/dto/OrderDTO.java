package com.infuse.api.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO {

	private Long orderId;
	private Long controlNumber;
	private LocalDateTime registrationDate;
	private String productName;
	private BigDecimal productValue;
	private Integer productQuantity;

	public OrderDTO() {
	}

	public OrderDTO(Long orderId, Long controlNumber, LocalDateTime registrationDate, String productName,
			BigDecimal productValue, Integer productQuantity) {
		this.orderId = orderId;
		this.controlNumber = controlNumber;
		this.registrationDate = registrationDate;
		this.productName = productName;
		this.productValue = productValue;
		this.productQuantity = productQuantity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

}
