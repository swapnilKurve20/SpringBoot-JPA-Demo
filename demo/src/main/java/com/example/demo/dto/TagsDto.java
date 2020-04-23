package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

public class TagsDto {

	private String name;

	private Set<PostsDto> postsDto = new HashSet<>();

	public TagsDto() {
		super();
	}

	public TagsDto(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PostsDto> getPostsDto() {
		return postsDto;
	}

	public void setPostsDto(Set<PostsDto> postsDto) {
		this.postsDto = postsDto;
	}

}
