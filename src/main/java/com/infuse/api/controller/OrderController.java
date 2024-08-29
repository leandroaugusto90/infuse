package com.infuse.api.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.infuse.api.domain.dto.request.OrderRequestDTO;
import com.infuse.api.domain.dto.response.OrderResponseDTO;
import com.infuse.api.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	private final OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<OrderResponseDTO>> getOrders(@RequestParam(required = false) Long controlNumber,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registrationDate,
			@RequestParam(required = false) String productName, @RequestParam(required = false) Integer productQuantity,
			@RequestParam(required = false) Long customerCode) {
		return ResponseEntity 
				.ok(service.getOrders(controlNumber, registrationDate, productName, productQuantity, customerCode));
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<OrderResponseDTO>> saveOrders(@RequestBody @Valid List<OrderRequestDTO> request) {
		List<OrderResponseDTO> orderSaved = service.save(request);
		List<String> ids = orderSaved.stream().map(ordSav -> ordSav.getCustomerOrderId().toString())
				.collect(Collectors.toList());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(String.join(",", ids)).toUri();
		return ResponseEntity.created(uri).body(orderSaved);
	}
}
