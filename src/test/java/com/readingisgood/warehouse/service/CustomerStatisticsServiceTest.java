package com.readingisgood.warehouse.service;

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
import org.springframework.security.test.context.support.WithMockUser;

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
	@WithMockUser
	public void TestGetMonthlyOrderStats() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

		Order order = new Order();
		order.setTotalItems(2);
		order.setTotal(2370);
		order.setCustomerId("CUST123");
		order.setOrderDate(simpleDateFormat.parse("12/06/2021"));
		
		Order order2 = new Order();
		order2.setTotalItems(2);
		order2.setTotal(2370);
		order.setOrderDate(simpleDateFormat.parse("09/01/2021"));
		
		Order order3 = new Order();
		order3.setTotalItems(2);
		order3.setTotal(2370);
		order.setCustomerId("CUST123");
		order3.setOrderDate(simpleDateFormat.parse("08/11/2020"));
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		orders.add(order2);
		orders.add(order3);
		order.setCustomerId("CUST123");
		
		when(orderRepository.findByCustomerId("CUST123")).thenReturn(orders);
		StatisticsResponse response = customerStatisticsService.getMonthlyOrderStats("12345", "2021");
		assertNotNull(response);
		assertNotNull(response.getReport());
	}
	
}
