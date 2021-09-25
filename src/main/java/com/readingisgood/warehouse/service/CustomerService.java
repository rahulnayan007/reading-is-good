package com.readingisgood.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.entity.Customer;
import com.readingisgood.warehouse.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public List<Customer> addCustomers(List<Customer> customers) {
		return customerRepository.saveAll(customers);
	}
	
}
