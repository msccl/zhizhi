package com.qkzz.money.service;

import java.util.List;

import com.qkzz.common.Tools;
import com.qkzz.money.bean.MoneyInfo;
import com.qkzz.money.dao.MoneyDao;
import com.qkzz.money.dao.MoneyInfoDao;
import com.qkzz.money.dao.impl.MoneyDaoImpl;
import com.qkzz.money.dao.impl.MoneyInfoDaoImpl;


public class MoneyService {

	private static MoneyInfoDao moneyInfoInst = new MoneyInfoDaoImpl();
	private static MoneyDao moneyInst = new MoneyDaoImpl();
	
	/**
	 * 执行货币增加操作
	 * @param uid 用户ID
	 * @param moneyInfoId 货币ID
	 * @param money 货币数值
	 * @param tradeId 操作类型ID
	 * @param remark 注释
	 * @param cls 类引用，通常为this
	 * @return
	 */
	public static int incMoney(long uid, int moneyInfoId,double money, int tradeId, String remark,Object cls) {

		//moneyInfoId=0为吱币
		if(moneyInfoInst.getMoneyInfo(moneyInfoId) == null && moneyInfoId != 0) {
			//货币不存在
			return -1;
		}
		//游客状态下不能操作
//		if (UserService.isGuest(uid)) {
//			return -1;
//		}

		if (money < 0) {
			return -2;
		}
		
		if (MoneyTradeService.isLockTradeId(tradeId)) {
			return -3;// trade id is lock
		}

		double userMoney = MoneyService.getMoney(uid,moneyInfoId);
		if (userMoney < 0) {
			return -4;
		}

		if(moneyInst.incMoney(uid, moneyInfoId, money) != -1) {
			// add money inc log
			remark = remark + "@" + Tools.getLocalHostName()
					+ (cls == null ? "-NoClass:" : cls.getClass().getName())
					+ ":" + userMoney;
			int result = MoneyLogService.addLog(tradeId, uid, moneyInfoId, money, remark);
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
	
	/**
	 * 执行货币增加操作
	 * @param uid 用户ID
	 * @param moneyInfoId 货币ID
	 * @param money 货币数值
	 * @param tradeId 操作类型ID
	 * @param remark 注释
	 * @param cls 类引用，通常为this
	 * @return
	 */
	public static int decMoney(long uid, int moneyInfoId, double money, int tradeId, String remark,Object cls) {

		if(moneyInfoInst.getMoneyInfo(moneyInfoId) == null && moneyInfoId != 0) {
			//货币不存在
			return -1;
		}

		//游客状态下不能操作
//		if (UserService.isGuest(uid)) {
//			return -1;
//		}
		if (money < 0) {
			return -2;
		}
		if (MoneyTradeService.isLockTradeId(tradeId)) {
			return -3;// trade id is lock
		}
		if (MoneyService.isMoneyBlack(uid, moneyInfoId)) {
			return -4;
		}
		double userMoney = MoneyService.getMoney(uid, moneyInfoId);
		if (userMoney < money || userMoney < 0) {
			return -5;
		}
		if (moneyInst.decMoney(uid, moneyInfoId, money) != -1) {
			remark = remark + "@" + Tools.getLocalHostName()
					+ (cls == null ? "-NoClass:" : cls.getClass().getName())
					+ ":" + userMoney;
			int result = MoneyLogService.addLog(tradeId, uid, moneyInfoId, money, remark);
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
	
	/**
	 * 获取用户某种货币的数量
	 * @param uid
	 * @param moneyInfoId
	 * @return
	 */
	public static double getMoney(long uid,int moneyInfoId) {
		return moneyInst.getMoney(uid, moneyInfoId);
	}
	
	/**
	 * 用户账户是否被加黑
	 * @param uid 用户ID
	 * @param moneyInfoId 货币ID
	 * @return
	 */
	public static boolean isMoneyBlack(long uid, int moneyInfoId) {
		return moneyInst.isMoneyBlack(uid, moneyInfoId);
	}
	
	/**
	 * 用户资金账户冻结
	 * @param uid 用户ID
	 * @param moneyInfoId 货币ID
	 * @return
	 */
	public static int setMoneyAccountLock(long uid, int moneyInfoId) {
		return moneyInst.changeStatus(uid, moneyInfoId, 1);
	}

	/**
	 * 用户资金账户解冻
	 * @param uid 用户ID
	 * @param moneyInfoId 货币ID
	 * @return
	 */
	public static int setMoneyAccountUnlock(long uid, int moneyInfoId) {
		return moneyInst.changeStatus(uid, moneyInfoId, 0);
	}

	
	/**
	 * 根据ID获取货币信息
	 * @param moneyInfoId
	 * @return
	 */
	public static MoneyInfo getMoneyInfo(int moneyInfoId) {
		return moneyInfoInst.getMoneyInfo(moneyInfoId);
	}

	
	/**
	 * 获取游戏中货币列表
	 * @param gameid
	 * @return
	 */
	public static List<MoneyInfo> getGameMoneyInfo(int gameid) {
		return moneyInfoInst.getGameMoneyInfo(gameid);
	}
	
	
	/**
	 * 更改支付密码
	 * @param uid
	 * @param moneyInfoId
	 * @param newpwd
	 * @return
	 */
	public static int modifyPwdPassword(long uid, int moneyInfoId, String newpwd) {
		return moneyInst.modifyPayPassword(uid, moneyInfoId, newpwd);
	}
	

	/**
	 * 直接更改金额
	 * @param uid
	 * @param moneyInfoId
	 * @param money
	 * @return
	 */
	public static int modifyMoney(long uid, int moneyInfoId, double money) {
		return moneyInst.modifyMoney(uid, moneyInfoId, money);
	}
	

	/**
	 * 验证支付密码是否正确
	 * @param uid
	 * @param moneyInfoId
	 * @param password
	 * @return
	 */
	public static boolean isValidatePassword(long uid, int moneyInfoId, String password) {
		return moneyInst.isValidatePassword(uid, moneyInfoId, password);
	}


	/**
	 * 获取支付密码
	 * @param uid
	 * @param moneyInfoId
	 * @return
	 */
	public static String getMoneyPayPassword(long uid, int moneyInfoId) {
		return moneyInst.getMoneyPayPassword(uid,moneyInfoId);
	}
	
	
	
	/**
	 * 根据货币在游戏内部的唯一ID获取货币详细信息
	 * 前提是该货币已经加入到数据库中，该方法主要用来查询未在DEV后台注册的货币
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static MoneyInfo getByUnregisteredMoneyId(int gameid, String id) {
		return moneyInfoInst.getByUnregisteredMoneyId(gameid, id);
	}


	/**
	 * 判断游戏中自定义的货币是否存在
	 * 该货币是未在dev后台添加的货币，在游戏中可以直接添加
	 * @param gameid 游戏ID，后台分配
	 * @param moneyidInGame 货币在游戏中的唯一ID，可以是字符串
	 * @return
	 */
	public static boolean isMoneyInGameExist(int gameid,String moneyidInGame) {
		return moneyInfoInst.isMoneyInGameExist(gameid, moneyidInGame);
	}

	
	/**
	 * 在游戏中直接添加未注册的货币
	 * @param bean
	 * @return
	 */
	public static int addMoneyInfo(MoneyInfo bean) {
		return moneyInfoInst.addMoneyInfo(bean);
	}

}
