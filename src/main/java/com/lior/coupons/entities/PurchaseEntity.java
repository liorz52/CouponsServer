package com.lior.coupons.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="purchases")
public class PurchaseEntity implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Column(name="amount", unique = false, nullable = false)
	private long amount;

	@Column(name="timestamp", unique = false, nullable = false)
	private Date timestamp;

	@ManyToOne
	@JsonIgnore
	private UserEntity user;

	@ManyToOne 
	@JsonIgnore
	private CouponEntity coupon;

	private PurchaseEntity() {}



	public PurchaseEntity(long id, long amount, Date timestamp, UserEntity user, CouponEntity coupon) {
		this(amount, timestamp, user, coupon);
		this.id = id;
	}

	public PurchaseEntity(long amount, Date timestamp, UserEntity user, CouponEntity coupon) {
		this.amount = amount;
		this.timestamp = timestamp;
		this.user = user;
		this.coupon = coupon;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CouponEntity getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponEntity coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "PurchaseEntity [id=" + id + ", amount=" + amount
				+ ", timestamp=" + timestamp + ", user=" + user + ", coupon=" + coupon + "]";
	}


}
