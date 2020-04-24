package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.UserProfiles;

@Repository
@Transactional
public class UserProfileDaoImpl extends BaseDao implements UserProfileDao {

	public UserProfiles getUserProfile(Long prifileId) {
		
		UserProfiles userProfiles = null;
		try {
			userProfiles = (UserProfiles) getSession().get(UserProfiles.class, prifileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfiles;
	}
}
