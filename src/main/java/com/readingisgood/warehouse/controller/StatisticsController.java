package com.readingisgood.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.StatisticsResponse;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.service.CustomerStatisticsService;
import com.readingisgood.warehouse.util.Constants;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
	
	@Autowired
	private CustomerStatisticsService customerStatisticsService;

	@GetMapping(value = "/monthly/customer/{id}/year/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<StatisticsResponse> getCustomerOrderStatistics(@PathVariable(value = "id") String customerId, 
			@PathVariable(value = "year") String year) {
		try {
			return customerStatisticsService.getMonthlyOrderStats(customerId, year.trim());
		} catch(Exception exception) {
			return new Response<StatisticsResponse>(null, new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}
	
}
