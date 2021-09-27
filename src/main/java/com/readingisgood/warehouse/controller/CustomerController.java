package com.readingisgood.warehouse.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.warehouse.dto.ListRequestDto;
import com.readingisgood.warehouse.dto.ListResponse;
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.entity.Customer;
import com.readingisgood.warehouse.service.CustomerService;
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
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserInfo userInfo;

	/**
	 * API to add one customer at a time.
	 * @param customer
	 * @return Response<Customer>
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Customer> addCustomer(@RequestBody final Customer customer) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "addCustomer", userInfo.getUserName());
			return new Response<Customer>(customerService.addCustomer(customer),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "addCustomer", userInfo.getUserName(), exception.getMessage());
			return new Response<Customer>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

	/**
	 * API to add multiple customers at a time.
	 * @param customers
	 * @return ListResponse<Customer>
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/list")
	public ListResponse<Customer> addCustomers(@RequestBody final ListRequestDto<Customer> customers) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "addCustomers", userInfo.getUserName());
			return new ListResponse<Customer>(customerService.addCustomers(customers.getData()),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "addCustomers", userInfo.getUserName(),
					exception.getMessage());
			return new ListResponse<Customer>(Collections.emptyList(),
					new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

}
