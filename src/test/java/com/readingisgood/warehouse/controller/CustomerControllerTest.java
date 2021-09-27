package com.readingisgood.warehouse.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.warehouse.dto.ListRequestDto;
import com.readingisgood.warehouse.dto.ListResponse;
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.entity.Address;
import com.readingisgood.warehouse.entity.Customer;
import com.readingisgood.warehouse.service.CustomerService;
import com.readingisgood.warehouse.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	@WithMockUser
	public final void TestAddCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId("CUST123");
		customer.setFirstName("Rahul");
		customer.setLastName("Nayan");
		customer.setMobile("8764563456");
		customer.setEmail("rn@hotmail.com");
		customer.setAddresses(new ArrayList<Address>());
		String json = mapper.writeValueAsString(customer);
		Mockito.when(customerService.addCustomer(customer)).thenReturn(customer);
		mockMvc.perform(post("/api/customer").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data.firstName", Matchers.equalTo("Rahul")));
	}

	@Test
	@WithMockUser
	public final void TestAddCustomerException() throws Exception {
		String json = mapper.writeValueAsString(new NullPointerException("Unit testing exception"));
		Mockito.when(customerService.addCustomer(ArgumentMatchers.any()))
				.thenThrow(new NullPointerException("Unit testing exception"));
		mockMvc.perform(post("/api/customer").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}

	@Test
	@WithMockUser
	public final void TestAddCustomers() throws Exception {
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
		ListRequestDto<Customer> request = new ListRequestDto<Customer>();
		request.setData(customers);
		String json = mapper.writeValueAsString(request);
		Mockito.when(customerService.addCustomers(ArgumentMatchers.any())).thenReturn(customers);
		mockMvc.perform(post("/api/customer/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(ListResponse.class)))
				.andExpect(jsonPath("$.data[0].firstName", Matchers.equalTo("Rahul")));
	}

	@Test
	@WithMockUser
	public final void TestAddCustomersException() throws Exception {
		String json = mapper.writeValueAsString(new NullPointerException("Unit testing exception"));
		Mockito.when(customerService.addCustomers(ArgumentMatchers.any()))
				.thenThrow(new NullPointerException("Unit testing exception"));
		mockMvc.perform(post("/api/customer/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(ListResponse.class)))
				.andExpect(jsonPath("$.data", Matchers.emptyIterable()));
	}

}
