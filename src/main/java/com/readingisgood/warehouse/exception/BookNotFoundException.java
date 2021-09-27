package com.readingisgood.warehouse.exception;

public class BookNotFoundException extends Throwable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2098374347732718993L;

	public BookNotFoundException(String message) {
		super(message);
	}
	
}
