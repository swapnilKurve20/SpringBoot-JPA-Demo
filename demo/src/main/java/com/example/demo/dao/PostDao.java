package com.example.demo.dao;

import java.util.Map;

import com.example.demo.model.Posts;

public interface PostDao {
	Posts addPost(Posts post);

	Map<String, Posts> getAllPostsByProfile(String id);

	Posts findById(Long id);

	void deleteById(String profileId, String postId);

	void updatePost(String profileId, Posts post);

}
