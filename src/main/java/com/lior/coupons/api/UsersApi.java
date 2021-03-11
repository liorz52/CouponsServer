package com.lior.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lior.coupons.dto.UserDto;
import com.lior.coupons.dto.UserLoginDetails;
import com.lior.coupons.dto.data.SuccessfulLoginData;
import com.lior.coupons.dto.data.UserDataForCompany;
import com.lior.coupons.dto.data.UserDataForCustomer;
import com.lior.coupons.dto.data.UserLoginData;
import com.lior.coupons.exceptions.ApplicationException;
import com.lior.coupons.logic.CacheController;
import com.lior.coupons.logic.UsersController;

@RestController
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	UsersController usersController;
	

	@PostMapping
	public long createUser(@RequestBody UserDto user) throws ApplicationException {
		return this.usersController.createUser(user);
	}

	@GetMapping("/{userId}")
	public UserDto getUserForAdmin(@PathVariable("userId") long id) throws ApplicationException {
		UserDto user= this.usersController.getUser(id);
		return user;
	}

	@GetMapping("/forCompany")
	public UserDataForCompany getUserForCompany(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		UserDataForCompany user= this.usersController.getUserForCompany(userLoginData.getUserId());
		return user;
	}

	@GetMapping("/forCustomer")
	public UserDataForCustomer getUserForCustomer(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		UserDataForCustomer user= this.usersController.getUserForCustomer(userLoginData.getUserId());
		return user;
	}

	@PutMapping
	public void updateUser(@RequestBody UserDto user, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		this.usersController.updateUser(user, userLoginData);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long id) throws ApplicationException {
		this.usersController.deleteUser(id);
	}

	@GetMapping
	public List<UserDto> getAllUsers() throws ApplicationException {
		List<UserDto> users = this.usersController.getAllUsers();
		return users;
	}

	@PostMapping("/login")
	public SuccessfulLoginData login(@RequestBody UserLoginDetails userLoginDetails ) throws ApplicationException {
		SuccessfulLoginData successfulLoginData= this.usersController.login(userLoginDetails);
		return successfulLoginData;
	}
	
	@PostMapping("/logOut")
	public void logOut(  HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		String token= request.getHeader("Authorization");
		this.usersController.logOut(userLoginData, token);
		
	}



}
