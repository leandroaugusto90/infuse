package com.infuse.api.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO_CLIENTE")
public class CustomerOrder {

	@Id
	@Column(name = "ID_PEDIDO_CLIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerOrderId;

	@OneToOne
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	private Customer customer;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
	private Order order;

	@Column(name = "VALOR_PEDIDO")
	private BigDecimal totalValue;

	@Column(name = "DESCONTO")
	private Boolean isDiscont;

	public CustomerOrder() {
	}

	public CustomerOrder(Long customerOrderId, Customer customer, Order order, BigDecimal totalValue,
			Boolean isDiscont) {
		this.customerOrderId = customerOrderId;
		this.customer = customer;
		this.order = order;
		this.totalValue = totalValue;
		this.isDiscont = isDiscont;
	}

	public Long getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(Long customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public Boolean getIsDiscont() {
		return isDiscont;
	}

	public void setIsDiscont(Boolean isDiscont) {
		this.isDiscont = isDiscont;
	}

}
