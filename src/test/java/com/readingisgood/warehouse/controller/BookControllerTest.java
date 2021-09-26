package com.readingisgood.warehouse.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.readingisgood.warehouse.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
	
	@MockBean
	private BookService bookService;

	@Test
	final void test() {
		assertEquals(1, 1);
	}

}
