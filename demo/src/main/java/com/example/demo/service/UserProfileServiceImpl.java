package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserProfileResponseDto;
import com.example.demo.model.UserProfiles;

@Service
public class UserProfileServiceImpl extends BaseService implements UserProfileService {

	ModelMapper modelMapper = new ModelMapper();

	public UserProfileResponseDto getUserProfile(Long userId) {
		
		UserProfiles userProfile = null;
		UserProfileResponseDto userProfileResponseDto  = null;
		
		try {
			userProfile = getUserProfileDao().getUserProfile(userId);
			if (userProfile != null) {
				userProfileResponseDto = modelMapper.map(userProfile,
						UserProfileResponseDto.class);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfileResponseDto;
	}
}
