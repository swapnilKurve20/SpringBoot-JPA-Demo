package com.example.demo.dao;

import com.example.demo.model.UserProfiles;

public interface UserProfileDao {
	UserProfiles getUserProfile(Long userId);
	public String insert1() throws Exception;
}
