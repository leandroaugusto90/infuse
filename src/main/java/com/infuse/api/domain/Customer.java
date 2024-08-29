package com.infuse.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Customer {

	@Id
	@Column(name = "ID_CLIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "NOME")
	private String name;

	@Column(name = "CPF_CNPJ")
	private String cpfCnpj;

	public Customer() {
	}

	public Customer(Long customerId) {
		this.customerId = customerId;
	}

	public Customer(Long customerId, String name, String cpfCnpj) {
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
