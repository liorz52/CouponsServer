package com.lior.coupons.dto;


public class CompanyDto {

	private long id;
	private String companyName;
	private String address;
	private String phoneNumber;

	public CompanyDto() {
		super();
	}

	public CompanyDto(String companyName, String address, String phoneNumber) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public CompanyDto(long id, String companyName, String address, String phoneNumber) {
		this(companyName, address, phoneNumber);
		this.id = id;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return " \nCompany [id=" + id + ", CompanyName=" + companyName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]\n";
	}



}
