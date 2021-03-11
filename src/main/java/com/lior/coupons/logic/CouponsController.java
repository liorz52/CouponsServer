package com.lior.coupons.logic;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lior.coupons.dao.ICouponsDao;
import com.lior.coupons.dto.CouponDto;
import com.lior.coupons.dto.data.CouponDataGetForCustomer;
import com.lior.coupons.entities.CompanyEntity;
import com.lior.coupons.entities.CouponEntity;
import com.lior.coupons.enums.CategoryType;
import com.lior.coupons.enums.ErrorType;
import com.lior.coupons.exceptions.ApplicationException;

@Controller
public class CouponsController {

	@Autowired
	private ICouponsDao couponsDao;

	@Autowired
	private CompaniesController companiesController;


	public long createCoupon(CouponDto coupon) throws ApplicationException {
		validateCreateCoupon(coupon);
		CompanyEntity company = companiesController.getCompany(coupon.getCompanyId());
		CouponEntity couponEntity = new CouponEntity(coupon);
		couponEntity.setCompany(company);
		try {
			couponEntity = this.couponsDao.save(couponEntity);
			long id = couponEntity.getId();
			return id;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, coupon.toString());

		}
	}

	private void validateCreateCoupon(CouponDto coupon) throws ApplicationException {
		try {
			if (couponsDao.findByCouponName(coupon.getCouponName()) != null) {
				throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS);
			}

		} catch (Exception e) {
			if (e instanceof ApplicationException) {
				throw e;
			}
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CRUD error");
		}

		if (coupon.getCouponName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (coupon.getCouponName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (coupon.getPrice() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		if (coupon.getPrice() <= 0) {
			throw new ApplicationException(ErrorType.INVALID_PRICE);
		}
		if (coupon.getStartDate().before(Calendar.getInstance().getTime())) {
			throw new ApplicationException(ErrorType.INVALID_DATES);
		}
		if (coupon.getStartDate() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		if (coupon.getEndDate().before(coupon.getStartDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATES);
		}
		if (coupon.getEndDate() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}

		if (coupon.getCategory() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}

		if (coupon.getAmount() < 1) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT);
		}

	}

	public CouponDataGetForCustomer getCouponForCustomer(long id) throws ApplicationException {
		CouponDataGetForCustomer coupon;
		try {
			coupon = this.couponsDao.getByIdForCustomer(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupon failed" + id);

		}
		if(coupon == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return coupon;
	}

	public CouponDto getCoupon(long id) throws ApplicationException {
		CouponDto coupon;

		try {
			coupon = this.couponsDao.getById(id);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupon failed" + id);
		}
		if(coupon == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return coupon;
	}

	private boolean validateCouponExist(long id) throws ApplicationException {
		try {
			return this.couponsDao.existsById(id);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "validate coupon exist failed" + id);

		}
	}

	public void updateCoupon(CouponDto coupon) throws ApplicationException {
		validateUpdateCoupon(coupon);
		CouponEntity couponEntity = new CouponEntity(coupon);
		CompanyEntity company = companiesController.getCompany(coupon.getCompanyId());
		couponEntity.setCompany(company);
		couponEntity.setId(coupon.getId());
		try {
			this.couponsDao.save(couponEntity);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "update coupon failed" + coupon.toString());

		}
	}

	private void validateUpdateCoupon(CouponDto coupon) throws ApplicationException {
		if (validateCouponExist(coupon.getId()) == false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
//		if (coupon.getCouponName() == null) {
//			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
//		}
//
//		if (coupon.getCouponName().isEmpty()) {
//			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
//		}

	commonValidate(coupon);
	}
	
	private void commonValidate(CouponDto coupon) throws ApplicationException {

		if (coupon.getPrice() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		if (coupon.getPrice() < 1) {
			throw new ApplicationException(ErrorType.INVALID_PRICE);
		}
		if (coupon.getEndDate().before(Calendar.getInstance().getTime())) {
			throw new ApplicationException(ErrorType.INVALID_DATES);
		}
		if (coupon.getStartDate() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		if (coupon.getEndDate().before(coupon.getStartDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATES);
		}
		if (coupon.getEndDate() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}

		if (coupon.getCategory() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}

		if (coupon.getAmount() < 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT);
		}
	}

	public void deleteCoupon(long id) throws ApplicationException {
		if (validateCouponExist(id) == false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		try {
			this.couponsDao.deleteById(id);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "delete coupons failed" + id);

		}
	}

	public List<CouponDto> getAllCoupons() throws ApplicationException {
		try {
			List<CouponDto> coupons = this.couponsDao.getAll();
			return coupons;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all coupons failed");

		}
	}

	public List<CouponDataGetForCustomer> getAllCouponsForCustomer() throws ApplicationException {
		try {
			List<CouponDataGetForCustomer> coupons = this.couponsDao.getAllForCustomer();
			return coupons;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all coupons failed");

		}
	}

	public List<CouponDto> getCouponsByCompanyId(long companyId) throws ApplicationException {
		if (companiesController.validateCompanyExist(companyId) == false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		try {
			List<CouponDto> coupons = this.couponsDao.getAllByCompanyId(companyId);
			return coupons;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupons by company id failed");
		}
	}

	public List<CouponDataGetForCustomer> getCouponsByCompanyIdForCustomer(long companyId) throws ApplicationException {
		if (companiesController.validateCompanyExist(companyId) == false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		try {
			List<CouponDataGetForCustomer> coupons = this.couponsDao.getAllByCompanyIdForCustomer(companyId);
			return coupons;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupons by company id failed");
		}
	}

	public List<CouponDto> getCouponsByType(CategoryType type) throws ApplicationException {
		if (type == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		try {
			List<CouponDto> coupons = this.couponsDao.getAllByType(type);
			return coupons;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupons  by type failed");
		}
	}

	public List<CouponDataGetForCustomer> getCouponsByTypeForCustomer(CategoryType type) throws ApplicationException {
		if (type == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}
		try {
			List<CouponDataGetForCustomer> coupons = this.couponsDao.getAllByTypeForCustomer(type);
			return coupons;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupons  by type failed");
		}
	}


	// the userId is from api-userLoginData
	public List<CouponDataGetForCustomer> getCouponsByMaxPrice(long id, float maxPrice) throws ApplicationException {
		if(maxPrice < 0) {
			throw new ApplicationException(ErrorType.PRICE_MUST_BE_POSITIVE);
		}
		try {
			return couponsDao.findByMaxPrice(id, maxPrice);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get coupons by max price failed");
		}
	}

	public void deleteExpiredCoupons() throws ApplicationException {
		try {
			Date now = new Date(System.currentTimeMillis());
			this.couponsDao.deleteAllByEndDateBefore(now);

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Delete expired coupons failed");
		}
	}



}
