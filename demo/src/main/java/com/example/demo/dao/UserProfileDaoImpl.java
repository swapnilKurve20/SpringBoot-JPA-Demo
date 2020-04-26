package com.example.demo.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserProfiles;

@Repository
@Transactional
public class UserProfileDaoImpl extends BaseDao implements UserProfileDao {

	@Autowired
	private UserDao userDao;

	public UserProfiles getUserProfile(Long profileId) {

		UserProfiles userProfiles = null;
		String getProfileSql = "Select * from userprofiles where id = " + profileId;
		try {

			NativeQuery<UserProfiles> queryObj = getSession().createNativeQuery(getProfileSql);
			List<UserProfiles> profile = queryObj.getResultList();
			Iterator it = profile.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				userProfiles = new UserProfiles();
				userProfiles.setId(((BigInteger) line[0]).longValue());
				userProfiles.setAddress(String.valueOf(line[1]));
				userProfiles.setGender(String.valueOf(line[2]));
				userProfiles.setUser(userDao.getUser(Long.parseLong(String.valueOf(line[3]))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfiles;
	}
}
