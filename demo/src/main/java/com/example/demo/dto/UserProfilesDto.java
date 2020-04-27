package com.example.demo.dto;

public class UserProfilesDto {

	private String id;

	private String gender;

	private String address;

	private UserDto user;

	public UserProfilesDto() {
		super();
	}

	public UserProfilesDto(String gender, String address) {
		super();
		this.gender = gender;
		this.address = address;
	}

	
	public UserProfilesDto(String gender, String address, UserDto user) {
		super();
		this.gender = gender;
		this.address = address;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserDto getUserDto() {
		return user;
	}

	public void setUserDto(UserDto userDto) {
		this.user = userDto;
	}

}
