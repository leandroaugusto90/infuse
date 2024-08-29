package com.infuse.api.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infuse.api.exception.BusinessException;
import com.infuse.api.model.Customer;
import com.infuse.api.model.CustomerOrder;
import com.infuse.api.model.Order;
import com.infuse.api.model.dto.request.OrderRequestDTO;
import com.infuse.api.model.dto.response.OrderResponseDTO;
import com.infuse.api.model.mapper.OrderMapper;
import com.infuse.api.repository.CustomerOrderRepository;
import com.infuse.api.repository.CustomerRepository;
import com.infuse.api.repository.OrderRepository;
import com.infuse.api.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Double PERCENT_5 = 0.05;
	private static final Double PERCENT_10 = 0.10;

	private final CustomerOrderRepository customerOrderRepository;
	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final OrderMapper mapper;

	public OrderServiceImpl(CustomerOrderRepository customerOrderRepository, OrderRepository orderRepository, CustomerRepository customerRepository, OrderMapper mapper) {
		this.customerOrderRepository = customerOrderRepository;
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}
	
	@Override
	public List<OrderResponseDTO> getOrders(Long controlNumber, LocalDate registrationDate, String productName, Integer productQuantity, Long customerCode) {
		return customerOrderRepository.getOrders(controlNumber, registrationDate, productName, productQuantity, customerCode).stream()
				.map(mapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderResponseDTO> save(List<OrderRequestDTO> orderRequest) {
		List<OrderResponseDTO> responseList = new ArrayList<>();
		validateOrders(orderRequest);
		orderRequest.forEach(orderReq -> {
			CustomerOrder customerOrder = new CustomerOrder();
			Customer customer = customerRepository.findByCustomerId(orderReq.getCustomerCode()).orElseThrow(() -> new BusinessException("Cliente não encontrado!"));
			Order order = mapper.requestToEntity(orderReq);
			
			customerOrder.setOrder(order);
			customerOrder.setCustomer(customer);
			customerOrder.setTotalValue(calculateTotalValue(orderReq.getProductQuantity(), orderReq.getProductValue()));
			
			if((orderReq.getProductQuantity() > 5 && orderReq.getProductQuantity() < 10) || orderReq.getProductQuantity() >= 10) {
				customerOrder.setIsDiscont(true);
			}
			
			order.setCustomerOrder(customerOrder);
			Order orderSaved = orderRepository.save(order);
			
			OrderResponseDTO orderResponse = mapper.orderEntityToResponse(orderSaved);
			orderResponse.setCustomer(mapper.customerEntityToDto(customer));
			orderResponse.setCustomerOrderId(orderSaved.getCustomerOrder().getCustomerOrderId());
			orderResponse.setTotalValue(orderSaved.getCustomerOrder().getTotalValue());
			responseList.add(orderResponse);
		});
		return responseList; 
	}

	private void validateOrders(List<OrderRequestDTO> orderRequest) {
		if(orderRequest.size() > 10) {
			throw new BusinessException("Requisição limitada a 10 pedidos");
		}
		orderRequest.forEach(order -> {
			if(orderRepository.findByControlNumber(order.getControlNumber()).isPresent()) {
				throw new BusinessException("Número controle " + order.getControlNumber() + " já cadastrado!");
			}
		});
	}
	
	private BigDecimal calculateTotalValue(Integer productQuantity, BigDecimal productValue) {
		BigDecimal totalValue = productValue.multiply(BigDecimal.valueOf(productQuantity));
		if(productQuantity > 5 && productQuantity < 10) {
			totalValue = (totalValue.subtract(totalValue.multiply(BigDecimal.valueOf(PERCENT_5))));
		}else if(productQuantity >= 10) {
			totalValue = (totalValue.subtract(totalValue.multiply(BigDecimal.valueOf(PERCENT_10))));
		}
		return totalValue;
	}
}
