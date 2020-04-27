package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserProfileResponseDto;
import com.example.demo.model.UserProfiles;
import com.example.demo.rest.UserProfilesController;

@Service
public class UserProfileServiceImpl extends BaseService implements UserProfileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	public UserProfileResponseDto getUserProfile(Long userId) {
		LOGGER.info("Started get user profile call.");

		UserProfiles userProfile = null;
		UserProfileResponseDto userProfileResponseDto = null;

		try {
			userProfile = getUserProfileDao().getUserProfile(userId);
			if (userProfile != null) {
				userProfileResponseDto = getModelMapper().map(userProfile, UserProfileResponseDto.class);
			}
		} catch (Exception exception) {
			getCustomExceptionHandler().logExcepton(exception);
		}
		LOGGER.info("Completed get user profile call.");
		return userProfileResponseDto;
	}
}
