package com.infuse.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infuse.api.model.dto.CustomerDTO;
import com.infuse.api.model.dto.OrderDTO;
import com.infuse.api.model.dto.request.OrderRequestDTO;
import com.infuse.api.model.dto.response.OrderResponseDTO;
import com.infuse.api.service.OrderService;

class OrderControllerTest {

	private OrderController controller;

	@Mock
	private OrderService service;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		controller = new OrderController(service);
	}

	@Test
	void testGetOrders() throws Exception {
		List<OrderResponseDTO> mockResponse = mockListOrders();
		when(service.getOrders(anyLong(), any(), anyString(), anyInt(), anyLong())).thenReturn(mockResponse);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order");
		MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testSaveOrders() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<OrderRequestDTO> mockRequest = Arrays.asList(new OrderRequestDTO(1L, 1L, null, "TESTE", BigDecimal.TEN, 5));
		List<OrderResponseDTO> mockResponse = mockListOrders();
		when(service.save(mockRequest)).thenReturn(mockResponse);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(mockRequest));
		MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void testShouldBadRequestSaveOrders() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<OrderRequestDTO> mockRequest = Arrays.asList(new OrderRequestDTO(1L, 1L, LocalDateTime.now(), "TESTE", BigDecimal.TEN, 5));
		List<OrderResponseDTO> mockResponse = mockListOrders();
		when(service.save(mockRequest)).thenReturn(mockResponse);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(mockRequest));
		MockMvcBuilders.standaloneSetup(controller).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	private List<OrderResponseDTO> mockListOrders() {
		return Arrays.asList(new OrderResponseDTO(
				1L,
				new CustomerDTO(1L, "TESTE", "22222222222"),
				new OrderDTO(1L, 1L, LocalDateTime.now(), "teste", BigDecimal.TEN, 5),
				BigDecimal.TEN, 
				Boolean.FALSE));
	}

}
