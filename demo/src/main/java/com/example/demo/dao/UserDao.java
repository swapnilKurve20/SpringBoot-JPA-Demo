package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao {
	public Long saveUser(User user);
	public User getUser(Long userId);
	public String insert2() throws Exception;
}
