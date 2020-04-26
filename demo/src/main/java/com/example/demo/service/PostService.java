package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.model.Posts;

public interface PostService {

	Posts addPost(Long id, PostRequestDto post);

	List<PostRequestDto> getAllPostsByProfile(String profileId);
	
	void deleteById(String userProfileId, String postId);
	
	void updatePost(String userProfileId, PostRequestDto requestDto);
}
