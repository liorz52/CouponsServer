package com.lior.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lior.coupons.dao.ICompaniesDao;
import com.lior.coupons.dto.CompanyDto;
import com.lior.coupons.dto.data.CompanyDataGetAllForCustomer;
import com.lior.coupons.entities.CompanyEntity;
import com.lior.coupons.enums.ErrorType;
import com.lior.coupons.exceptions.ApplicationException;

@Controller
public class CompaniesController {

	@Autowired
	private ICompaniesDao companiesDao;

	public long createCompany(CompanyDto company) throws ApplicationException {
		validateCreateCompany(company);
		CompanyEntity companyEntity = new CompanyEntity(company);
		try {
			companyEntity = this.companiesDao.save(companyEntity);
			long id = companyEntity.getId();
			return id;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "create company failed" + company.toString());
		}

	}

	private void validateCreateCompany(CompanyDto company) throws ApplicationException {
		try {
			if(companiesDao.findByCompanyName(company.getCompanyName())!= null) {
				throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS);
			}

		}catch (Exception e) {
			if( e instanceof ApplicationException) {
				throw e;
			}
			throw new ApplicationException(ErrorType.GENERAL_ERROR,"CRUD error");
		}

		if (company.getCompanyName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (company.getCompanyName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME);
		}

		if (company.getCompanyName().length() < 2) {
			throw new ApplicationException(ErrorType.MUST_BE_2_CHARACTERS);
		}
		commonValidate(company);
	}

	public CompanyEntity getCompany(long id) throws ApplicationException {
		CompanyEntity company;
		try {
			company = this.companiesDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get company failed" + id);
		}

		if(company == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		return company;


	}



	//only admin can update company
	public void updateCompany(CompanyDto company) throws ApplicationException {
		CompanyEntity companyEntity = new CompanyEntity(company);
		companyEntity.setId(company.getId());
		validateupdateCompany(company);
		try {
			this.companiesDao.save(companyEntity);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "update company failed" + company.toString());
		}
	}

	private void validateupdateCompany(CompanyDto company) throws ApplicationException {
		if(company.getId()<1) {
			throw new ApplicationException(ErrorType.ID_MUST_BE_POSITIVE);
		}
		if( validateCompanyExist(company.getId())==false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		commonValidate(company);
	}
	
	private void commonValidate(CompanyDto company) throws ApplicationException {
		if (company.getAddress() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_ADDRESS);
		}

		if (company.getAddress().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_ADDRESS);
		}

		if (company.getPhoneNumber() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}

		if (company.getPhoneNumber() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE);
		}

		if (company.getPhoneNumber() == null) {
			throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER);
		}
	}

	public void deleteCompany(long id) throws ApplicationException {
		if(validateCompanyExist(id)==false) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
		}
		try {
			companiesDao.deleteById(id);
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "delete company failed" + id);
		}
	}

	public List<CompanyEntity> getAllCompanies() throws ApplicationException {
		try {
			List<CompanyEntity> companies = (List<CompanyEntity>) this.companiesDao.findAll();
			return companies;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all companies failed");

		}
	}

	public List<CompanyDataGetAllForCustomer> getAllCompaniesForCustomer() throws ApplicationException {
		try {
			List<CompanyDataGetAllForCustomer> companies = companiesDao.getAllForCustomer();
			return companies;
		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "get all companies failed");

		}
	}


	boolean validateCompanyExist(long id) throws ApplicationException {
		try {
			return companiesDao.existsById(id);

		} catch (Exception e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "validate company exist failed" + id);
		}
	}
}