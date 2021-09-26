package com.readingisgood.warehouse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.warehouse.entity.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	public Book findByIsbn(String isbn);
	
}
