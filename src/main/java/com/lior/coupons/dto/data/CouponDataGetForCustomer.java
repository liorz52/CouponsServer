package com.lior.coupons.dto.data;

public class CouponDataGetForCustomer {
	


	private long id;
	private String couponName;
	private String companyName;
	private String description;
	private Float price;
	
	
	public CouponDataGetForCustomer(long id, String couponName, String companyName, String description, Float price) {
		super();
		this.id = id;
		this.couponName = couponName;
		this.companyName = companyName;
		this.description = description;
		this.price = price;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	

	
}
