package com.example.demo.dto;

public class UserProfilesDto {

	private String id;

	private String gender;

	private String address;

	private UserDto userDto;

	public UserProfilesDto() {
		super();
	}

	public UserProfilesDto(String gender, String address) {
		super();
		this.gender = gender;
		this.address = address;
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
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
