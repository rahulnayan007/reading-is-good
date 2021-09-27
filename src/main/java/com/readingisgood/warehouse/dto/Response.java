package com.readingisgood.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author rahul
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class Response<T> {

	private T data;

	private Status status;

}
