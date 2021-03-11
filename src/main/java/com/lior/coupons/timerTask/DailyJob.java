package com.lior.coupons.timerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lior.coupons.exceptions.ApplicationException;
import com.lior.coupons.logic.CouponsController;

@Component
public class DailyJob {

	@Autowired
	CouponsController couponsController;

	@Scheduled(cron=("0 30 01 * * * "))
	public void deleteExpiredCoupons() throws ApplicationException {
		couponsController.deleteExpiredCoupons();
	}

}


