package com.readingisgood.warehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.exception.BookOutOfStockException;
import com.readingisgood.warehouse.repository.BookRepository;
import com.readingisgood.warehouse.repository.OrderRepository;
import com.readingisgood.warehouse.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private BookRepository bookRepository;

	@Test
	@WithMockUser
	final void TestCreateOrder() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTID");
		order.setCustomerId("TESTCUSTID");
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setStatus(Constants.CREATED_STATUS);
		List<String> isbns = new ArrayList<String>();
		isbns.add("98376563543");
		isbns.add("93876736534");
		order.setBookIsbnList(isbns);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		order.setOrderDate(simpleDateFormat.parse("12/06/2021"));

		Book book = new Book();
		book.setIsbn("98376563543");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setStock(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);

		Book book2 = new Book();
		book2.setIsbn("93876736534");
		book2.setTitle("Test Title");
		book2.setPublication("Test Pub");
		book2.setEdition("2");
		book2.setPrice(656L);
		book2.setStock(0);
		List<String> authours2 = new ArrayList<String>();
		authours.add("Test Auth2");
		book.setAuthors(authours2);
		book.setPrice(906L);
		List<Book> books = new ArrayList<Book>();
		books.add(book);

		when(bookRepository.findByIsbnIn(ArgumentMatchers.any())).thenReturn(books);
		when(bookRepository.saveAll(ArgumentMatchers.any())).thenReturn(books);
		when(orderRepository.save(order)).thenReturn(order);
		Order response = orderService.saveOrder(order);
		assertThat(response).isNotNull();
		assertEquals("TESTID", response.getOrderId());
		assertEquals("TESTCUSTID", response.getCustomerId());
	}

	@Test
	@WithMockUser
	final void TestCancelOrder() throws Exception, BookOutOfStockException {
		Order order = new Order();
		order.setOrderId("TESTID");
		order.setCustomerId("TESTCUSTID");
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setStatus(Constants.CANCEL_STATUS);
		List<String> isbns = new ArrayList<String>();
		isbns.add("98376563543");
		isbns.add("93876736534");
		order.setBookIsbnList(isbns);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		order.setOrderDate(simpleDateFormat.parse("12/06/2021"));

		Book book = new Book();
		book.setIsbn("98376563543");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setStock(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);

		Book book2 = new Book();
		book2.setIsbn("93876736534");
		book2.setTitle("Test Title");
		book2.setPublication("Test Pub");
		book2.setEdition("2");
		book2.setPrice(656L);
		book2.setStock(0);
		List<String> authours2 = new ArrayList<String>();
		authours.add("Test Auth2");
		book.setAuthors(authours2);
		book.setPrice(906L);
		List<Book> books = new ArrayList<Book>();
		books.add(book);

		Optional<Order> optional = Optional.of(order);
		when(orderRepository.findByOrderId(ArgumentMatchers.any())).thenReturn(optional);
		when(bookRepository.findByIsbnIn(ArgumentMatchers.any())).thenReturn(books);
		when(bookRepository.saveAll(ArgumentMatchers.any())).thenReturn(books);
		when(orderRepository.save(order)).thenReturn(order);
		Order response = orderService.saveOrder(order);
		assertThat(response).isNotNull();
		assertEquals("TESTID", response.getOrderId());
		assertEquals("TESTCUSTID", response.getCustomerId());
	}

	@Test
	@WithMockUser
	final void TestGetOrderById() throws Exception {
		Order order = new Order();
		order.setOrderId("TESTID");
		order.setCustomerId("TESTCUSTID");
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setStatus(Constants.CREATED_STATUS);
		Optional<Order> optional = Optional.of(order);
		when(orderRepository.findByOrderId(ArgumentMatchers.any())).thenReturn(optional);
		Order response = orderService.getOrderById(order.getOrderId());
		assertThat(response).isNotNull();
		assertEquals("TESTID", response.getOrderId());
		assertEquals("TESTCUSTID", response.getCustomerId());
	}
	
	@Test
	@WithMockUser
	final void TestGetOrderByIdEmpty() throws Exception {
		Order order = new Order();
		order.setOrderId("TESTID");
		order.setCustomerId("TESTCUSTID");
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setStatus(Constants.CREATED_STATUS);
		Optional<Order> optional = Optional.empty();
		when(orderRepository.findByOrderId(ArgumentMatchers.any())).thenReturn(optional);
		Order response = orderService.getOrderById(order.getOrderId());
		assertThat(response).isNull();
	}
	
	@Test
	@WithMockUser
	final void TestGetOrdersWithDateRange() throws Exception {
		Order order = new Order();
		order.setOrderId("TESTID");
		order.setCustomerId("TESTCUSTID");
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setStatus(Constants.CREATED_STATUS);
		
		Order order1 = new Order();
		order1.setOrderId("TESTID");
		order1.setCustomerId("TESTCUSTID");
		order1.setTotalItems(2);
		order1.setTotal(2370);
		order1.setStatus(Constants.CREATED_STATUS);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		orders.add(order1);
		when(orderRepository.getOrdersWithDateRange(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(orders);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		List<Order> response = orderService.getOrdersWithDateRange(simpleDateFormat.parse("12/05/20"), simpleDateFormat.parse("10/10/21"));
		Assertions.assertNotNull(response);
		assertEquals("TESTID", response.get(0).getOrderId());
		assertEquals("TESTCUSTID", response.get(0).getCustomerId());
	}
	
	@Test
	@WithMockUser
	final void TestGetOrdersByCustomerId() throws Exception {
		Order order = new Order();
		order.setOrderId("TESTID");
		order.setCustomerId("TESTCUSTID");
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setStatus(Constants.CREATED_STATUS);
		
		Order order1 = new Order();
		order1.setOrderId("TESTID");
		order1.setCustomerId("TESTCUSTID");
		order1.setTotalItems(2);
		order1.setTotal(2370);
		order1.setStatus(Constants.CREATED_STATUS);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		orders.add(order1);
		when(orderRepository.findByCustomerId(ArgumentMatchers.any())).thenReturn(orders);
		List<Order> response = orderService.getOrdersByCustomerId("TESTCUSTID");
		Assertions.assertNotNull(response);
		assertEquals("TESTID", response.get(0).getOrderId());
		assertEquals("TESTCUSTID", response.get(1).getCustomerId());
	}
}
