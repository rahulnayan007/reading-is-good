package com.readingisgood.warehouse.exception;

/**
 * 
 * @author rahul
 *
 */
public class BookOutOfStockException extends Throwable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5306147399165788598L;

	public BookOutOfStockException(String message) {
		super(message);
	}

}
