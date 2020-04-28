package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.UserProfilesDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.model.UserProfiles;

@Service
@Transactional(transactionManager="hibernateTransactionManager", isolation=Isolation.SERIALIZABLE,rollbackFor=Exception.class) // specify transaction manager
public class UserServiceImpl extends BaseService implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	public Long saveUser(UserRequestDto userRequestDto) {
		LOGGER.info("Service : Entered save user method");
		Long userId = null;
		try {
			if (userRequestDto != null) {
				User u = new User(userRequestDto.getFirstName(), userRequestDto.getLastName(),
						userRequestDto.getEmail(), userRequestDto.getPassword());
				UserProfiles profile = new UserProfiles(userRequestDto.getGender(), userRequestDto.getAddress());

				u.setUserProfile(profile);
				profile.setUser(u);

				userId = getUserDao().saveUser(u);
			}
			if (userId == null)
				throw new Exception("Exception occurred while saving User");
			else {
				LOGGER.info("Service : Completed save user method");
				return userId;
			}
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Service : Completed save user method");
		return userId;
	}

	public UserResponseDto getUser(Long userId) {
		LOGGER.info("Service : Entered get user method");
		ModelMapper modelMapper = new ModelMapper();
		UserResponseDto userResponseDto = null;
		User user = getUserDao().getUser(userId);
		userResponseDto = modelMapper.map(user, UserResponseDto.class);

		UserProfiles userProfile = user.getUserProfile();
		userResponseDto.setProfilesDto(modelMapper.map(userProfile, UserProfilesDto.class));

		Map<String, Posts> posts = getPostDao().getAllPostsByProfile(String.valueOf(userProfile.getId()));

		if (posts != null) {
			List<PostResponseDto> responseDtos = new ArrayList<>();
			PostResponseDto dto;
			for (Posts p : posts.values()) {
				dto = getModelMapper().map(p, PostResponseDto.class);
				responseDtos.add(dto);
			}
			userResponseDto.setPosts(responseDtos);
		}

		LOGGER.info("Service : Completed get user method");
		return userResponseDto;
	}
	
	public String insert2() throws Exception {
		String insert2 = getUserDao().insert2();
		// if(true) { throw new Exception("**************message"); }  
	return insert2;
}
}
