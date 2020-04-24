package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userService;

	@PostMapping(path = "/addUser", consumes = "application/json", produces = "application/json")
	public Long saveUser(@RequestBody UserRequestDto user) {

		Long userId = userService.saveUser(user);

		return userId;
	}

	@GetMapping("/getUserById")
	public UserResponseDto getUser(@RequestParam(value = "id") String id) throws Exception {
		UserResponseDto userResponseDto = null;
		userResponseDto = userService.getUser(Long.parseLong(id));
		if (userResponseDto == null)
			throw new DataNotFoundException("No data found for id " + id);

		return userResponseDto;
	}
}
