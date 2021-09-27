package com.readingisgood.warehouse.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.warehouse.security.BookUserDetailsService;
import com.readingisgood.warehouse.security.filter.JwtUtil;
import com.readingisgood.warehouse.security.model.AuthenticationRequest;
import com.readingisgood.warehouse.security.model.AuthenticationResponse;
import com.readingisgood.warehouse.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Slf4j
@RestController
public class AuthenticationController {
	
	Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BookUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping("/authenticate")
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			LOGGER.info(Constants.START_LOG_STATEMENT, "createAuthenticationToken", authenticationRequest.getUsername());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (Exception exception) {
			LOGGER.error(Constants.EXCEPTION_LOG_STATEMENT, "createAuthenticationToken", authenticationRequest.getUsername(), exception);
			throw new Exception("Incorrect username or password", exception);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));	
	}
		
}
