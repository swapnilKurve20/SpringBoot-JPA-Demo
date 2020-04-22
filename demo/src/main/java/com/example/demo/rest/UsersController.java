package com.example.demo.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.UserProfiles;
import com.example.demo.pojo.UserDetails;
import com.example.demo.repo.IUsersRepo;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	IUsersRepo usersRepo;

	@PostMapping(path = "/addUser", consumes = "application/json", produces = "application/json")
	public String saveUser(@RequestBody UserDetails user) throws Exception {

		Long id = null;
		if (user != null) {
			User u = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
			UserProfiles profile = new UserProfiles(user.getGender(), user.getAddress());

			u.setUserProfile(profile);

			profile.setUser(u);

			User u1 = usersRepo.save(u);

			id = u1.getId();
		}
		if (id == null)
			throw new Exception("Exception occurred while saving User");
		else
			return String.valueOf(id);
	}

	@GetMapping("/getUserById")
	public UserDetails getUser(@RequestParam(value = "id") String id) throws Exception {
		UserDetails userDetails = null;
		if (id == null)
			throw new Exception("Exception occurred while saving User");
		else {
			Optional<User> user = null;

			user = usersRepo.findById(Long.parseLong(id));

			User u = user.get();

			userDetails = new UserDetails(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(),
					u.getUserProfile().getGender(), u.getUserProfile().getAddress());
		}
		return userDetails;
	}
}
