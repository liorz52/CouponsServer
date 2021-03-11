package com.lior.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lior.coupons.dto.UserDto;
import com.lior.coupons.dto.data.UserDataForCompany;
import com.lior.coupons.dto.data.UserDataForCustomer;
import com.lior.coupons.dto.data.UserLoginData;
import com.lior.coupons.entities.UserEntity;

@Repository
public interface IUsersDao extends CrudRepository <UserEntity, Long> {

	public UserEntity findByUserName(String userName);


	@Query(value = "select new com.lior.coupons.dto.data.UserLoginData( u.id, u.company.id, u.userType) from UserEntity u where u.userName= :user_name and u.password= :password")
	public UserLoginData login(@Param("user_name") String userName, @Param("password") String password);

	@Query("select new com.lior.coupons.dto.data.UserDataForCompany(u.userName, u.company.id, u.firstName, u.lastName)"
			+ " from UserEntity u where u.id=?1")
	public  UserDataForCompany getForCompany(long id); 

	@Query("select new com.lior.coupons.dto.UserDto(u.id, u.userName, u.password,u.company.id, u.userType ,u.firstName, u.lastName)"
			+ " from UserEntity u where u.id=?1")
	public  UserDto getForAdmin(long id); 


	@Query("select new com.lior.coupons.dto.data.UserDataForCustomer(u.userName, u.firstName, u.lastName)"
			+ " from UserEntity u where u.id=?1")
	public  UserDataForCustomer getForCustomer(long id); 

	@Query("select new com.lior.coupons.dto.UserDto(u.id, u.userName, u.password,u.company.id, u.userType ,u.firstName, u.lastName)"
			+ " from UserEntity u")
	public List<UserDto> getAll(); 


}
