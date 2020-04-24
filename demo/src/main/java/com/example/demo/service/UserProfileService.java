package com.example.demo.service;

import com.example.demo.dto.UserProfileResponseDto;


public interface UserProfileService {

	public UserProfileResponseDto getUserProfile(Long userId);
}
