package com.lior.coupons.logic;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lior.coupons.dao.IUsersDao;
import com.lior.coupons.dto.UserDto;
import com.lior.coupons.dto.UserLoginDetails;
import com.lior.coupons.dto.data.SuccessfulLoginData;
import com.lior.coupons.dto.data.UserDataForCompany;
import com.lior.coupons.dto.data.UserDataForCustomer;
import com.lior.coupons.dto.data.UserLoginData;
import com.lior.coupons.entities.UserEntity;
import com.lior.coupons.enums.ErrorType;
import com.lior.coupons.exceptions.ApplicationException;

@Controller
public class UsersController {


	@Autowired
	private IUsersDao usersDao;
	@Autowired
	private CompaniesController companiesController;
	@Autowired
	private CacheController cacheController;

	private static final String ENCRIPTION_TOKEN_SALT = "ASFDSDGFDSFGSSD-54675467#$%^";


	public long createUser(UserDto user) throws ApplicationException {
		validateCreateUser(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		UserEntity userEntity= fromUserDtoToUserEntity(user);
		try {

			userEntity= this.usersDao.save(userEntity);
			long id= userEntity.getId();
			return id;
		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, user.toString());

		} 
	}

	private void validateCreateUser(UserDto user) throws ApplicationException {
		try {
			if(usersDao.findByUserName(user.getUserName())!= null) {
				throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS);
			}

		}catch (Exception e) {
			if( e instanceof ApplicationException) {
				throw e;
			}
			throw new ApplicationException(ErrorType.GENERAL_ERROR,"CRUD error");
		}

		if (user.getUserName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (user.getUserName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}
		if (user.getUserName().substring(0, user.getUserName().indexOf("@")).length() < 2) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL);
		}
		if (user.getUserName().substring(user.getUserName().indexOf('@'), user.getUserName().indexOf('.')).length() < 3) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL);
		}
		if (user.getUserName().substring(user.getUserName().indexOf('.')).length() < 3) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL);
		}
		if(user.getUserType()==null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		commonValidate(user);
	}

	public UserDto getUser(long id) throws ApplicationException {
		UserDto user;
		try {
			user= this.usersDao.getForAdmin(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"get user failed"+ id);

		}
		if(user == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return user;
	}


	public UserDataForCompany getUserForCompany(long id) throws ApplicationException {
		UserDataForCompany user;
		try {
			user= this.usersDao.getForCompany(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"get user failed"+ id);

		}
		if(user == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return user;
	}

	public UserDataForCustomer getUserForCustomer(long id) throws ApplicationException {
		UserDataForCustomer user;
		try {
			user=  this.usersDao.getForCustomer(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"get user failed"+ id);

		}
		if(user == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return user;
	}

	public void updateUser(UserDto user, UserLoginData userLoginData) throws ApplicationException {
		user.setCompanyId(userLoginData.getCompanyId());		
		commonValidate(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		UserEntity userEntity= fromUserDtoToUserEntity(user);
		userEntity.setId(userLoginData.getUserId());
		try {
			this.usersDao.save(userEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"update user failed"+ user.toString());

		}
	}


	private void commonValidate(UserDto user) throws ApplicationException {
		// this validate is only if the user is a company
		if (user.getCompanyId()!= null) {
			if(companiesController.validateCompanyExist(user.getCompanyId())==false) {
				throw new ApplicationException(ErrorType.INVALID_COMMPANY_ID);
			}
		}
		if (user.getFirstName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (user.getFirstName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (user.getFirstName().length() < 2) {
			throw new ApplicationException(ErrorType.MUST_BE_2_CHARACTERS);
		}
		if (user.getLastName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (user.getLastName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (user.getLastName().length() < 2) {
			throw new ApplicationException(ErrorType.MUST_BE_2_CHARACTERS);
		}
		if (user.getPassword() == null) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD);
		}

		if (user.getPassword().isEmpty()) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD);	
		}

		if (user.getPassword().length() < 8) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD);

		}
	}

	public void deleteUser(long id) throws ApplicationException {
		if(validateUserExist(id)==false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		try {
			this.usersDao.deleteById(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"delete user failed"+ id);

		}
		
		this.cacheController.deleteAllUserData(id);

	}

	public List<UserDto> getAllUsers() throws ApplicationException {
		try {
			List<UserDto> users=  this.usersDao.getAll();
			return users;
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"get all users failed");

		}
	}


	public SuccessfulLoginData login(UserLoginDetails userLoginDetails) throws ApplicationException {
		userLoginDetails.setPassword(String.valueOf(userLoginDetails.getPassword().hashCode()));
		UserLoginData userLoginData= this.usersDao.login(userLoginDetails.getUserName(),userLoginDetails.getPassword());
		if(userLoginData== null) {
			throw new ApplicationException(ErrorType.LOGIN_FAILED,"user name or password are incorrect");
		}
		String token= generateToken(userLoginDetails.getUserName());
		this.cacheController.putData(token, userLoginData);
		this.cacheController.putToken(userLoginData.getUserId(), token);
		SuccessfulLoginData successfulLoginData= new SuccessfulLoginData(userLoginData.getUserType(), token, userLoginData.getUserId());
		return successfulLoginData;

	}

	private String generateToken(String userName) {
		int token= (userName + Calendar.getInstance().getTime().toString()+ ENCRIPTION_TOKEN_SALT).hashCode();
		return String.valueOf(token);
	}
	
	public void logOut(UserLoginData userLoginData, String token) {
		long id= userLoginData.getUserId();
		this.cacheController.deleteData(id, token);
	}



	UserEntity fromUserDtoToUserEntity(UserDto user) throws ApplicationException {
		UserEntity userEntity= new UserEntity(user);				
		if(user.getCompanyId()!=null) {
			long companyId= user.getCompanyId();
			userEntity.setCompany(companiesController.getCompany(companyId));
		}
		return userEntity;
	}
	
	
	boolean validateUserExist(long id) throws ApplicationException {
		try {
			return usersDao.existsById(id);

		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,"validate user exist failed"+ id);
		}
	}

}
