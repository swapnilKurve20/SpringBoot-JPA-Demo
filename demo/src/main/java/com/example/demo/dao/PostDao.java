package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Posts;

public interface PostDao {
	Posts addPost(Posts post);

	List<Posts> getAllPostsByProfile(String id);

	Posts findById(Long id);

	void deleteById(String profileId, String postId);

	void updatePost(String profileId, Posts post);

}
