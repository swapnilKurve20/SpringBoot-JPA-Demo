package com.example.demo.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import com.example.demo.pojo.UserDetails;
import com.example.demo.repo.IUsersRepo;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

//	@Autowired
	IUsersRepo usersRepo;

	@Autowired
	UserService userService;
	
	@PostMapping(path = "/addUser", consumes = "application/json", produces = "application/json")
	public Long saveUser(@RequestBody UserRequestDto user) {

		Long userId=null;
		try {
			userId = userService.saveUser(user);		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userId;
	}

	@GetMapping("/getUserById")
	public UserResponseDto getUser(@RequestParam(value = "id") String id) throws Exception {
		UserResponseDto userResponseDto = null;
		if (id == null)
			throw new Exception("Exception occurred while saving User");
		else {
			Optional<User> user = null;

			userResponseDto = userService.getUser(Long.parseLong(id));
		}
		return userResponseDto;
	}
}
