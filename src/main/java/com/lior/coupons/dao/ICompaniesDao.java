package com.lior.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lior.coupons.dto.data.CompanyDataGetAllForCustomer;
import com.lior.coupons.entities.CompanyEntity;


@Repository
public interface ICompaniesDao extends CrudRepository <CompanyEntity, Long> {

	public CompanyEntity findByCompanyName(String companyName);

	@Query("select new com.lior.coupons.dto.data.CompanyDataGetAllForCustomer(c.companyName) from CompanyEntity c ")
	public List<CompanyDataGetAllForCustomer> getAllForCustomer();




}
