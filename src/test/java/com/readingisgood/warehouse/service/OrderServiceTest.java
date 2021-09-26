package com.readingisgood.warehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.entity.Order;
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
	final void TestCreateOrder() throws Exception {
		Order order = new Order();
		order.setId("765645");
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
		book.setId("TEST ID");
		book.setIsbn("98376563543");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setCount(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		
		when(bookRepository.findAllById(order.getBookIsbnList())).thenReturn(books);
		when(orderRepository.save(order)).thenReturn(order);
		Response<Order> response = orderService.saveOrder(order);
		assertThat(response).isNotNull();
		assertEquals("1", response.getStatus().getCode());
		assertEquals("765645", response.getData().getId());
	}
	
	
	@Test
	final void TestCancelOrder() throws Exception {
		Order order = new Order();
		order.setId("765645");
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
		book.setId("TEST ID");
		book.setIsbn("98376563543");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setCount(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		
		Book book2 = new Book();
		book2.setId("TEST ID");
		book2.setIsbn("93876736534");
		book2.setTitle("Test Title");
		book2.setPublication("Test Pub");
		book2.setEdition("2");
		book2.setPrice(656L);
		book2.setCount(0);
		List<String> authours2 = new ArrayList<String>();
		authours.add("Test Auth2");
		book.setAuthors(authours2);
		book.setPrice(906L);
		
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		
		when(bookRepository.findAllById(order.getBookIsbnList())).thenReturn(books);
		when(orderRepository.save(order)).thenReturn(order);
		Response<Order> response = orderService.saveOrder(order);
		assertThat(response).isNotNull();
		assertEquals("1", response.getStatus().getCode());
		assertEquals("765645", response.getData().getId());
	}

}
