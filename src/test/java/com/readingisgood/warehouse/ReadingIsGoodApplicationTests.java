package com.readingisgood.warehouse;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

//@RunWith(Suite.class)
//@SuiteClasses({})
class ReadingIsGoodApplicationTests {

	@MockBean
	ApplicationContext ctx;
	
	@Test
	void contextLoads() {
		//assertNotNull(ctx);
	}

}
