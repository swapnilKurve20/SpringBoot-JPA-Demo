package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.exceptions.CustomExceptionHandler;
import com.example.demo.exceptions.DataNotFoundException;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController{

	@PostMapping(path = "/addUser", consumes = "application/json", produces = "application/json")
	public Long saveUser(@RequestBody UserRequestDto user) {
		Long userId=null;
		try {
			userId = getUserService().saveUser(user);	
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		return userId;
	}

	@GetMapping("/getUserById")
	public UserResponseDto getUser(@RequestParam(value = "id") String id) throws Exception {
		UserResponseDto userResponseDto = null;
		
		try {
			userResponseDto = getUserService().getUser(Long.parseLong(id));
			if (userResponseDto == null)
				throw new DataNotFoundException("No data found for id " + id);	
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		return userResponseDto;
	}
}
