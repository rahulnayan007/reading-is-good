package com.readingisgood.warehouse.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author rahul
 *
 */
@Data
@ToString
@EqualsAndHashCode
@Document(collection = "Book")
public class Book {

	@Id
	private String isbn;

	private String title;

	private String edition;

	private List<String> authors;

	private String publication;

	private Long price;

	private int stock;

}
