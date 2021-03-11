package com.lior.coupons.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lior.coupons.dto.CompanyDto;
import com.lior.coupons.dto.data.CompanyDataGetAllForCustomer;
import com.lior.coupons.entities.CompanyEntity;
import com.lior.coupons.exceptions.ApplicationException;
import com.lior.coupons.logic.CompaniesController;



@RestController
@RequestMapping("/companies")
public class CompaniesApi {

	@Autowired
	CompaniesController companiesController;

	@PostMapping
	public long createCompany(@RequestBody CompanyDto company) throws ApplicationException {
		return this.companiesController.createCompany(company);
	}

	@GetMapping("/{companyId}")
	public CompanyEntity getCompany(@PathVariable("companyId") long id) throws ApplicationException {
		CompanyEntity company= this.companiesController.getCompany(id);
		return company;
	}

	@PutMapping
	public void updateCompany(@RequestBody CompanyDto company) throws ApplicationException {
		this.companiesController.updateCompany(company);
	}

	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long id) throws ApplicationException {
		this.companiesController.deleteCompany(id);
	}

	@GetMapping("/forAdmin")
	public List<CompanyEntity> getAllCompanies() throws ApplicationException {
		List<CompanyEntity> companies = this.companiesController.getAllCompanies();
		return companies;
	}

	@GetMapping
	public List<CompanyDataGetAllForCustomer> getAllCompaniesForCustomer() throws ApplicationException {
		List<CompanyDataGetAllForCustomer> companies= this.companiesController.getAllCompaniesForCustomer();
		return companies;
	}


}
