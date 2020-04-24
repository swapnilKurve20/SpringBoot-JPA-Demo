package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.example.demo.model.Posts;

public class TagResponseDto {

	private Long Id;
	private String name;
    private Set<Posts> posts = new HashSet<>();
    
	public TagResponseDto() {
		super();
	}

	public TagResponseDto(Long id, String name, Set<Posts> posts) {
		super();
		Id = id;
		this.name = name;
		this.posts = posts;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Posts> getPosts() {
		return posts;
	}

	public void setPosts(Set<Posts> posts) {
		this.posts = posts;
	}
	
}
