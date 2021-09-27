package com.readingisgood.warehouse.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author rahul
 *
 */
@Getter
@AllArgsConstructor
public class AuthenticationResponse {

	private final String jwt;
	
	public String getJwt() {
		return jwt;
	}
	
}
