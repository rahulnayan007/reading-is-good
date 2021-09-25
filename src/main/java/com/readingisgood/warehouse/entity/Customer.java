package com.readingisgood.warehouse.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Document(collection = "Customer")
public class Customer {

	@Id
	private String id;
	
	private String customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String mobile;
	
	private String email;
	
	private List<Address> addresses;
	
}
