package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserProfileService;

@Component
public class BaseController {

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	PostService postService;
	
	@Autowired
	TagService tagService;
	
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
