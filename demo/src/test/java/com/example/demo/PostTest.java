package com.example.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostRequestDto.TagsDetails;
import com.example.demo.dto.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class PostTest implements CommonData {

	@Autowired
	private MockMvc mvc;
	
	@Test
	@Order(1)
	public void createUserAPI() throws Exception 
	{
		System.out.println("Creating user-----------------------1");
		UserRequestDto user=new UserRequestDto("John","Doe","john.doe1992@xyz.com","j2ohnDoe123","M","Street no 45 NY");
	
		mvc.perform(
			post("/users")
			.content(asJsonString(user))
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.with(httpBasic(USERNAME, PASSWORD)))
			.andExpect(status().isCreated()).andDo(print());
	}
	

	@Test
	@Order(2)
	void getUser() throws Exception {

		System.out.println("Get user-----------------------2");
		mvc.perform(get("/users?id=1").accept(MediaType.APPLICATION_JSON).with(httpBasic(USERNAME, PASSWORD)))
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@Order(3)
	public void createPostAPI() throws Exception 
	{
		System.out.println("Creating post-----------------------3");
		PostRequestDto post=new PostRequestDto("PostTitle","Post1 Description",null);
		
		Set<TagsDetails> tagList=new HashSet<TagsDetails>();
		
		TagsDetails tag1 = new TagsDetails();
		tag1.setName("Tag1");
		TagsDetails tag2 = new TagsDetails();
		tag2.setName("Tag1");
				
		tagList.add(tag1);
		tagList.add(tag2);
		
		post.setTags(tagList);
		mvc.perform(
			post("/post/1")
			.content(asJsonString(post))
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.with(httpBasic(USERNAME, PASSWORD)))
			.andExpect(status().isCreated()).andDo(print());
	}

	@Test
	@Order(4)
	void getPost() throws Exception {

		System.out.println("Get post by user id-----------------------4");
		mvc.perform(get("/users?id=1").accept(MediaType.APPLICATION_JSON).with(httpBasic(USERNAME, PASSWORD)))
				.andExpect(status().isOk()).andDo(print());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
