package com.techgeeknext;

import com.techgeeknext.controller.JwtAuthenticationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootJwtApplicationTests {
	private static final Logger log = LogManager.getLogger(SpringBootJwtApplicationTests.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JwtAuthenticationController controller;

	void contextLoads() {
	}

	// @Test
	// public void userRegistrationLoginAuthenticationDeleteTests() throws Exception{
	// 	log.info("User Registration test");
	// 	mockMvc.perform(post("/register")
	// 			.contentType(APPLICATION_JSON_VALUE)
	// 			.content("{\"firstName\":\"Foo1\", " +
	// 					"\"username\":\"Foo1\", "+
	// 					"\"lastName\":\"Bar1\", " +
	// 					"\"email\":\"foo1@bar.com\", " +
	// 					"\"password\":\"12345\"}"))
	// 			.andExpect(status().isOk());
	// 	log.info("Registration Successfull !!");
//		log.info("Registration with Duplicate email test");
//		mockMvc.perform(post("/registration")
//				.contentType(APPLICATION_JSON_VALUE)
//				.content("{\"firstName\":\"Foo\", " +
//						"\"lastName\":\"Bar\", " +
//						"\"email\":\"foo@bar.com\", " +
//						"\"password\":\"12345\"}"))
//				.andExpect(status().is4xxClientError());

		// log.info("Login test");
		// MvcResult mvcResult = mockMvc.perform(post("/authenticate")
		// 		.contentType(APPLICATION_JSON_VALUE)
		// 		.content("{\"username\":\"Foo1\", " +
		// 				"\"password\":\"12345\"}"))
		// 		.andExpect(status().isOk())
		// 		.andReturn();
//		JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
//		authToken = resp.getString("token");
//
//		log.info("User Get Details test");
//		mockMvc.perform(get("/user/getdetails")
//				.header("Authorization", authToken))
//				.andExpect(status().isOk());
//
//		log.info("Delete User test");
//		mockMvc.perform(post("/user/delete")
//				.header("Authorization", authToken))
//				.andExpect(status().isOk());
	// }


}