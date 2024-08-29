package com.infuse.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infuse.api.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(Long customerId);
}
