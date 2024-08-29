package com.infuse.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infuse.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByControlNumber(Long controlNumber);
}
