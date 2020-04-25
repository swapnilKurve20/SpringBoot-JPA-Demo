package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;

@Component
public class BaseController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserProfileService getUserProfileService() {
		return userProfileService;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
}
