package com.example.demo.dto;

public class UserProfileResponseDto {

	private String gender;
	private String address;

	public UserProfileResponseDto() {
		super();
	}

	public UserProfileResponseDto(String gender, String address) {
		super();
		this.gender = gender;
		this.address = address;
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

}
