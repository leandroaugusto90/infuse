package com.infuse.api.domain.dto;

public class CustomerDTO {

	private Long customerId;
	private String name;
	private String cpfCnpj;

	public CustomerDTO() {
	}

	public CustomerDTO(Long customerId) {
		this.customerId = customerId;
	}

	public CustomerDTO(Long customerId, String name, String cpfCnpj) {
		this.customerId = customerId;
		this.name = name;
		this.cpfCnpj = cpfCnpj;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

}
