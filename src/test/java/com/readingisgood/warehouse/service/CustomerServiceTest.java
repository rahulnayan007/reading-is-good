package com.readingisgood.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.readingisgood.warehouse.entity.Address;
import com.readingisgood.warehouse.entity.Customer;
import com.readingisgood.warehouse.repository.CustomerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void TestAddCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId("CUST123");
		customer.setFirstName("Rahul");
		customer.setLastName("Nayan");
		customer.setMobile("8764563456");
		customer.setEmail("rn@hotmail.com");
		customer.setAddresses(new ArrayList<Address>());
		
		when(customerRepository.save(ArgumentMatchers.any())).thenReturn(customer);
		Customer response = customerService.addCustomer(customer);
		assertNotNull(response);
		assertEquals("Rahul", response.getFirstName());
		assertEquals("Nayan", response.getLastName());
		assertEquals("CUST123", response.getCustomerId());
	}

	@Test
	public void TestAddCustomers() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId("CUST123");
		customer.setFirstName("Rahul");
		customer.setLastName("Nayan");
		customer.setMobile("8764563456");
		customer.setEmail("rn@hotmail.com");
		customer.setAddresses(new ArrayList<Address>());
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		customers.add(customer);
		
		when(customerRepository.saveAll(ArgumentMatchers.any())).thenReturn(customers);
		List<Customer> response = customerService.addCustomers(customers);
		assertNotNull(response);
		assertEquals(2, response.size());
		assertEquals("Rahul", response.get(0).getFirstName());
		assertEquals("Nayan", response.get(0).getLastName());
		assertEquals("CUST123", response.get(0).getCustomerId());
		assertEquals("Rahul", response.get(1).getFirstName());
		assertEquals("Nayan", response.get(1).getLastName());
		assertEquals("CUST123", response.get(1).getCustomerId());
	}

}
