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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lior.coupons.dto.CouponDto;
import com.lior.coupons.dto.data.CouponDataGetForCustomer;
import com.lior.coupons.dto.data.UserLoginData;
import com.lior.coupons.enums.CategoryType;
import com.lior.coupons.exceptions.ApplicationException;
import com.lior.coupons.logic.CouponsController;
@RestController
@RequestMapping("/coupons")
public class CouponsApi {

	@Autowired
	CouponsController couponsController;

	@PostMapping
	public long creatCoupon(@RequestBody CouponDto coupon, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		coupon.setCompanyId(userLoginData.getCompanyId());
		return this.couponsController.createCoupon(coupon);
	}

	@GetMapping("/forCustomer/{couponId}")
	public CouponDataGetForCustomer getCouponForCustomer(@PathVariable("couponId") long id) throws ApplicationException {
		CouponDataGetForCustomer coupon= this.couponsController.getCouponForCustomer(id);
		return coupon;
	}


	@GetMapping("/{couponId}")
	public CouponDto getCoupon(@PathVariable("couponId") long id) throws ApplicationException {
		CouponDto coupon= (CouponDto) this.couponsController.getCoupon(id);
		return coupon;
	}

	@PutMapping
	public void updateCoupon(@RequestBody CouponDto coupon, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		coupon.setCompanyId(userLoginData.getCompanyId());
		this.couponsController.updateCoupon(coupon);
	}

	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long id) throws ApplicationException {
		this.couponsController.deleteCoupon(id);
	}


	@GetMapping
	public List<CouponDto> getAllCoupons() throws ApplicationException {
		List<CouponDto> coupons = this.couponsController.getAllCoupons();
		return coupons;
	}

	@GetMapping("/allForCustomer")
	public List<CouponDataGetForCustomer> getAllCouponsForCustomer() throws ApplicationException {
		List<CouponDataGetForCustomer> coupons = this.couponsController.getAllCouponsForCustomer();
		return coupons;
	}

	@GetMapping("/byCompanyId")
	public List<CouponDto> getCouponsByCompanyId(@RequestParam("companyId")long companyId) throws ApplicationException{
		List<CouponDto> coupons= this.couponsController.getCouponsByCompanyId(companyId);
		return coupons;
	}
	

	@GetMapping("/byCompanyIdForCompany")
	public List<CouponDto> getCouponsByCompanyIdForCompany(HttpServletRequest request) throws ApplicationException{
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		long companyId= userLoginData.getCompanyId();
		List<CouponDto> coupons= this.couponsController.getCouponsByCompanyId(companyId);
		return coupons;
	}

	@GetMapping("/byCompanyIdForCustomer")
	public List<CouponDataGetForCustomer> getCouponsByCompanyIdForCustomer(@RequestParam("companyId")long companyId) throws ApplicationException{
		List<CouponDataGetForCustomer> coupons= this.couponsController.getCouponsByCompanyIdForCustomer(companyId);
		return coupons;
	}

	@GetMapping("/byType")
	public List<CouponDto> getCouponsByType(@RequestParam("type")CategoryType type) throws ApplicationException{
		List<CouponDto> coupons= this.couponsController.getCouponsByType(type);
		return coupons;
	}

	@GetMapping("/byTypeForCustomer")
	public List<CouponDataGetForCustomer> getCouponsByTypeForCustomer(@RequestParam("type")CategoryType type) throws ApplicationException{
		List<CouponDataGetForCustomer> coupons= this.couponsController.getCouponsByTypeForCustomer(type);
		return coupons;
	}

	@GetMapping("/byMaxPrice")
	public List<CouponDataGetForCustomer> getCouponsByMaxPrice( HttpServletRequest request, @RequestParam("maxPrice") Float maxPrice) throws ApplicationException{
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		long userId=userLoginData.getUserId();
		List<CouponDataGetForCustomer> coupons= this.couponsController.getCouponsByMaxPrice(userId,maxPrice);
		return coupons; 
	}

}
