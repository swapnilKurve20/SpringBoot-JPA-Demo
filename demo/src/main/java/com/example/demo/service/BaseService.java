package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PostDao;
import com.example.demo.dao.TagDao;
import com.example.demo.dao.UserProfileDao;

@Service
public class BaseService {

	@Autowired
	protected UserProfileDao userProfileDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private TagDao tagDao;

	private ModelMapper modelMapper;

	public UserProfileDao getUserProfileDao() {
		return userProfileDao;
	}

	public void setUserProfileDao(UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}

	public PostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public TagDao getTagDao() {
		return tagDao;
	}

	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	public ModelMapper getModelMapper() {
		if (modelMapper == null)
			modelMapper = new ModelMapper();
		return modelMapper;
	}

}
