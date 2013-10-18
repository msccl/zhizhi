package com.qkzz.money.service;

import com.qkzz.money.bean.MoneyTradeBean;
import com.qkzz.money.dao.MoneyTradeDao;
import com.qkzz.money.dao.impl.MoneyTradeDaoImpl;


public class MoneyTradeService {
	public static MoneyTradeDao moneyTradeInst = new MoneyTradeDaoImpl();

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
