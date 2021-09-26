package com.readingisgood.warehouse.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Statistics;
import com.readingisgood.warehouse.dto.StatisticsResponse;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.service.CustomerStatisticsService;
import com.readingisgood.warehouse.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerStatisticsService customerStatisticsService;
	
	@Test
	public final void TestGetCustomerOrderStatistics() throws Exception {
		
		Statistics statistics = new Statistics("Jan", 1, 2, 399);
		Map<String, Statistics> report = new HashMap<String, Statistics>();
		report.put(statistics.getMonth(), statistics);
		StatisticsResponse statisticsResponse = new StatisticsResponse();
		statisticsResponse.setReport(report);
		Response<StatisticsResponse> response = new Response<StatisticsResponse>(
				statisticsResponse, new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
		
		Mockito.when(customerStatisticsService.getMonthlyOrderStats("12345", "2021")).thenReturn(response);
		mockMvc.perform(get("/api/statistics/monthly/customer/12345/year/2021"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
			.andExpect(jsonPath("$.status.code", Matchers.equalTo("1")))
			.andExpect(jsonPath("$.data.report.Jan.totalOrderCount", Matchers.equalTo(1)))
			.andExpect(jsonPath("$.data.report.Jan.totalBookCount", Matchers.equalTo(2)))
			.andExpect(jsonPath("$.data.report.Jan.totalPurchasedAmount", Matchers.equalTo(399)));
	}
	
	@Test
	public final void TestGetCustomerOrderStatisticsException() throws Exception {
		Mockito.when(customerStatisticsService.getMonthlyOrderStats("12345", "2021")).thenThrow(new NullPointerException("Unit Test Exception"));
		mockMvc.perform(get("/api/statistics/monthly/customer/12345/year/2021"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.notNullValue(Response.class)))
			.andExpect(jsonPath("$.status.code", Matchers.equalTo("0")))
			.andExpect(jsonPath("$.data", Matchers.nullValue()));
	}
	
}
