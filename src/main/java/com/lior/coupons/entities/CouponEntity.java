package com.lior.coupons.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lior.coupons.dto.CouponDto;
import com.lior.coupons.enums.CategoryType;


@Entity
@Table(name="coupons")
public class CouponEntity implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Column(name="coupon_name", unique = true, nullable = false)
	private String couponName;

	@Column(name="price", unique = false, nullable = false)
	private Float price;

	@Column(name="description", nullable = true)
	private String description;

	@Column(name="start_date", unique = false, nullable = false)
	private Date startDate;

	@Column(name="end_date", unique = false, nullable = false)
	private Date endDate;

	@Enumerated(EnumType.STRING)
	@Column(name="category", unique = false, nullable = false)
	private CategoryType category;

	@Column(name="amount", unique = false, nullable = false)
	private long amount;

	@ManyToOne
	@JsonIgnore
	private CompanyEntity company;

	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PurchaseEntity> purchases;

	public CouponEntity() {}



	public CouponEntity(long id, String couponName, Float price, String description, Date startDate, Date endDate,
			CategoryType category, long amount, CompanyEntity company, List<PurchaseEntity> purchases) {

		this(couponName, price, description, startDate, endDate, category, amount, company);
		this.purchases = purchases;
		this.id = id;
	}

	public CouponEntity(long id, String couponName, Float price, String description, Date startDate, Date endDate,
			CategoryType category, long amount, CompanyEntity company) {

		this(couponName, price, description, startDate, endDate, category, amount, company);
		this.id = id;
	}

	public CouponEntity(String couponName, Float price, String description, Date startDate, Date endDate,
			CategoryType category, long amount, CompanyEntity company) {

		this.couponName = couponName;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.amount = amount;
		this.company = company;
	}
	

	public CouponEntity(CouponDto couponDto) {
		this.couponName= couponDto.getCouponName();
		this.price= couponDto.getPrice();
		this.description= couponDto.getDescription();
		this.startDate= couponDto.getStartDate();
		this.endDate= couponDto.getEndDate();
		this.category= couponDto.getCategory();
		this.amount= couponDto.getAmount();
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


	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "CouponEntity [id=" + id + ", couponName=" + couponName + ", price=" + price + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", category=" + category
				+ ", amount=" + amount  + ", company=" + company
				+ ", purchases=" + purchases + "]";
	}






}
