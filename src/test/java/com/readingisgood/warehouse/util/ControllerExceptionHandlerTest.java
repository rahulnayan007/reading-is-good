package com.readingisgood.warehouse.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	@WithMockUser
	public final void TestServerError() throws Exception {
		String json = mapper.writeValueAsString("Test of internal server error");
		mockMvc.perform(post("/api/customer/list").contentType(MediaType.APPLICATION_XML).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError());
	}

	@Test
	@WithMockUser
	public final void TestBadRequest() throws Exception {
		String json = mapper.writeValueAsString("Test of bad request");
		mockMvc.perform(post("/api/customer/list/na").contentType(MediaType.APPLICATION_XML).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	@Test
	public final void TestBadUser() throws Exception {
		String json = mapper.writeValueAsString("Test of bad request");
		mockMvc.perform(post("/api/customer/list").contentType(MediaType.APPLICATION_XML).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

}
