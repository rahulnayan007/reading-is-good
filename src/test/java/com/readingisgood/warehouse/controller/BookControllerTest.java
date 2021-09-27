package com.readingisgood.warehouse.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.exception.BookNotFoundException;
import com.readingisgood.warehouse.service.BookService;
import com.readingisgood.warehouse.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	@WithMockUser
	public final void TestAddBook() throws Exception {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setTitle("Ikigai - The Japanese Secret To A Long And Happy Life");
		book.setEdition("1");
		book.setAuthors(Arrays.asList("Hector Garcia","Francesc Miralles","Heather Cleary"));
		book.setPublication("Penguin Books");
		book.setPrice(250L);
		book.setStock(200);
		String json = mapper.writeValueAsString(book);
		Mockito.when(bookService.addBook(book)).thenReturn(book);
		mockMvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data.isbn", Matchers.equalTo(book.getIsbn())));
	}
	
	@Test
	@WithMockUser
	public final void TestAddBookException() throws Exception {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setTitle("Ikigai - The Japanese Secret To A Long And Happy Life");
		book.setEdition("1");
		book.setAuthors(Arrays.asList("Hector Garcia","Francesc Miralles","Heather Cleary"));
		book.setPublication("Penguin Books");
		book.setPrice(250L);
		book.setStock(200);
		String json = mapper.writeValueAsString(book);
		Mockito.when(bookService.addBook(book)).thenThrow(new Exception("Exception."));
		mockMvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}
	
	@Test
	@WithMockUser
	public final void TestAddBooks() throws Exception {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setTitle("Ikigai - The Japanese Secret To A Long And Happy Life");
		book.setEdition("1");
		book.setAuthors(Arrays.asList("Hector Garcia","Francesc Miralles","Heather Cleary"));
		book.setPublication("Penguin Books");
		book.setPrice(250L);
		book.setStock(200);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		books.add(book);
		ListRequestDto<Book> request = new ListRequestDto<Book>();
		request.setData(books);
		String json = mapper.writeValueAsString(request);
		Mockito.when(bookService.addBooks(ArgumentMatchers.any())).thenReturn(books);
		mockMvc.perform(post("/api/book/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.SUCCESS_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(ListResponse.class)))
				.andExpect(jsonPath("$.data[0].isbn", Matchers.equalTo(book.getIsbn())));
	}
	
	@Test
	@WithMockUser
	public final void TestAddBooksException() throws Exception {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setTitle("Ikigai - The Japanese Secret To A Long And Happy Life");
		book.setEdition("1");
		book.setAuthors(Arrays.asList("Hector Garcia","Francesc Miralles","Heather Cleary"));
		book.setPublication("Penguin Books");
		book.setPrice(250L);
		book.setStock(200);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		books.add(book);
		ListRequestDto<Book> request = new ListRequestDto<Book>();
		request.setData(books);
		String json = mapper.writeValueAsString(request);
		Mockito.when(bookService.addBooks(ArgumentMatchers.any())).thenThrow(new NullPointerException());
		mockMvc.perform(post("/api/book/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}

	@Test
	@WithMockUser
	public final void TestUpdateBookStock() throws Exception, BookNotFoundException {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setTitle("Ikigai - The Japanese Secret To A Long And Happy Life");
		book.setEdition("1");
		book.setAuthors(Arrays.asList("Hector Garcia","Francesc Miralles","Heather Cleary"));
		book.setPublication("Penguin Books");
		book.setPrice(250L);
		book.setStock(80);
		String json = mapper.writeValueAsString(book);
		Mockito.when(bookService.updateBookStock(book)).thenReturn(book);
		mockMvc.perform(patch("/api/book").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo("1")))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data.isbn", Matchers.equalTo("9781786330895")));
	}
	
	@Test
	@WithMockUser
	public final void TestUpdateBookStockBookNotFound() throws Exception, BookNotFoundException {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setStock(40);
		String json = mapper.writeValueAsString(book);
		Mockito.when(bookService.updateBookStock(book)).thenThrow(new BookNotFoundException("Book not found."));
		mockMvc.perform(patch("/api/book").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.BOOK_NOT_FOUND)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}
	
	@Test
	@WithMockUser
	public final void TestUpdateBookStockException() throws Exception, BookNotFoundException {
		Book book = new Book();
		book.setIsbn("9781786330895");
		book.setStock(40);
		String json = mapper.writeValueAsString(book);
		Mockito.when(bookService.updateBookStock(book)).thenThrow(new NullPointerException());
		mockMvc.perform(patch("/api/book").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status.code", Matchers.equalTo(Constants.FAILURE_CODE)))
				.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
				.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}
	
}
