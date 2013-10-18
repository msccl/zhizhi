package com.qkban.service.money;

import com.qkban.bean.money.MoneyTradeBean;
import com.qkban.dao.money.MoneyTradeDao;
import com.qkban.util.ActiveBean;

public class MoneyTradeService {
	public static MoneyTradeDao moneyTradeInst = (MoneyTradeDao)ActiveBean.getBean("moneyTradeDaoImpl");

	/**
	 * tradeid是否可用
	 * @param tradeId
	 * @return
	 */
	public static boolean isLockTradeId(int tradeId) {
		MoneyTradeBean trade = moneyTradeInst.getTrade(tradeId);
		if(trade == null) {
			return true;
		}
		if(trade.getStatus() != 0) {
			return true;
		}
		
		return false;
	}
	
	public static MoneyTradeBean getTrade(int tradeid) {
		return moneyTradeInst.getTrade(tradeid);
	}
}
