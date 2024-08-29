package com.infuse.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class Order {

	@Id
	@Column(name = "ID_PEDIDO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
	private CustomerOrder customerOrder;

	@Column(name = "NUMERO_CONTROLE")
	private Long controlNumber;

	@Column(name = "DATA_CADASTRO")
	private LocalDateTime registrationDate;

	@Column(name = "NOME_PRODUTO")
	private String productName;

	@Column(name = "VALOR_PRODUTO")
	private BigDecimal productValue;

	@Column(name = "QUANTIDADE_PRODUTO")
	private Integer productQuantity;

	public Order() {
	}

	public Order(Long orderId) {
		this.orderId = orderId;
	}

	public Order(Long orderId, CustomerOrder customerOrder, Long controlNumber, LocalDateTime registrationDate,
			String productName, BigDecimal productValue, Integer productQuantity) {
		this.orderId = orderId;
		this.customerOrder = customerOrder;
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

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
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
