package com.infuse.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infuse.api.domain.Customer;
import com.infuse.api.domain.CustomerOrder;
import com.infuse.api.domain.Order;
import com.infuse.api.domain.dto.CustomerDTO;
import com.infuse.api.domain.dto.OrderDTO;
import com.infuse.api.domain.dto.request.OrderRequestDTO;
import com.infuse.api.domain.dto.response.OrderResponseDTO;
import com.infuse.api.domain.mapper.OrderMapper;
import com.infuse.api.repository.CustomerOrderRepository;
import com.infuse.api.repository.CustomerRepository;
import com.infuse.api.repository.OrderRepository;

class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl service;

	@Mock
	private CustomerOrderRepository customerOrderRepository;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private OrderMapper mapper;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		service = new OrderServiceImpl(customerOrderRepository, orderRepository, customerRepository, mapper);
	}

	@Test
	void testGetOrders() {
		when(customerOrderRepository.getOrders(anyLong(), any(), anyString(), anyInt(), anyLong()))
				.thenReturn(mockEntityListOrders());
		
		List<OrderResponseDTO> orders = service.getOrders(anyLong(), any(), anyString(), anyInt(), anyLong());
		assertNotNull(orders);
		assertEquals(1L, orders.size());
	}

	@Test
	void testSave() {
		when(orderRepository.save(any())).thenReturn(mockOrder());
		when(customerRepository.findByCustomerId(anyLong())).thenReturn(Optional.of(new Customer(1L)));
		when(mapper.requestToEntity(any())).thenReturn(new Order(1L));
		when(mapper.orderEntityToResponse(any())).thenReturn(mockOrderResponse());
		when(mapper.customerEntityToDto(any())).thenReturn(new CustomerDTO(1L));
		
		List<OrderResponseDTO> orders = service.save(mockRequestListOrders());
		assertNotNull(orders);
		assertEquals(1L, orders.size());
	}

	private List<CustomerOrder> mockEntityListOrders() {
		return Arrays.asList(new CustomerOrder(1L,
				new Customer(1L, "TESTE", "22222222222"),
				new Order(1L, new CustomerOrder(1L, 
						new Customer(1L, "TESTE", "22222222222"), 
						new Order(1L, new CustomerOrder(), 1L, LocalDateTime.now(), "teste", BigDecimal.TEN, 5), 
						BigDecimal.TEN, Boolean.FALSE), 1L, LocalDateTime.now(), "teste", BigDecimal.TEN, 5),
				BigDecimal.TEN, Boolean.FALSE));
	}
	
	private List<OrderRequestDTO> mockRequestListOrders() {
		return  Arrays.asList(new OrderRequestDTO(1L, 1L, null, "TESTE", BigDecimal.TEN, 5));
	}
	
	private Order mockOrder() {
		return new Order(1L, 
				new CustomerOrder(1L, 
						new Customer(1L, "TESTE", "22222222222"), 
						new Order(1L, new CustomerOrder(), 1L, LocalDateTime.now(), "teste", BigDecimal.TEN, 5), 
						BigDecimal.TEN, Boolean.FALSE), 
				1L, 
				LocalDateTime.now(), 
				"TESTE",
				BigDecimal.TEN,
				5);
	}
	
	private OrderResponseDTO mockOrderResponse() {
		return new OrderResponseDTO(1L,
				new CustomerDTO(1L, "TESTE", "22222222222"),
				new OrderDTO(1L, 1L, LocalDateTime.now(), "teste", BigDecimal.TEN, 5),
				BigDecimal.TEN, Boolean.FALSE);
	}
	
}
