package com.readingisgood.warehouse.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.warehouse.entity.Book;

/**
 * 
 * @author rahul
 *
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	public Book findByIsbn(String isbn);
	 
	public List<Book> findByIsbnIn(List<String> isbnList);
	
}
