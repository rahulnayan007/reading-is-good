package com.readingisgood.warehouse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Document(collection = "Address")
public class Address {
	
	private String addressId;

	private String addressLine1;
	
	private String addressLine2;
	
	private String zipcode;
	
	private String city;
	
	private String state;
	
	private String type;
	
}
