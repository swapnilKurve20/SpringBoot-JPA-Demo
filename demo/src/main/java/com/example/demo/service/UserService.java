package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;

public interface UserService {
	public Long saveUser(UserRequestDto user);
	public UserResponseDto getUser(Long userId);
}
