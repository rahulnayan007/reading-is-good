package com.readingisgood.warehouse.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class StatisticsResponse {

	private Map<String, Statistics> report = new HashMap<String, Statistics>();
	
}
