package com.lior.coupons.dao;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lior.coupons.dto.CouponDto;
import com.lior.coupons.dto.data.CouponDataGetForCustomer;
import com.lior.coupons.entities.CouponEntity;
import com.lior.coupons.enums.CategoryType;


@Repository
public interface ICouponsDao extends CrudRepository <CouponEntity, Long> {






	public CouponEntity findByCouponName(String couponName);


	@Query("select new com.lior.coupons.dto.data.CouponDataGetForCustomer( c.id, c.couponName, c.company.companyName," 
			+ " c.description, c.price) from CouponEntity c where c.id in (select p.coupon "
			+ "from PurchaseEntity p where p.user.id = :userId) and c.price < :maxPrice")
	public List<CouponDataGetForCustomer> findByMaxPrice(@Param("userId") long userId, @Param("maxPrice") float maxPrice);

	@Transactional
	@Modifying
	public void deleteAllByEndDateBefore(Date now);

	@Query("select new com.lior.coupons.dto.data.CouponDataGetForCustomer(c.id ,c.couponName, c.company.companyName,"
			+ " c.description, c.price) from CouponEntity c where c.id=?1")
	public CouponDataGetForCustomer getByIdForCustomer(long id);

	@Query("select new com.lior.coupons.dto.CouponDto(c.id, c.couponName, c.price, c.description, c.startDate, c.endDate,"
			+ " c.category, c.amount, c.company.id) from CouponEntity c where c.id=?1")
	public CouponDto getById(long id);

	@Query("select new com.lior.coupons.dto.data.CouponDataGetForCustomer(c.id,c.couponName, c.company.companyName,"
			+ " c.description, c.price) from CouponEntity c ")
	public List<CouponDataGetForCustomer> getAllForCustomer();

	@Query("select new com.lior.coupons.dto.CouponDto(c.id, c.couponName, c.price, c.description, c.startDate, c.endDate,"
			+ " c.category, c.amount, c.company.id) from CouponEntity c")
	public List<CouponDto> getAll();

	@Query("select new com.lior.coupons.dto.data.CouponDataGetForCustomer(c.id, c.couponName, c.company.companyName,"
			+ " c.description, c.price) from CouponEntity c where c.company.id=?1")
	public List<CouponDataGetForCustomer> getAllByCompanyIdForCustomer(long companyId);

	@Query("select new com.lior.coupons.dto.CouponDto(c.id, c.couponName, c.price, c.description, c.startDate, c.endDate,"
			+ " c.category, c.amount, c.company.id) from CouponEntity c where c.company.id=?1")
	public List<CouponDto> getAllByCompanyId(long companyId);


	@Query("select new com.lior.coupons.dto.data.CouponDataGetForCustomer(c.id, c.couponName, c.company.companyName,"
			+ " c.description, c.price) from CouponEntity c where c.category=?1")
	public List<CouponDataGetForCustomer> getAllByTypeForCustomer(CategoryType type);

	@Query("select new com.lior.coupons.dto.CouponDto(c.id, c.couponName, c.price, c.description, c.startDate, c.endDate,"
			+ " c.category, c.amount, c.company.id) from CouponEntity c where c.category=?1")
	public List<CouponDto> getAllByType(CategoryType type);



}
