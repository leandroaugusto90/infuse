package com.infuse.api.model.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.infuse.api.model.Customer;
import com.infuse.api.model.CustomerOrder;
import com.infuse.api.model.Order;
import com.infuse.api.model.dto.CustomerDTO;
import com.infuse.api.model.dto.request.OrderRequestDTO;
import com.infuse.api.model.dto.response.OrderResponseDTO;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

	@Mapping(source = "customerOrder.customer", target = "customer")
	@Mapping(source = "customerOrder.order", target = "order")
	OrderResponseDTO entityToDto(CustomerOrder customerOrder);
	
	Order requestToEntity(OrderRequestDTO request);
	
	@Mapping(source = "order", target = "order")
	OrderResponseDTO orderEntityToResponse(Order order);
	
	CustomerDTO customerEntityToDto(Customer customer);
}
