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

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Customer> addCustomer(@RequestBody final Customer customer) {
		try {
			return new Response<Customer>(customerService.addCustomer(customer), new Status("1", "success"));
		} catch(Exception exception) {
			return new Response<Customer>(null, new Status("0", "failure"));
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/list")
	public ListResponse<Customer> addCustomers(@RequestBody final ListRequestDto<Customer> customers) {
		try {
			return new ListResponse<Customer>(customerService.addCustomers(customers.getData()), new Status("1", "success"));
		} catch(Exception exception) {
			return new ListResponse<Customer>(Collections.emptyList(), new Status("0", "failure"));
		}
	}

}
