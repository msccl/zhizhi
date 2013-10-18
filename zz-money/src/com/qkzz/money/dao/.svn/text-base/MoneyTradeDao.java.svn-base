package com.qkzz.money.dao;

import java.util.List;

import com.qkzz.money.bean.MoneyTradeBean;



public interface MoneyTradeDao {

	/**
	 * 添加trade
	 * @param moneytrade
	 * @return
	 */
	public int add(MoneyTradeBean moneytrade);
	
	/**
	 * 删除trade
	 * @param id
	 * @return
	 */
	public int del(int id);
	
	/**
	 * 更新trade
	 * @param moneytrade
	 * @return
	 */
	public int update(MoneyTradeBean moneytrade);
	
	/**
	 * 获取trade信息
	 * @param id
	 * @return
	 */
	public MoneyTradeBean getTrade(int id);
	
	/**
	 * 获取所有trade列表
	 * @return
	 */
	public List<MoneyTradeBean> getTradeList();
	
	
	/**
	 * 获取锁定的列表
	 * @return
	 */
	public List<MoneyTradeBean> getLockTradeIdList();

}
