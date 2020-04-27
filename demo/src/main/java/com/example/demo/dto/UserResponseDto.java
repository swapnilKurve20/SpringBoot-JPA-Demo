package com.example.demo.dto;

import java.util.List;

public class UserResponseDto {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private UserProfilesDto profilesDto;

	private List<PostResponseDto> posts;

	public UserResponseDto() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserProfilesDto getProfilesDto() {
		return profilesDto;
	}

	public void setProfilesDto(UserProfilesDto profilesDto) {
		this.profilesDto = profilesDto;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PostResponseDto> getPosts() {
		return posts;
	}

	public void setPosts(List<PostResponseDto> posts) {
		this.posts = posts;
	}

}
