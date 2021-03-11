package com.lior.coupons.dto;

import java.util.Date;

public class PurchaseDto {
	private long id;
	private long userId;
	private long couponId;
	private String couponName;
	private long amount;
	private Date timestamp;

	/**
	 * ctor for create purchase
	 */
	public PurchaseDto(long couponId, long amount) {
		this.couponId = couponId;
		this.amount = amount;
	}


	public PurchaseDto(long id, long userId,String couponName, long couponId, long amount, Date timestamp) {
		this(couponId, amount);
		this.userId= userId;
		this.couponName= couponName;
		this.timestamp = timestamp;
		this.id = id;

	}



	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public PurchaseDto() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getCouponId() {
		return couponId;
	}


	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}


	public long getAmount() {
		return amount;
	}


	public void setAmount(long amount) {
		this.amount = amount;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "PurchaseDto [id=" + id + ", userId=" + userId + ", couponId=" + couponId + ", amount=" + amount
				+ ", timestamp=" + timestamp + ", couponName=" + couponName + "]";
	}




}
