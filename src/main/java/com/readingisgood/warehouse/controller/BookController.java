package com.readingisgood.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.warehouse.dto.ListRequestDto;
import com.readingisgood.warehouse.dto.ListResponse;
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.exception.BookNotFoundException;
import com.readingisgood.warehouse.service.BookService;
import com.readingisgood.warehouse.util.Constants;
import com.readingisgood.warehouse.util.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserInfo userInfo;

	/**
	 * API to add one book at a time.
	 * @param {@link Book}
	 * @return {@link Response<Book>}
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Book> addBook(@RequestBody final Book book) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "addBook", userInfo.getUserName());
			return new Response<Book>(bookService.addBook(book),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "addBook", userInfo.getUserName(), exception.getMessage());
			return new Response<Book>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

	/**
	 * API to add multiple books at a time.
	 * @param {@link List<Book>}
	 * @return {@link ListResponse<Book>}
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/list")
	public ListResponse<Book> addBooks(@RequestBody final ListRequestDto<Book> books) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "addBooks", userInfo.getUserName());
			return new ListResponse<Book>(bookService.addBooks(books.getData()),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "addBooks", userInfo.getUserName(), exception.getMessage());
			return new ListResponse<Book>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

	/**
	 * API to update book's stock
	 * @param {@link Book}
	 * @return {@link Response<Book>}
	 */
	@PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Book> updateBookStock(@RequestBody final Book book) {
		try {
			log.info(Constants.START_LOG_STATEMENT, "updateBook", userInfo.getUserName());
			return new Response<Book>(bookService.updateBookStock(book),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch(BookNotFoundException exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "updateBook", userInfo.getUserName(), exception.getMessage());
			return new Response<Book>(null, new Status(Constants.BOOK_NOT_FOUND, exception.getMessage()));
		} catch (Exception exception) {
			log.error(Constants.EXCEPTION_LOG_STATEMENT, "updateBook", userInfo.getUserName(), exception.getMessage());
			return new Response<Book>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}
}
