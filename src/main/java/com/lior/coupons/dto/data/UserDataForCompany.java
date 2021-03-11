package com.lior.coupons.dto.data;

public class UserDataForCompany {


	private String userName;
	private long companyId;
	private String firstName;
	private String lastName;



	public UserDataForCompany(String userName, long companyId, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public long getCompanyId() {
		return companyId;
	}



	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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



}
