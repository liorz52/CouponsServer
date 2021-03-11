package com.lior.coupons.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.lior.coupons.dto.data.PurchaseDataGet;
import com.lior.coupons.dto.data.PurchaseDataGetByUserIdForCustomer;
import com.lior.coupons.dto.PurchaseDto;
import com.lior.coupons.entities.PurchaseEntity;

@Repository
public interface IPurchasesDao extends CrudRepository <PurchaseEntity, Long> {


	public List<PurchaseEntity> findByUserId(long userId);


	@Query("select new com.lior.coupons.dto.data.PurchaseDataGet(p.user.userName, p.coupon.couponName, "
			+ "p.coupon.company.companyName, p.timestamp, p.amount) from PurchaseEntity p where p.id = ?1")
	public PurchaseDataGet getById(long id); 

	@Query("select new com.lior.coupons.dto.PurchaseDto(p.id, p.user.id, p.coupon.couponName, p.coupon.id, p.amount,"
			+ " p.timestamp) from PurchaseEntity p")
	public List<PurchaseDto> getAll(); 

	@Query("select new com.lior.coupons.dto.PurchaseDto(p.id, p.user.id, p.coupon.couponName, p.coupon.id, p.amount,"
			+ " p.timestamp) from PurchaseEntity p where p.user.id=?1")
	public  List<PurchaseDto> getAllByUserId(long userId); 

	@Query("select new com.lior.coupons.dto.data.PurchaseDataGetByUserIdForCustomer(p.id, p.coupon.couponName, p.coupon.company.companyName, p.amount,"
			+ " p.timestamp) from PurchaseEntity p where p.user.id=?1")
	public  List<PurchaseDataGetByUserIdForCustomer> getAllByUserIdForCustomer(long userId); 

	@Query("select new com.lior.coupons.dto.PurchaseDto(p.id, p.user.id, p.coupon.couponName, p.coupon.id, p.amount,"
			+ " p.timestamp) from PurchaseEntity p where p.coupon.company.id=?1")
	public  List<PurchaseDto> getAllByCompanyId(long companyId); 


}