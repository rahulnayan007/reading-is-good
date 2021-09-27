package com.readingisgood.warehouse.util;

import org.springframework.stereotype.Component;

/**
 * 
 * @author rahul
 *
 */
@Component
public class Constants {

	public static final String DATE_FORMAT = "dd/MM/yy";
	
	public static final String QUERY_DATE_FORMAT = "dd-MM-yy";
	
	public static final String CREATED_STATUS = "created";

	public static final String CANCEL_STATUS = "cancelled";
	
	public static final String SUCCESS_MESSAGE = "success";
	
	public static final String SUCCESS_CODE = "1";
	
	public static final String FAILURE_MESSAGE = "failure";
	
	public static final String FAILURE_CODE = "0";
	
	public static final String START_LOG_STATEMENT = "{} called by user : {}";
	
	public static final String EXCEPTION_LOG_STATEMENT = "Exception in {} call by user : {}, {}";

	public static final String BOOK_OUT_OF_STOCK = "2";
	
	public static final String BOOK_NOT_FOUND = "3";
	
}
