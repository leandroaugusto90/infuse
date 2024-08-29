package com.infuse.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infuse.api.model.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{

	@Query("SELECT co FROM CustomerOrder co WHERE "
			+ " (:controlNumber IS NULL OR co.order.controlNumber  = :controlNumber) AND "
			+ " (:registrationDate IS NULL OR DATE_FORMAT(co.order.registrationDate,'%Y-%m-%d') = DATE_FORMAT(:registrationDate,'%Y-%m-%d')) AND "
			+ " (:productName IS NULL OR co.order.productName LIKE CONCAT('%',:productName,'%')) AND "
			+ " (:productQuantity IS NULL OR co.order.productQuantity = :productQuantity) AND "
			+ " (:customerCode IS NULL OR co.customer.id = :customerCode)")
	List<CustomerOrder> getOrders(@Param("controlNumber") Long controlNumber, 
						  @Param("registrationDate") LocalDate registrationDate, 
						  @Param("productName") String productName,
						  @Param("productQuantity") Integer productQuantity,
						  @Param("customerCode") Long customerCode);

}
 