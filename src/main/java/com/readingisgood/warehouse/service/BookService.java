package com.readingisgood.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.exception.BookNotFoundException;
import com.readingisgood.warehouse.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book addBook(Book book) throws Exception {
		log.debug("addBook called.");
		return bookRepository.save(book);
	}

	public List<Book> addBooks(List<Book> books) throws Exception {
		return bookRepository.saveAll(books);
	}

	public Book updateBookStock(Book book) throws BookNotFoundException {
		log.debug("updateBookStock called.");
		Book bookToUpdate = bookRepository.findByIsbn(book.getIsbn());
		if(bookToUpdate==null) {
			throw new BookNotFoundException("Book not found.");
		}
		bookToUpdate.setStock(book.getStock());
		return bookRepository.save(bookToUpdate);
	}

}
