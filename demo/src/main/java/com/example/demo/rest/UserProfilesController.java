package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserProfileResponseDto;

@RestController
@RequestMapping("/userProfiles")
public class UserProfilesController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfilesController.class);

	@GetMapping
	public ResponseEntity<Object> getUserProfile(@RequestParam(value = "userId") String userId) throws Exception {
		LOGGER.info("Started get user profile call.");
		UserProfileResponseDto profile = null;
		try {
			if (userId == null)
				throw new Exception("User Id should not be null");
			else {
				profile = getUserProfileService().getUserProfile(Long.parseLong(userId));
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>("Something went wrong !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Completed get user profile call.");
		return new ResponseEntity<Object>(profile, HttpStatus.OK);
	}
}
