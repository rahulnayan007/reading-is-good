package com.readingisgood.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.entity.Customer;
import com.readingisgood.warehouse.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer) throws Exception {
		log.debug("addCustomer called.");
		return customerRepository.save(customer);
	}
	
	public List<Customer> addCustomers(List<Customer> customers) throws Exception {
		log.debug("addCustomers called.");
		return customerRepository.saveAll(customers);
	}
	
}
