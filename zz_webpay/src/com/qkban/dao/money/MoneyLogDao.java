package com.qkban.dao.money;

import java.util.List;

import com.qkban.bean.money.UserMoneyLogBean;


public interface MoneyLogDao {

	/**
	 * 添加log
	 * @param log
	 * @return
	 */
	public int add(UserMoneyLogBean log);
	
	/**
	 * 用户log�?大数�?
	 * @param uid
	 * @return
	 */
	public int logMaxCount(int uid);
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserMoneyLogBean> getMoneyLogList(int uid,int start,int size);
	
	/**
	 * 充�?�记录列�?
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserMoneyLogBean> getAddMoneyLogList(int uid,int start,int size);
	
	
	/**
	 * 充�?�记录�?�量
	 * @param uid
	 * @return
	 */
	public int addMoneyLogMaxCount(int uid);
	
}
