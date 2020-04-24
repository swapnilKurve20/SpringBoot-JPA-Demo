package com.example.demo.service;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.model.Posts;

public interface PostService {

	public Posts addPost(Long id, PostRequestDto post);
}
