package com.readingisgood.warehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void TestAddBook() {
		Book book = new Book();
		book.setId("TEST ID");
		book.setIsbn("Test ISBN");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setCount(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		
		when(bookRepository.save(book)).thenReturn(book);
		Book response = bookService.addBook(book);
		assert(response).equals(book);
	}

	@Test
	public void TestAddBooks() {
		Book book = new Book();
		book.setId("TEST ID");
		book.setIsbn("Test ISBN");
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
		books.add(book);
		
		when(bookRepository.saveAll(books)).thenReturn(books);
		List<Book> response = bookService.addBooks(books);
		assertThat(response).isEqualTo(books);
	}

	@Test
	public void updateBook() {
		Book book = new Book();
		book.setId("TEST ID");
		book.setIsbn("Test ISBN");
		book.setTitle("Test Title");
		book.setPublication("Test Pub");
		book.setEdition("2");
		book.setPrice(656L);
		book.setCount(10);
		List<String> authours = new ArrayList<String>();
		authours.add("Test Auth1");
		book.setAuthors(authours);
		book.setPrice(786L);
		Optional<Book> bookOptinal = Optional.of(book);
		
		when(bookRepository.findById(book.getId())).thenReturn(bookOptinal);
		when(bookRepository.save(book)).thenReturn(book);
		Book response = bookService.updateBook(book);
		assertThat(response).isEqualTo(book);
	}
}
