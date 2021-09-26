package com.readingisgood.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.StatisticsResponse;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.repository.OrderRepository;
import com.readingisgood.warehouse.util.Constants;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerStatisticsServiceTest {
	
	@InjectMocks
	private CustomerStatisticsService customerStatisticsService;
	
	@Mock
	private OrderRepository orderRepository;

	@Test
	public void TestGetMonthlyOrderStats() throws Exception {
		Order order = new Order();
		order.setTotalItems(2);
		order.setTotal(2370);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		order.setOrderDate(simpleDateFormat.parse("12/06/2021"));
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		
		when(orderRepository.findByCustomerId("12345")).thenReturn(orders);
		Response<StatisticsResponse> response = customerStatisticsService.getMonthlyOrderStats("12345", "2021");
		assertNotNull(response);
		assertNotNull(response.getData());
		assertNotNull(response.getData().getReport());
		assertNotNull(response.getStatus());
		assertEquals("1", response.getStatus().getCode());
	}
	
}
