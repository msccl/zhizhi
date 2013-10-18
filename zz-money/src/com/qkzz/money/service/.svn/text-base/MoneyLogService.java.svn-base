package com.qkzz.money.service;

import java.util.List;

import com.qkzz.money.bean.UserMoneyLog;
import com.qkzz.money.dao.MoneyLogDao;
import com.qkzz.money.dao.impl.MoneyLogDaoImpl;



public class MoneyLogService {
	public static MoneyLogDao moneyLogInst = new MoneyLogDaoImpl();
	
	public static int addLog(int tradeid, long uid, int moneyInfoId, double money, String remark) {
		UserMoneyLog log = new UserMoneyLog();
		log.setUid(uid);
		log.setMoneyinfoid(moneyInfoId);
		log.setTradeid(tradeid);
		log.setMoney(money);
		log.setRemark(remark);
		return moneyLogInst.add(log);
	}

	
	public static List<UserMoneyLog> getMoneyLogList(long uid, int moneyInfoId, int start, int size) {
		return moneyLogInst.getMoneyLogList(uid, moneyInfoId, start, size);
	}
	

	public static int logMaxCount(long uid, int moneyInfoId) {
		return moneyLogInst.logMaxCount(uid, moneyInfoId);
	}
	
	
	/**
	 * 充值记录列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public static List<UserMoneyLog> getAddMoneyLogList(long uid, int moneyInfoId, int start, int size) {
		return moneyLogInst.getAddMoneyLogList(uid, moneyInfoId, start, size);
	}
	
	
	/**
	 * 充值记录总量
	 * @param uid
	 * @return
	 */
	public static int addMoneyLogMaxCount(long uid, int moneyInfoId) {
		return moneyLogInst.addMoneyLogMaxCount(uid, moneyInfoId);
	}

}
