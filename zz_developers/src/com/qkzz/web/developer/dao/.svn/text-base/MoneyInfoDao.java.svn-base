package com.qkzz.web.developer.dao;

import java.util.List;

import com.qkzz.web.developer.bean.MoneyInfo;

public interface MoneyInfoDao {
	
	/**
	 * 添加货币信息
	 * @param bean
	 * @return
	 */
	public int addMoneyInfo(MoneyInfo bean);
	
	/**
	 * 获取最新插入的货币ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public int getLastInsertID(int gameid,String name);
	
	/**
	 * 编辑货币信息
	 * @param bean
	 * @return
	 */
	public int editMoneyInfo(MoneyInfo bean);
	
	/**
	 * 删除货币
	 * @param id
	 * @return
	 */
	public int delMoneyInfo(int id);
	
	/**
	 * 根据ID获取货币信息
	 * @param id
	 * @return
	 */
	public MoneyInfo getMoneyInfo(int id);
	
	/**
	 * 获取某个游戏的货币信息
	 * @param gameid
	 * @return
	 */
	public List<MoneyInfo> getGameMoneyInfo(int gameid,int startIndex,int num);
	
	/**
	 * 游戏货币总量
	 * @param gameid
	 * @return
	 */
	public int getGameMoneyMaxCount(int gameid);
	
}
