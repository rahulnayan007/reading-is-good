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
import com.readingisgood.warehouse.util.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

	@Autowired
	private CustomerStatisticsService customerStatisticsService;

	@Autowired
	private UserInfo userInfo;

	/**
	 * API to get monthly order statistics by customer id.
	 * 
	 * @param customerId
	 * @param year
	 * @return {@link Response<StatisticsResponse>}
	 */
	@GetMapping(value = "/monthly/customer/{id}/year/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<StatisticsResponse> getCustomerOrderStatistics(@PathVariable(value = "id") String customerId,
			@PathVariable(value = "year") String year) {
		try {
			StatisticsController.log.info(Constants.START_LOG_STATEMENT, "getCustomerOrderStatistics",
					userInfo.getUserName());
			return new Response<StatisticsResponse>(
					customerStatisticsService.getMonthlyOrderStats(customerId, year.trim()),
					new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		} catch (Exception exception) {
			StatisticsController.log.error(Constants.EXCEPTION_LOG_STATEMENT, "getCustomerOrderStatistics",
					userInfo.getUserName(), exception.getMessage());
			return new Response<StatisticsResponse>(null,
					new Status(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE));
		}
	}

}
