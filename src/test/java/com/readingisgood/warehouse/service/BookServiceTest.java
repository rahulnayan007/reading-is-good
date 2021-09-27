package com.readingisgood.warehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.exception.BookNotFoundException;
import com.readingisgood.warehouse.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	@WithMockUser
	public void TestAddBook() throws Exception {
		Book book = new Book();
		book.setIsbn("Test ISBN");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setStock(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		when(bookRepository.save(book)).thenReturn(book);
		Book response = bookService.addBook(book);
		assert(response).equals(book);
	}

	@Test
	@WithMockUser
	public void TestAddBooks() throws Exception {
		Book book = new Book();
		book.setIsbn("Test ISBN");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setStock(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		books.add(book);
		when(bookRepository.saveAll(books)).thenReturn(books);
		List<Book> response = bookService.addBooks(books);
		assertThat(response).isEqualTo(books);
	}

	@Test
	@WithMockUser
	public void TestUpdateBook() throws BookNotFoundException {
		Book book = new Book();
		book.setIsbn("Test ISBN");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setStock(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(book);
		when(bookRepository.save(book)).thenReturn(book);
		Book response = bookService.updateBookStock(book);
		assertThat(response).isEqualTo(book);
	}
	
	@Test
	@WithMockUser
	public void TestUpdateBookNotFoundException() throws BookNotFoundException {
		Book book = new Book();
		book.setIsbn("Test ISBN");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setStock(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(null);
		Assertions.assertThrows(Throwable.class, () -> bookService.updateBookStock(book));
	}
}
