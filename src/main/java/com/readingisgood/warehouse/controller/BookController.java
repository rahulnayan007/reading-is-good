package com.readingisgood.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.warehouse.dto.ListRequestDto;
import com.readingisgood.warehouse.dto.ListResponse;
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Book> addBook(@RequestBody final Book book) {
		try {
			return new Response<Book>(bookService.addBook(book), new Status("1", "success"));
		} catch (Exception exception) {
			return new Response<Book>(null, new Status("0", "failure"));
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/list")
	public ListResponse<Book> addBooks(@RequestBody final ListRequestDto<Book> books) {
		try {
			return new ListResponse<Book>(bookService.addBooks(books.getData()), new Status("1", "success"));
		} catch (Exception exception) {
			return new ListResponse<Book>(null, new Status("0", "failure"));
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Book> updateBook(@RequestBody final Book book) {
		try {
			return new Response<Book>(bookService.updateBook(book), new Status("1", "success"));
		} catch (Exception exception) {
			return new Response<Book>(null, new Status("0", "failure"));
		}
	}
}
