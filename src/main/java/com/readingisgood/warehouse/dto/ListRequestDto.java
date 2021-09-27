package com.readingisgood.warehouse.dto;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author rahul
 *
 * @param <T>
 */
@Data
public class ListRequestDto<T> {

	private List<T> data;
	
}
