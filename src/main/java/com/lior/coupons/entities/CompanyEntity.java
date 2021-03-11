package com.lior.coupons.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lior.coupons.dto.CompanyDto;
import com.lior.coupons.exceptions.ApplicationException;


@Entity
@Table(name="companies")
public class CompanyEntity  implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Column(name="company_name", unique = true, nullable = false)
	private String companyName;

	@Column(name="address", unique = false, nullable = false)
	private String address;

	@Column(name="phone_number", unique = false, nullable = false)
	private String phoneNumber;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserEntity> users;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<CouponEntity> coupons;



	public CompanyEntity(long id, String companyName, String address, String phoneNumber, List<UserEntity> users,
			List<CouponEntity> coupons) {

		this (companyName, address, phoneNumber);
		this.id = id;
		this.users= users;
		this.coupons= coupons;
	}

	public CompanyEntity(String companyName, String address, String phoneNumber) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public CompanyEntity(CompanyDto companyDto) {
		this.companyName= companyDto.getCompanyName();
		this.address= companyDto.getAddress();
		this.phoneNumber= companyDto.getPhoneNumber();
	}
	

	public CompanyEntity() {}

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

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<CouponEntity> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponEntity> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "CompanyEntity [id=" + id + ", companyName=" + companyName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", users=" + users + ", coupons=" + coupons + "]";
	}



}
