package com.readingisgood.warehouse.dto;

import lombok.Data;

@Data
public class Statistics {

	private String month;
	
	private int totalOrderCount;
	
	private int totalBookCount;
	
	private Long totalPurchasedAmount;
	
	public Statistics(String month, int totalOrderCount, int totalBookCount, long totalPurchasedAmount) {
		this.month = month;
		this.totalOrderCount = totalOrderCount;
		this.totalBookCount = totalBookCount;
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
	
}
