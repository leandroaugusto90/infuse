package com.infuse.api.model.dto.response;

import java.math.BigDecimal;

import com.infuse.api.model.dto.CustomerDTO;
import com.infuse.api.model.dto.OrderDTO;

public class OrderResponseDTO {

	private Long customerOrderId;
	private CustomerDTO customer;
	private OrderDTO order;
	private BigDecimal totalValue;
	private Boolean isDiscont = Boolean.FALSE;

	public OrderResponseDTO() {
	}

	public OrderResponseDTO(Long customerOrderId, CustomerDTO customer, OrderDTO order, BigDecimal totalValue,
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

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
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
