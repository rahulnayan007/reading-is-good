package com.readingisgood.warehouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> addBooks(List<Book> books) {
		return bookRepository.saveAll(books);
	}

	public Book updateBook(Book book) {
		Optional<Book> optional = bookRepository.findById(book.getId());
		Book updatedBook = null;
		if(optional.isPresent()) {
			Book bookToUpdate = optional.get();
			bookToUpdate.setIsbn(book.getIsbn());
			bookToUpdate.setTitle(book.getTitle());
			bookToUpdate.setEdition(book.getEdition());
			bookToUpdate.setAuthors(book.getAuthors());
			bookToUpdate.setPublication(book.getPublication());
			bookToUpdate.setPrice(book.getPrice());
			updatedBook = bookRepository.save(bookToUpdate);
		}
		return updatedBook;
	}

}
