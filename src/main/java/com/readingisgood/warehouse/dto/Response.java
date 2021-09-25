package com.readingisgood.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

	private T data;

	private Status status;

}
