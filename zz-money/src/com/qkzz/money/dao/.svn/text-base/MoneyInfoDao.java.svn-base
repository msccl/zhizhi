package com.qkzz.money.dao;

import java.util.List;

import com.qkzz.money.bean.MoneyInfo;


public interface MoneyInfoDao {
	
	/**
	 * 根据ID获取货币信息
	 * @param id
	 * @return
	 */
	public MoneyInfo getMoneyInfo(int id);
	
	/**
	 * 获取某个游戏的货币信息列表
	 * @param gameid
	 * @return
	 */
	public List<MoneyInfo> getGameMoneyInfo(int gameid);
	
	
	/**
	 * 判断游戏中自定义的货币是否存在
	 * 该货币是未在dev后台添加的货币，在游戏中可以直接添加
	 * @param gameid 游戏ID，后台分配
	 * @param moneyidInGame 货币在游戏中的唯一ID，可以是字符串
	 * @return
	 */
	boolean isMoneyInGameExist(int gameid,String moneyidInGame);
	
	/**
	 * 添加货币
	 * @param bean
	 * @return
	 */
	public int addMoneyInfo(MoneyInfo bean);

	
	/**
	 * 根据货币在游戏内部的唯一ID获取货币详细信息
	 * 前提是该货币已经加入到数据库中，该方法主要用来查询未在DEV后台注册的货币
	 * @param gameid
	 * @param id
	 * @return
	 */
	public MoneyInfo getByUnregisteredMoneyId(int gameid, String id);

}
