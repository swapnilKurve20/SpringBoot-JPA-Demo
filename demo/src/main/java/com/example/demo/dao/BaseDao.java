package com.example.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.exceptions.CustomExceptionHandler;

@Component
public class BaseDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CustomExceptionHandler customExceptionHandler;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
	
	public CustomExceptionHandler getCustomExceptionHandler() {
		return customExceptionHandler;
	}

}
