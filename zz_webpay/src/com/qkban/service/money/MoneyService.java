package com.qkban.service.money;

import com.qkban.dao.money.MoneyDao;
import com.qkban.util.ActiveBean;
import com.qkban.util.Tools;


public class MoneyService {

	public static MoneyDao moneyInst = (MoneyDao)ActiveBean.getBean("moneyDaoImpl");
	
	public static int incMoney(int uid, double money, int tradeId, String remark,Object cls) {

		//游客状�?�下不能操作
//		if (UserService.isGuest(uid)) {
//			return -1;
//		}

		if (money < 0) {
			return -2;
		}
		
		if (MoneyTradeService.isLockTradeId(tradeId)) {
			return -3;// trade id is lock
		}

		double userMoney = MoneyService.getMoney(uid);
		if (userMoney < 0) {
			return -4;
		}

		if(moneyInst.incMoney(uid, money) != -1) {
			// add money inc log
			remark = remark + "@" + Tools.getLocalHostName()
					+ (cls == null ? "-NoClass:" : cls.getClass().getName())
					+ ":" + userMoney;
			int result = MoneyLogService.addLog(tradeId, uid, money,remark);
			if (result <= 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("tradeId:");
				sb.append(tradeId);
				sb.append("\tuid:");
				sb.append(uid);
				sb.append("\tmoney:");
				sb.append(money);
				sb.append("\tremark:");
				sb.append(remark);
				sb.append("\tcls:");
				sb.append(cls);
//				Log4jUtil.info(sb.toString(), "MoneylogFailed");
			}
			return 0;
		}
		return -5;
	}
	
	public static int decMoney(int uid, double money, int tradeId, String remark,Object cls) {

		//游客状�?�下不能操作
//		if (UserService.isGuest(uid)) {
//			return -1;
//		}
		if (money < 0) {
			return -2;
		}
		if (MoneyTradeService.isLockTradeId(tradeId)) {
			return -3;// trade id is lock
		}
		if (MoneyService.isMoneyBlack(uid)) {
			return -4;
		}
		double userMoney = MoneyService.getMoney(uid);
		if (userMoney < money || userMoney < 0) {
			return -5;
		}
		if (moneyInst.decMoney(uid, money) != -1) {
			remark = remark + "@" + Tools.getLocalHostName()
					+ (cls == null ? "-NoClass:" : cls.getClass().getName())
					+ ":" + userMoney;
			int result = MoneyLogService.addLog(tradeId, uid, money,remark);
			if (result <= 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("tradeId:");
				sb.append(tradeId);
				sb.append("\tuid:");
				sb.append(uid);
				sb.append("\tmoney:");
				sb.append(money);
				sb.append("\tremark:");
				sb.append(remark);
				sb.append("\tcls:");
				sb.append(cls);
//				Log4jUtil.info(sb.toString(), "MoneylogFailed");
			}
			return 0;
		}
		return -6;
		
	}
	
	
	public static double getMoney(int uid) {
		return moneyInst.getMoney(uid);
	}
	
	public static boolean isMoneyBlack(int uid) {
		return moneyInst.isMoneyBlack(uid);
	}
	
	public int setMoneyAccountLock(int uid) {
		return moneyInst.changeStatus(uid, 1);
	}

	public int setMoneyAccountUnlock(int uid) {
		return moneyInst.changeStatus(uid, 0);
	}

	/**
	 * 更改支付密码
	 * @param uid
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 */
	public static int modifyPwdPassword(int uid,String newpwd) {
		return moneyInst.modifyPayPassword(uid, newpwd);
	}
	
	/**
	 * 直接更改金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public static int modifyMoney(int uid,double money) {
		return moneyInst.modifyMoney(uid, money);
	}
	
	/**
	 * 验证支付密码是否正确
	 * @param uid
	 * @param password
	 * @return
	 */
	public static boolean isValidatePassword(int uid,String password) {
		return moneyInst.isValidatePassword(uid, password);
	}

	/**
	 * 获取支付密码
	 * @param uid
	 * @return
	 */
	public static String getMoneyPayPassword(int uid) {
		return moneyInst.getMoneyPayPassword(uid);
	}
	
	/**
	 * 增加资金账户，可以设定糖豆初始�??
	 * @param uid
	 * @param initMoney
	 * @return
	 */
	public static int addMoneyAccount(int uid,double initMoney) {
		return moneyInst.addMoneyAccount(uid, initMoney);
	}
	
	
}
