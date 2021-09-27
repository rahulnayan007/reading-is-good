package com.readingisgood.warehouse.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.readingisgood.warehouse.entity.Order;

/**
 * 
 * @author rahul
 *
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	
	public Optional<Order> findByOrderId(String orderId);

	@Query("{orderDate : {$gt : ?0, $lt : ?1}}")
	public List<Order> getOrdersWithDateRange(Date startDate, Date endDate);
	
	public List<Order> findByCustomerId(String customerId);
	
}

