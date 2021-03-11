package com.lior.coupons.dto.data;

import com.lior.coupons.enums.UserType;

public class UserLoginData {

	private long userId;
	private Long companyId;
	private UserType userType;



	public UserLoginData(long userId, Long companyId, UserType userType) {
		super();
		this.userId = userId;
		this.companyId = companyId;
		this.userType = userType;
	}

	public UserLoginData() {
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "UserLoginData [userId=" + userId + ", companyId=" + companyId + ", userType=" + userType + "]";
	}


}
