package com.readingisgood.warehouse.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readingisgood.warehouse.entity.Book;
import com.readingisgood.warehouse.entity.Order;
import com.readingisgood.warehouse.exception.BookOutOfStockException;
import com.readingisgood.warehouse.repository.BookRepository;
import com.readingisgood.warehouse.repository.OrderRepository;
import com.readingisgood.warehouse.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Persist Order based on status field in the request.
	 * 
	 * @param order
	 * @return Response<Order>
	 * @throws Exception
	 * @throws BookOutOfStockException
	 */
	public Order saveOrder(Order order) throws Exception, BookOutOfStockException {
		log.debug("saveOrder called.");
		Order result = null;
		if (Constants.CREATED_STATUS.equals(order.getStatus())) {
			result = createOrder(order);
		} else if (Constants.CANCEL_STATUS.equals(order.getStatus())) {
			result = cancelOrder(order);
		}
		return result;
	}

	private Order createOrder(Order order) throws Exception, BookOutOfStockException {
		log.debug("createOrder called.");
		Order createdOrder = null;
		Lock lock = new ReentrantLock();
		lock.lock();

		List<Book> booksToDecrementStock = new ArrayList<Book>();
		StringBuffer outofStockBooks = new StringBuffer();
		List<Book> books = bookRepository.findByIsbnIn(order.getBookIsbnList());
		for (Book book : books) {
			if (book.getStock() < 1) {
				if (outofStockBooks.length() > 0) {
					outofStockBooks.append(", ");
				}
				outofStockBooks.append(book.getIsbn());
			}
			if (book.getStock() >= 1) {
				book.setStock(book.getStock() - 1);
				booksToDecrementStock.add(book);
			}
			if (outofStockBooks.length() > 0) {
				throw new BookOutOfStockException("Out of stock: ".concat(outofStockBooks.toString()));
			}
		}
		if (!booksToDecrementStock.isEmpty()) {
			bookRepository.saveAll(booksToDecrementStock);
			createdOrder = orderRepository.save(order);
		}
		lock.unlock();
		return createdOrder;
	}

	private Order cancelOrder(Order order) throws Exception {
		log.debug("cancelOrder called.");
		Lock lock = new ReentrantLock();
		lock.lock();

		Optional<Order> optional = orderRepository.findByOrderId(order.getOrderId());
		Order orderToCancel = null;
		if (optional.isPresent()) {
			orderToCancel = optional.get();
			orderToCancel.setStatus(Constants.CANCEL_STATUS);
		}
		List<Book> booksToUpdate = bookRepository.findByIsbnIn(orderToCancel.getBookIsbnList());
		for (Book book : booksToUpdate) {
			book.setStock(book.getStock() + 1);
		}
		bookRepository.saveAll(booksToUpdate);
		Order cancelledOrder = orderRepository.save(orderToCancel);
		lock.unlock();
		return cancelledOrder;
	}

	public Order getOrderById(String orderId) throws Exception {
		log.debug("getOrderById called.");
		Optional<Order> order = orderRepository.findByOrderId(orderId);
		if (order.isEmpty())
			return null;
		return order.get();
	}

	public List<Order> getOrdersWithDateRange(Date startDate, Date endDate) throws Exception {
		log.debug("getOrdersWithDateRange called.");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		return orderRepository.getOrdersWithDateRange(simpleDateFormat.parse(simpleDateFormat.format(startDate)),
				simpleDateFormat.parse(simpleDateFormat.format(endDate)));
	}

	public List<Order> getOrdersByCustomerId(String customerId) throws Exception {
		log.debug("getOrdersByCustomerId called.");
		return orderRepository.findByCustomerId(customerId);
	}
}
