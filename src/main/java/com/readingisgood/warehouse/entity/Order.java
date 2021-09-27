package com.readingisgood.warehouse.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.readingisgood.warehouse.util.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author rahul
 *
 */
@Data
@ToString
@EqualsAndHashCode
@Document(collection = "Order")
public class Order {

	@Id
	private String orderId;

	private String customerId;

	private List<String> bookIsbnList;

	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private Date orderDate;

	private String status;

	private int totalItems;

	private long total;

}
