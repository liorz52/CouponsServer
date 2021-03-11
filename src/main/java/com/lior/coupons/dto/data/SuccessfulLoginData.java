package com.lior.coupons.dto.data;

import com.lior.coupons.enums.UserType;

public class SuccessfulLoginData {

	private UserType userType;
	private String token;
	private Long userId;
	
	
	public SuccessfulLoginData(UserType userType, String token, Long userId) {
		super();
		this.userType = userType;
		this.token = token;
		this.userId = userId;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "SuccessfulLoginData [userType=" + userType + ", token=" + token + ", userId=" + userId + "]";
	}
	
	


	




}
