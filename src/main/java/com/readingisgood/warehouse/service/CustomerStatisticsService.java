package com.readingisgood.warehouse.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Statistics;
import com.readingisgood.warehouse.dto.StatisticsResponse;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.repository.OrderRepository;
import com.readingisgood.warehouse.util.Constants;

@Service
public class CustomerStatisticsService {

	@Autowired
	private OrderRepository orderRepository;

	public Response<StatisticsResponse> getMonthlyOrderStats(String customerId, String year) {
		// Fetch all orders with the customerId 
		List<Order> orders = orderRepository.findByCustomerId(customerId);
		SimpleDateFormat monthNameFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
		StatisticsResponse statisticsResponse = new StatisticsResponse();
		Map<String, Statistics> report = statisticsResponse.getReport();
		if(null==report) {
			report = new HashMap<String, Statistics>();
		}
		
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
		for(Order order : orders) {
			if(year.equals(yearFormat.format(order.getOrderDate()))) {
				String month = monthNameFormat.format(order.getOrderDate());
				Statistics statistics = report.get(month);
				if(null==statistics) {
					statistics = new Statistics(month, 1, order.getTotalItems(), order.getTotal());
				} else {
					statistics.setTotalOrderCount(statistics.getTotalOrderCount()+1);
					statistics.setTotalBookCount(statistics.getTotalBookCount()+order.getTotalItems());
					statistics.setTotalPurchasedAmount(statistics.getTotalPurchasedAmount()+order.getTotal());
				}
				report.put(month, statistics);
			}
		}
		statisticsResponse.setReport(report);
		return new Response<StatisticsResponse>(statisticsResponse, new Status(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE));
	}

}
