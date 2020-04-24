package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class UserRequestDto {

	@NotEmpty(message = "first name must not be empty")
	private String firstName;

	private String lastName;

	@NotEmpty(message = "email must not be empty")
	private String email;

	@NotEmpty(message = "password must not be empty")
	private String password;

	@NotEmpty(message = "Gender must not be empty")
	private String gender;

	private String address;

	public UserRequestDto() {
		super();
	}

	public UserRequestDto(String firstName, String lastName, String email, String password, String gender,
			String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "UserRequestDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", gender=" + gender + ", address=" + address + "]";
	}
}
