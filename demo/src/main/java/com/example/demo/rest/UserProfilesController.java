package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserProfiles;
import com.example.demo.pojo.UserProfile;
import com.example.demo.repo.IUserProfiles;

@RestController
@RequestMapping("/userProfiles")
public class UserProfilesController {

	@Autowired
	private IUserProfiles userProfiles;

	@GetMapping("/getUserProfileByUserId")
	public UserProfile getUserProfile(@RequestParam(value = "userId") String userId) throws Exception {
		UserProfile profile = null;
		if (userId == null)
			throw new Exception("User Id should not be null");
		else {
			UserProfiles up = userProfiles.findByUserId(Long.parseLong(userId));
			profile = new UserProfile(up.getGender(), up.getAddress());
		}
		
		return profile;
	}
}
