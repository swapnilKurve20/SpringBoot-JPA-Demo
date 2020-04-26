package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.exceptions.DataNotFoundException;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

	@PostMapping
	public ResponseEntity<Object> saveUser(@RequestBody UserRequestDto user) {

		Long userId = getUserService().saveUser(user);

		return new ResponseEntity<Object>(userId, HttpStatus.OK);
	}

	@GetMapping
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
