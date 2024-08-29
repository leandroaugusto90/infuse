package com.infuse.api.service;

import java.time.LocalDate;
import java.util.List;

import com.infuse.api.domain.dto.request.OrderRequestDTO;
import com.infuse.api.domain.dto.response.OrderResponseDTO;

public interface OrderService {

	List<OrderResponseDTO> save(List<OrderRequestDTO> orderRequest);
	
	List<OrderResponseDTO> getOrders(Long controlNumber, LocalDate registrationDate, String productName, Integer productQuantity, Long customerCode);
}