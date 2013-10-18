package com.qkban.service.money;

import java.util.List;

import com.qkban.bean.money.UserMoneyLogBean;
import com.qkban.dao.money.MoneyLogDao;
import com.qkban.util.ActiveBean;

public class MoneyLogService {
	public static MoneyLogDao moneyLogInst = (MoneyLogDao)ActiveBean.getBean("moneyLogDaoImpl");
	
	public static int addLog(int tradeid, int uid, double money, String remark) {
		UserMoneyLogBean log = new UserMoneyLogBean();
		log.setUid(uid);
		log.setTradeid(tradeid);
		log.setMoney(money);
		log.setRemark(remark);
		return moneyLogInst.add(log);
	}

	
	public static List<UserMoneyLogBean> getMoneyLogList(int uid, int start, int size) {
		return moneyLogInst.getMoneyLogList(uid, start, size);
	}
	

	public static int logMaxCount(int uid) {
		return moneyLogInst.logMaxCount(uid);
	}
	
	
	/**
	 * 充�?�记录列�?
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public static List<UserMoneyLogBean> getAddMoneyLogList(int uid,int start,int size) {
		return moneyLogInst.getAddMoneyLogList(uid, start, size);
	}
	
	
	/**
	 * 充�?�记录�?�量
	 * @param uid
	 * @return
	 */
	public static int addMoneyLogMaxCount(int uid) {
		return moneyLogInst.addMoneyLogMaxCount(uid);
	}

}
