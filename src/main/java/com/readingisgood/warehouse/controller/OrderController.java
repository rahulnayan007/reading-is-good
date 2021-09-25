package com.readingisgood.warehouse.controller;

import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.warehouse.dto.ListResponse;
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.service.OrderService;
import com.readingisgood.warehouse.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService; 
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Order> createOrder(@RequestBody final Order order) {
		LOGGER.info("createOrder called.");
		
		try {
			return orderService.saveOrder(order);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return new Response<Order>(null, new Status("0", "failure"));
		}
	}
	
	@GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Order> getOrder(@PathVariable(value = "orderId") final String orderId) {
		LOGGER.info("getOrder called.");
		
		try {
			return orderService.getOrderById(orderId);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return new Response<Order>(null, new Status("0", "failure"));
		}
	}

	@GetMapping(value = "/startDate/{startDate}/endDate/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListResponse<Order> getordersWithinDateRange(@PathVariable(value = "startDate") @DateTimeFormat(pattern = Constants.QUERY_DATE_FORMAT) Date 
			startDate, @PathVariable(value = "endDate") @DateTimeFormat(pattern = Constants.QUERY_DATE_FORMAT) Date endDate) {
		LOGGER.info("getordersWithinDateRange called.");
		
		try {
			 return orderService.getOrdersWithDateRange(startDate, endDate);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return new ListResponse<Order>(Collections.emptyList(), new Status("0", "failure"));
		}
	}
	
	@GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListResponse<Order> getOrdersByCustomerId(@PathVariable(value = "customerId") final String customerId) {
		LOGGER.info("getOrdersByCustomerId called.");
		
		 try {
			return orderService.getOrdersByCustomerId(customerId);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return new ListResponse<Order>(Collections.emptyList(), new Status("0", "failure"));
		}
	}
}
