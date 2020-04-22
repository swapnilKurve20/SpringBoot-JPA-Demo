package com.example.demo.pojo;

public class UserProfile {

	private String gender;

	private String address;

	public UserProfile(String gender, String address) {
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
