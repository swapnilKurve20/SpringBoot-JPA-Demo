package com.example.demo.dao;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
@Transactional 
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public Long saveUser(User user) {

		Long userId = null;
		try {
			Serializable id = getSession().save(user);
			User userDetails = getSession().get(User.class, id);
			userId = userDetails.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}

	public User getUser(Long userId) {
		
		Serializable id =userId;
		User user = null;
		try {
		    user = getSession().get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
