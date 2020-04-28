package com.example.demo.dao;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import com.example.demo.model.User;

@Repository
@Transactional 
public class UserDaoImpl extends BaseDao implements UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public Long saveUser(User user) {
		LOGGER.info("Dao : Entered save user method");
		Long userId = null;
		try {
			Serializable id = getSession().save(user);
			User userDetails = getSession().get(User.class, id);
			userId = userDetails.getId();
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Dao : Completed save user method");
		return userId;
	}

	public User getUser(Long userId) {
		LOGGER.info("Dao : Completed get user method");
		Serializable id =userId;
		User user = null;
		try {
		    user = getSession().get(User.class, id);
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Dao : Completed get user method");
		return user;
	}
	
	public String insert2() throws Exception {

		Session session = getSession();
		int executeUpdate = 0;

		NativeQuery createNativeQuery = session.createNativeQuery("insert into test2(name) values (:name)");
		createNativeQuery.setParameter("name", "Santosh");
		executeUpdate = createNativeQuery.executeUpdate();

		return String.valueOf(executeUpdate);
	}
}
