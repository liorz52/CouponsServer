package com.lior.coupons.dto;



import java.util.Date;

import com.lior.coupons.entities.CouponEntity;
import com.lior.coupons.enums.CategoryType;

public class CouponDto {
	private long id;
	private String couponName;
	private long companyId;
	private Float price;
	private String description;
	private Date startDate;
	private Date endDate;
	private CategoryType category;
	private long amount;


	public CouponDto(long id, String couponName, Float price, String description, Date startDate, Date endDate,
			CategoryType category, long amount, long companyId) {
		this(couponName, price, description, startDate, endDate, category, amount);
		this.id = id;
		this.companyId = companyId;
	}


	public CouponDto(String couponName, Float price, String description, Date startDate, Date endDate, CategoryType category,
			long amount) {
		super();
		this.couponName = couponName;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.amount = amount;
	}


	public CouponDto(CouponEntity couponEntity) {
		this.id= couponEntity.getId();
		this.couponName= couponEntity.getCouponName();
		this.price= couponEntity.getPrice();
		this.description= couponEntity.getDescription();
		this.startDate= couponEntity.getStartDate();
		this.endDate= couponEntity.getEndDate();
		this.category= couponEntity.getCategory();
		this.amount= couponEntity.getAmount();
		this.companyId= couponEntity.getCompany().getId();
	}

	public CouponDto() {
		super();
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


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public CategoryType getCategory() {
		return category;
	}


	public void setCategory(CategoryType category) {
		this.category = category;
	}


	public long getAmount() {
		return amount;
	}


	public void setAmount(long amount) {
		this.amount = amount;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	@Override
	public String toString() {
		return "\nCoupon [id=" + id + ", couponName=" + couponName + ", price=" + price + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", category=" + category + ", amount=" + amount
				+ ", companyId=" + companyId + "]";
	}


}
