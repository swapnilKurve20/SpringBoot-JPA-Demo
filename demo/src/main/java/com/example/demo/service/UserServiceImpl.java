package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import com.example.demo.model.UserProfiles;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
//	ModelMapper modelMapper=new ModelMapper();
	
	public Long saveUser(UserRequestDto userRequestDto) {
		
		Long userId = null;
		try {
			if (userRequestDto != null) {
				User u = new User(userRequestDto.getFirstName(), userRequestDto.getLastName(), userRequestDto.getEmail(), userRequestDto.getPassword());
				UserProfiles profile = new UserProfiles(userRequestDto.getGender(), userRequestDto.getAddress());
				
				u.setUserProfile(profile);
				profile.setUser(u);

				userId = userDao.saveUser(u);
			}
			if (userId == null)
				throw new Exception("Exception occurred while saving User");
			else
				return userId;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	public UserResponseDto getUser(Long userId) {
		// Model mapper - entiry to Response DTO
		ModelMapper modelMapper=new ModelMapper();
		UserResponseDto userResponseDto=null;
		try {
			User user = userDao.getUser(userId);
			userResponseDto = modelMapper.map(user, UserResponseDto.class);
			
			UserProfiles userProfile = user.getUserProfile();
			
			userResponseDto.setGender(userProfile.getGender());
			userResponseDto.setAddress(userProfile.getAddress());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userResponseDto;
	}
}
