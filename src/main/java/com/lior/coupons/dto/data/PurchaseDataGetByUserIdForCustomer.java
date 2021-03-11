package com.lior.coupons.dto.data;

import java.util.Date;

public class PurchaseDataGetByUserIdForCustomer {

	private long id;
	private String couponName;
	private String companyName;
	private long amount;
	private Date timeStamp;



	public PurchaseDataGetByUserIdForCustomer(long id, String couponName, String companyName, long amount,
			Date timeStamp) {
		super();
		this.id = id;
		this.couponName = couponName;
		this.companyName = companyName;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
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



	public long getAmount() {
		return amount;
	}



	public void setAmount(long amount) {
		this.amount = amount;
	}



	public Date getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


}
