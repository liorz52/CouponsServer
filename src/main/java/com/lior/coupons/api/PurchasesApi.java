package com.lior.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lior.coupons.dto.PurchaseDto;
import com.lior.coupons.dto.data.PurchaseDataGet;
import com.lior.coupons.dto.data.PurchaseDataGetByUserIdForCustomer;
import com.lior.coupons.dto.data.UserLoginData;
import com.lior.coupons.exceptions.ApplicationException;
import com.lior.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchases")
public class PurchasesApi {

	@Autowired
	PurchasesController purchasesController;


	@PostMapping
	public Long createPurchase(@RequestBody PurchaseDto purchase, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		purchase.setUserId(userLoginData.getUserId());
		return this.purchasesController.createPurchase(purchase);
	}

	@GetMapping("/{purchaseId}")
	public PurchaseDataGet getPurchase(@PathVariable("purchaseId") long id) throws ApplicationException {
		PurchaseDataGet purchase= this.purchasesController.getPurchase(id);
		return purchase;
	}


	@GetMapping
	public List<PurchaseDto> getAllPurchases() throws ApplicationException {
		List<PurchaseDto> purchases = this.purchasesController.getAllPurchases();
		return purchases;
	}

	@GetMapping("/byUserId")
	public List<PurchaseDto> getAllPrchasesByUserId(@RequestParam ("userId") long userId) throws ApplicationException {
		List<PurchaseDto> purchases = this.purchasesController.getAllPrchasesByUserId(userId);
		return purchases;
	} 

	@GetMapping("/byUserIdForCustomer")
	public List<PurchaseDataGetByUserIdForCustomer> getAllPrchasesByUserIdForCustomer( HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		long userId= userLoginData.getUserId();
		List<PurchaseDataGetByUserIdForCustomer> purchases = this.purchasesController.getAllPrchasesByUserIdForCustomer(userId);
		return purchases;
	} 

	@GetMapping("/byCompanyIdForCompany")
	public List<PurchaseDto> getAllPurchasesByCompanyId(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData= (UserLoginData) request.getAttribute("userLoginData");
		long companyId= userLoginData.getCompanyId();
		List<PurchaseDto> purchases = this.purchasesController.getAllPurchasesByCompanyId(companyId);
		return purchases;
	}

	@GetMapping("/byCompanyId")
	public List<PurchaseDto> getAllPurchasesByCompanyId(@RequestParam ("companyId") long companyId) throws ApplicationException {
		List<PurchaseDto> purchases = this.purchasesController.getAllPurchasesByCompanyId(companyId);
		return purchases;
	}

	@DeleteMapping("/{purchasesId}")
	public void deletePurchase(@PathVariable("purchasesId") long id) throws ApplicationException {
		this.purchasesController.deletePurchase(id);
	}


}
