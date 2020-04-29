package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests implements CommonData { 

	@Autowired
	private MockMvc mvc;

	/*
	 * @Test
	 * 
	 * @Order(1) void test2() throws Exception { System.out.println("Order 1"); mvc
	 * .perform(get("/tags").accept(MediaType.APPLICATION_JSON).with(httpBasic(
	 * USERNAME, PASSWORD))) .andExpect(status().isOk()); }
	 */
	
}
