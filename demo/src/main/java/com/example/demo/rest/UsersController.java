package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

	@PostMapping
	public ResponseEntity<Object> saveUser(@RequestBody UserRequestDto user) {
	
		LOGGER.info("Controller : Started save user call.");
		Long userId = null;
		try {	
			userId = getUserService().saveUser(user);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Something went wrong !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Controller : Completed save user call for id = "+ userId);
		return new ResponseEntity<Object>(userId, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> getUser(@RequestParam(value = "id") String id) throws Exception {
		LOGGER.info("Controller : Started get user call.");
		UserResponseDto userResponseDto = null;
		try {
			userResponseDto = getUserService().getUser(Long.parseLong(id));
			if (userResponseDto == null)
				throw new DataNotFoundException("No data found for id " + id);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Something went wrong !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Controller : Completed get user call returning results for id = "+ id);
		return new ResponseEntity<Object>(userResponseDto,HttpStatus.OK);
	}
}
