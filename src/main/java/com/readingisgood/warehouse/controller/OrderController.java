package com.readingisgood.warehouse.controller;

import java.util.Collections;
import java.util.Date;

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
import com.readingisgood.warehouse.exception.BookOutOfStockException;
import com.readingisgood.warehouse.service.OrderService;
import com.readingisgood.warehouse.util.Constants;
import com.readingisgood.warehouse.util.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserInfo userInfo;

	/**
	 * API to add one order at a time.
	 * 
	 * @param {@link Order}
	 * @return {@link Response<Order>}
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Order> createOrder(@RequestBody final Order order) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "createOrder", userInfo.getUserName());
			return new Response<Order>(orderService.saveOrder(order),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (BookOutOfStockException exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "createOrder", userInfo.getUserName(), exception.getMessage());
			return new Response<Order>(null, new Status(Constants.BOOK_OUT_OF_STOCK, exception.getMessage()));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "createOrder", userInfo.getUserName(), exception.getMessage());
			return new Response<Order>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

	/**
	 * API to get order by Id.
	 * 
	 * @param orderId
	 * @return {@link Response<Order>}
	 */
	@GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Order> getOrder(@PathVariable(value = "orderId") final String orderId) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "getOrder", userInfo.getUserName());
			return new Response<Order>(orderService.getOrderById(orderId),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			OrderController.log.error(Constants.EXCEPTION_LOG_STATEMENT, "getOrder", userInfo.getUserName(),
					exception.getMessage());
			return new Response<Order>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

	/**
	 * API to get orders within date range.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return {@link ListResponse<Order>}
	 */
	@GetMapping(value = "/startDate/{startDate}/endDate/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListResponse<Order> getordersWithinDateRange(
			@PathVariable(value = "startDate") @DateTimeFormat(pattern = Constants.QUERY_DATE_FORMAT) Date startDate,
			@PathVariable(value = "endDate") @DateTimeFormat(pattern = Constants.QUERY_DATE_FORMAT) Date endDate) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "getordersWithinDateRange", userInfo.getUserName());
			return new ListResponse<Order>(orderService.getOrdersWithDateRange(startDate, endDate),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "getordersWithinDateRange", userInfo.getUserName(),
					exception.getMessage());
			return new ListResponse<Order>(Collections.emptyList(),
					new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

	/**
	 * API to get all orders by customer Id.
	 * 
	 * @param customerId
	 * @return {@link ListResponse<Order>}
	 */
	@GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListResponse<Order> getOrdersByCustomerId(@PathVariable(value = "customerId") final String customerId) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "getOrdersByCustomerId", userInfo.getUserName());
			return new ListResponse<Order>(orderService.getOrdersByCustomerId(customerId),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "getOrdersByCustomerId", userInfo.getUserName(),
					exception.getMessage());
			return new ListResponse<Order>(Collections.emptyList(),
					new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}
}
