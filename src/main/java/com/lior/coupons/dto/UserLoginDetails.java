package com.lior.coupons.dto;

public class UserLoginDetails {

	private String userName;
	private String password;

	public UserLoginDetails(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public UserLoginDetails() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
