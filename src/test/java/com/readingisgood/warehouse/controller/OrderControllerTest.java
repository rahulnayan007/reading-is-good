package com.readingisgood.warehouse.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.exception.BookOutOfStockException;
import com.readingisgood.warehouse.service.OrderService;
import com.readingisgood.warehouse.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService orderService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	@WithMockUser
	public final void TestCreateOrder() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		String json = mapper.writeValueAsString(order);
		Mockito.when(orderService.saveOrder(ArgumentMatchers.any())).thenReturn(order);
		mockMvc.perform(post("/api/order/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data.status", Matchers.equalTo(Constants.CREATED_STATUS)));
	}

	@Test
	@WithMockUser
	public final void TestCreateOrderBookOutOfStockException() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		String json = mapper.writeValueAsString(order);
		Mockito.when(orderService.saveOrder(ArgumentMatchers.any()))
				.thenThrow(new BookOutOfStockException("Book out of stock."));
		mockMvc.perform(post("/api/order/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.BOOK_OUT_OF_STOCK)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}

	@Test
	@WithMockUser
	public final void TestCreateOrderException() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		String json = mapper.writeValueAsString(order);
		Mockito.when(orderService.saveOrder(ArgumentMatchers.any())).thenThrow(new NullPointerException());
		mockMvc.perform(post("/api/order/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}

	@Test
	@WithMockUser
	public final void TestGetOrder() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		String json = mapper.writeValueAsString(order);
		Mockito.when(orderService.getOrderById(ArgumentMatchers.anyString())).thenReturn(order);
		mockMvc.perform(get("/api/order/TESTORDERID").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data.status", Matchers.equalTo(Constants.CREATED_STATUS)));
	}

	@Test
	@WithMockUser
	public final void TestGetOrderException() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		String json = mapper.writeValueAsString(order);
		Mockito.when(orderService.getOrderById(ArgumentMatchers.anyString())).thenThrow(new NullPointerException());
		mockMvc.perform(get("/api/order/TESTORDERID").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}

	@Test
	@WithMockUser
	public final void TestGetOrdersByCustomerId() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		List<Order> list = new ArrayList<Order>();
		list.add(order);
		String json = mapper.writeValueAsString(list);
		Mockito.when(orderService.getOrdersByCustomerId(ArgumentMatchers.anyString())).thenReturn(list);
		mockMvc.perform(get("/api/order/customer/TESTCUSTID").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data[0].status", Matchers.equalTo(Constants.CREATED_STATUS)));
	}

	@Test
	@WithMockUser
	public final void TestGetOrdersByCustomerIdException() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		String json = mapper.writeValueAsString(order);
		Mockito.when(orderService.getOrdersByCustomerId(ArgumentMatchers.anyString()))
				.thenThrow(new NullPointerException());
		mockMvc.perform(get("/api/order/customer/TESTCUSTID").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.emptyIterable()));
	}

	@Test
	@WithMockUser
	public final void TestGetordersWithinDateRange() throws Exception {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		String json = mapper.writeValueAsString(orderList);
		Mockito.when(orderService.getOrdersWithDateRange(ArgumentMatchers.any(), ArgumentMatchers.any()))
				.thenReturn(orderList);
		mockMvc.perform(get("/api/order/startDate/01-05-19/endDate/19-09-21").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data[0].status", Matchers.equalTo(Constants.CREATED_STATUS)));
	}

	@Test
	@WithMockUser
	public final void TestGetordersWithinDateRangeException() throws Exception {
		Order order = new Order();
		order.setOrderId("TESTORDERID");
		order.setCustomerId("TESTCUSTID");
		order.setBookIsbnList(Arrays.asList("764-987-9474-0", "874-9484-984-1"));
		order.setOrderDate(new Date());
		order.setTotal(1200);
		order.setTotalItems(2);
		order.setStatus(Constants.CREATED_STATUS);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		String json = mapper.writeValueAsString(orderList);
		Mockito.when(orderService.getOrdersWithDateRange(ArgumentMatchers.any(), ArgumentMatchers.any()))
				.thenThrow(new NullPointerException());
		mockMvc.perform(get("/api/order/startDate/01-05-19/endDate/19-09-21").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.emptyIterable()));
	}
}
