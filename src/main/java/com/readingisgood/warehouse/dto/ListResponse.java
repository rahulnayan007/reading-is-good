package com.readingisgood.warehouse.dto;

import java.util.List;

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
public class ListResponse<T> {

	private List<T> data;

	private Status status;

}
