package com.readingisgood.warehouse.dto;

import java.util.List;

import lombok.Data;

@Data
public class ListRequestDto<T> {

	private List<T> data;
	
}
