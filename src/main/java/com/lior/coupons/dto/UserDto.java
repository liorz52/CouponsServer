package com.lior.coupons.dto;

import com.lior.coupons.enums.UserType;

public class UserDto {
	private long id;
	private String userName;
	private String password;
	private Long companyId;
	private UserType userType;
	private String firstName;
	private String lastName;


	public UserDto(long id, String userName, String password, Long companyId, UserType userType, String firstName,
			String lastName) {
		this (userName,password, companyId, userType, firstName,
				lastName);
		this.id = id;

	}
	public UserDto(String userName, String password, Long companyId, UserType userType, String firstName,
			String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.companyId = companyId;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public UserDto() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
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
	@Override
	public String toString() {
		return "\nUser [id=" + id + ", userName=" + userName + ", password=" + password + ", companyId=" + companyId
				+ ", userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}


}

