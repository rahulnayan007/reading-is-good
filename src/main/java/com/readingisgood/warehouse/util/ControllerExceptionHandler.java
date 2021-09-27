package com.readingisgood.warehouse.util;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

	@Autowired
	private UserInfo userInfo;

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AccountNotFoundException.class)
	public void handleNotFound(AccountNotFoundException exception) {
		log.error("Requested account not found {}, {}", userInfo.getUserName(), exception.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public void handleBadRequest(InvalidFormatException exception) {
		log.error("Invalid format of the request {}, {}", userInfo.getUserName(), exception.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void handleGeneralError(Exception exception) {
		log.error("An error occurred processing request {}, {}", userInfo.getUserName(), exception.getMessage());
	}

}
