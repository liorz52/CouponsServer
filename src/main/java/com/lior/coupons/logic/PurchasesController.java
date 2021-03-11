package com.lior.coupons.logic;




import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lior.coupons.dao.IPurchasesDao;
import com.lior.coupons.dto.CouponDto;
import com.lior.coupons.dto.PurchaseDto;
import com.lior.coupons.dto.UserDto;
import com.lior.coupons.dto.data.PurchaseDataGet;
import com.lior.coupons.dto.data.PurchaseDataGetByUserIdForCustomer;
import com.lior.coupons.entities.CouponEntity;
import com.lior.coupons.entities.PurchaseEntity;
import com.lior.coupons.entities.UserEntity;
import com.lior.coupons.enums.ErrorType;
import com.lior.coupons.exceptions.ApplicationException;

@Controller
public class PurchasesController {


	@Autowired
	private IPurchasesDao purchasesDao;
	@Autowired
	private CouponsController couponsController;
	@Autowired
	private CompaniesController companiesController;
	@Autowired
	private UsersController usersController;


	public Long createPurchase(PurchaseDto purchase) throws ApplicationException {
		validateCreatePurchase(purchase);
		PurchaseEntity purchaseEntity= fromPurchaseDtoToPurchaeEntity(purchase);
		try {
			purchaseEntity= this.purchasesDao.save(purchaseEntity);
			Long id= purchaseEntity.getId();
			CouponDto coupon= couponsController.getCoupon(purchase.getCouponId());
			coupon.setAmount(coupon.getAmount()-purchase.getAmount());
			couponsController.updateCoupon(coupon);
			return id;
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, purchase.toString());

		}
	}

	public void validateCreatePurchase(PurchaseDto purchase) throws ApplicationException {
		if(purchase.getAmount()<1) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT);
		}

		// Extracting the coupon to create multiple validations with one DB call
		CouponDto coupon= couponsController.getCoupon(purchase.getCouponId());

		if (coupon == null) {
			throw new ApplicationException(ErrorType.INVALID_COUPON);
		}

		if (coupon.getAmount() - purchase.getAmount() < 0) {
			throw new ApplicationException(ErrorType.NOT_ENOUGH_COUPONS_LEFT);
		}
	}

	public PurchaseDataGet getPurchase(long id) throws ApplicationException {
		PurchaseDataGet purchase;
		try {
			purchase= purchasesDao.getById(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get purchase failed" + id);

		}
		if(purchase == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return purchase;
	}


	public void deletePurchase(long id) throws ApplicationException {
		if(validatePurchaseExist(id)==false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		try {
			this.purchasesDao.deleteById(id);
		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "delete purchase failed" + id);

		}
	}


	public List<PurchaseDto> getAllPurchases() throws ApplicationException{
		try {
			List<PurchaseDto> purchases= purchasesDao.getAll();
			return purchases;
		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all purchases failed");

		}
	}


	public List<PurchaseDataGetByUserIdForCustomer> getAllPrchasesByUserIdForCustomer(long userId) throws ApplicationException{
		if(usersController.validateUserExist(userId)== false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}

		try {
			List<PurchaseDataGetByUserIdForCustomer> purchases= purchasesDao.getAllByUserIdForCustomer(userId);
			return purchases;
		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all purchases by user id failed");

		}
	}

	public List<PurchaseDto> getAllPrchasesByUserId(long userId) throws ApplicationException{
		if(usersController.validateUserExist(userId)== false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}

		try {
			List<PurchaseDto> purchases= purchasesDao.getAllByUserId(userId);
			return purchases;
		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all purchases by user id failed");

		}
	}


	public List<PurchaseDto> getAllPurchasesByCompanyId(long companyId) throws ApplicationException{
		if(companiesController.validateCompanyExist(companyId)== false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}

		try {
			List<PurchaseDto> purchases= purchasesDao.getAllByCompanyId(companyId);
			return purchases;
		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all purchases by company id failed");

		}
	}


	private boolean validatePurchaseExist(long id) throws ApplicationException {
		try {
			return purchasesDao.existsById(id);

		}catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "validate purchase exist failed"+ id);

		}


	}

	private PurchaseEntity fromPurchaseDtoToPurchaeEntity(PurchaseDto purchase) throws ApplicationException {
		Date timestamp= new Date(System.currentTimeMillis());
		UserDto userDto=  usersController.getUser(purchase.getUserId());
		UserEntity user= usersController.fromUserDtoToUserEntity(userDto);
		user.setId(purchase.getUserId());
		CouponDto couponDto= couponsController.getCoupon(purchase.getCouponId());
		CouponEntity coupon= new CouponEntity(couponDto);
		coupon.setId(couponDto.getId());
		PurchaseEntity purchaseEntity = new PurchaseEntity(purchase.getAmount(), timestamp, user,coupon);
		return purchaseEntity;
	}
}
