package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

public class PostsDto {

	private String title;

	private String description;

	private UserProfilesDto userProfileDto;

	private Set<TagsDto> tagsDto = new HashSet<>();

	public PostsDto() {
		super();
	}

	public PostsDto(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TagsDto> getTagsDto() {
		return tagsDto;
	}

	public void setTagsDto(Set<TagsDto> tagsDto) {
		this.tagsDto = tagsDto;
	}

	public UserProfilesDto getUserProfileDto() {
		return userProfileDto;
	}

	public void setUserProfileDto(UserProfilesDto userProfileDto) {
		this.userProfileDto = userProfileDto;
	}

}
