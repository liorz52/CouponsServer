package com.lior.coupons.dto.data;

import java.util.Date;

public class PurchaseDataGet {
	

	private String userName;
	private String couponName;
	private String companyName;
	private Date timestamp;
	private long amount;
	
	
	
	
	public PurchaseDataGet(String userName, String couponName, String companyName, Date timestamp, long amount) {
		super();
		this.userName = userName;
		this.couponName = couponName;
		this.companyName = companyName;
		this.timestamp = timestamp;
		this.amount = amount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
	
}
