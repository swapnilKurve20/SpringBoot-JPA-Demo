package com.example.demo.dto;

import java.util.Set;

import com.example.demo.dto.PostRequestDto.TagsDetails;

public class PostResponseDto {

	private String id;

	private String title;

	private String description;

	private Set<UserProfilesDto> likedBy;

	private Set<TagsDetails> tags;

	public PostResponseDto() {
		super();
	}

	public PostResponseDto(String title, String description, Set<UserProfilesDto> likedBy, Set<TagsDetails> tags) {
		super();
		this.title = title;
		this.description = description;
		this.likedBy = likedBy;
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Set<UserProfilesDto> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<UserProfilesDto> likedBy) {
		this.likedBy = likedBy;
	}

	public Set<TagsDetails> getTags() {
		return tags;
	}

	public void setTags(Set<TagsDetails> tags) {
		this.tags = tags;
	}

}
