package com.lior.coupons.entities;

import java.io.Serializable;
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
import com.lior.coupons.dto.UserDto;
import com.lior.coupons.enums.UserType;
import com.lior.coupons.exceptions.ApplicationException;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Column(name="user_name", unique = true, nullable = false)
	private String userName;

	@Column(name="password", unique = false, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name="user_type", unique = false, nullable = false)
	private UserType userType;

	@Column(name="first_name", unique = false, nullable = false)
	private String firstName;

	@Column(name="last_name", unique = false, nullable = false)
	private String lastName;

	@ManyToOne
	@JsonIgnore
	private CompanyEntity company;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PurchaseEntity> purchases;

	public UserEntity() {}


	public UserEntity(long id, String userName, String password, UserType userType, String firstName, String lastName,
			CompanyEntity company, List<PurchaseEntity> purchases) {
		this(userName, password, userType, firstName, lastName, company);
		this.id = id;
		this.purchases= purchases;

	}
	public UserEntity(String userName, String password, UserType userType, String firstName, String lastName,
			CompanyEntity company) {

		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
	}
	
	
	public UserEntity(UserDto userDto) {
		this.userName= userDto.getUserName();
		this.password= userDto.getPassword();
		this.userType= userDto.getUserType();
		this.firstName= userDto.getFirstName();
		this.lastName= userDto.getLastName();
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
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
		return "UserEntity [id=" + id + ", userName=" + userName + ", password=" + password 
				+ ", userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + ", company="
				+ company + ", purchases=" + purchases + "]";
	}





}
