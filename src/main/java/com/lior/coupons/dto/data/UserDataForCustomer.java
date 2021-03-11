package com.lior.coupons.dto.data;

public class UserDataForCustomer {

	private String userName;
	private String firstName;
	private String LastName;


	public UserDataForCustomer(String userName, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		LastName = lastName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}



}
