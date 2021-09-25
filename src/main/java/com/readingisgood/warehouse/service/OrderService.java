package com.readingisgood.warehouse.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.dto.ListResponse;
import com.readingisgood.warehouse.dto.Response;
import com.readingisgood.warehouse.dto.Status;
import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.repository.BookRepository;
import com.readingisgood.warehouse.repository.OrderRepository;
import com.readingisgood.warehouse.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public Response<Order> saveOrder(Order order) throws Exception {
		LOGGER.info("saveOrder called.");
		
		Order result = null;
		if(Constants.CREATED_STATUS.equals(order.getStatus())) {
			result = createOrder(order);
		} else if(Constants.CANCEL_STATUS.equals(order.getStatus())) {
			result = cancelOrder(order);
		}
		return new Response<Order>(result, new Status("1", "success"));
	}
	
	private synchronized Order createOrder(Order order) throws Exception {
		LOGGER.debug("createOrder called.");
		
		Iterable<Book> books = bookRepository.findAllById(order.getBookIsbnList());
		List<Book> updated = new ArrayList<Book>();
		List<String> unavailable = new ArrayList<String>();
		for(Book book: books) {
			if(book.getCount()>0) {
				book.setCount(book.getCount()-1);
				updated.add(book);
			} else {
				unavailable.add(book.getIsbn());
			}
		}
		
		if(unavailable.isEmpty()) {
			bookRepository.saveAll(updated);
			return orderRepository.save(order);
		}
		else {
			throw new Exception("Book(s) not available: ".concat(unavailable.toString()));
		}
	}
	
	private synchronized Order cancelOrder(Order order) throws Exception {
		LOGGER.debug("cancelOrder called.");
		
		Optional<Order> optional = orderRepository.findById(order.getId());
		Order cancelOrder = null;
		if(optional.isPresent()) {
			cancelOrder = optional.get();
			cancelOrder.setStatus(Constants.CANCEL_STATUS);
		}
		return orderRepository.save(cancelOrder);
	}

	public Response<Order> getOrderById(String orderId) throws Exception {
		LOGGER.debug("getOrderById called.");
		
		Optional<Order> order = orderRepository.findByOrderId(orderId);
		if(order.isEmpty())
			return null;
		return new Response<Order>(order.get(), new Status("1", "success"));
	}
	
	public ListResponse<Order> getOrdersWithDateRange(Date startDate, Date endDate) throws Exception {
		LOGGER.debug("getOrdersWithDateRange called.");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		return new ListResponse<Order>(orderRepository
				.getOrdersWithDateRange(simpleDateFormat.parse(simpleDateFormat.format(startDate)),
						simpleDateFormat.parse(simpleDateFormat.format(endDate))), new Status("1", "success"));
	}

	public ListResponse<Order> getOrdersByCustomerId(String customerId) throws Exception {
		LOGGER.debug("getOrdersByCustomerId called.");
		
		return new ListResponse<Order>(orderRepository.findByCustomerId(customerId), new Status("1", "success"));
	}
}
