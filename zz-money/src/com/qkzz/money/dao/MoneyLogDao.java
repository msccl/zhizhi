package com.qkzz.money.dao;

import java.util.List;

import com.qkzz.money.bean.UserMoneyLog;



public interface MoneyLogDao {

	/**
	 * 添加log
	 * @param log
	 * @return
	 */
	public int add(UserMoneyLog log);
	
	/**
	 * 用户log最大数量
	 * @param uid
	 * @return
	 */
	public int logMaxCount(long uid, int moneyInfoId);
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserMoneyLog> getMoneyLogList(long uid, int moneyInfoId, int start, int size);
	
	/**
	 * 充值记录列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserMoneyLog> getAddMoneyLogList(long uid, int moneyInfoId, int start,int size);
	
	
	/**
	 * 充值记录总量
	 * @param uid
	 * @return
	 */
	public int addMoneyLogMaxCount(long uid, int moneyInfoId);
	
}
