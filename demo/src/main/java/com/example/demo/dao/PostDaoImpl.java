package com.example.demo.dao;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Posts;

@Repository
@Transactional
public class PostDaoImpl extends BaseDao implements PostDao {
	
	public Posts addPost(Posts post) {
		Posts savedPost=null;
		try {
			Serializable id = getSession().save(post);
			savedPost = (Posts) getSession().get(Posts.class, id);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return savedPost; 
	}
}
