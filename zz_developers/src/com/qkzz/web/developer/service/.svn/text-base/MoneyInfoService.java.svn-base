package com.qkzz.web.developer.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.developer.bean.MoneyInfo;
import com.qkzz.web.developer.dao.MoneyInfoDao;

public class MoneyInfoService {

	private static MoneyInfoDao dao = ActiveBean.getBean("moneyInfoDaoImpl", MoneyInfoDao.class);

	/**
	 * 添加货币信息
	 * @param bean
	 * @return
	 */
	public static int addMoneyInfo(MoneyInfo bean) {
		return dao.addMoneyInfo(bean);
	}
	
	
	/**
	 * 获取最新插入的货币ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public static int getLastInsertID(int gameid,String name) {
		return dao.getLastInsertID(gameid, name);
	}
	
	/**
	 * 编辑货币信息
	 * @param bean
	 * @return
	 */
	public static int editMoneyInfo(MoneyInfo bean) {
		return dao.editMoneyInfo(bean);
	}
	
	/**
	 * 删除货币
	 * @param id
	 * @return
	 */
	public static int delMoneyInfo(int id) {
		return dao.delMoneyInfo(id);
	}
	
	/**
	 * 根据ID获取货币信息
	 * @param id
	 * @return
	 */
	public static MoneyInfo getMoneyInfo(int id) {
		return dao.getMoneyInfo(id);
	}
	
	/**
	 * 获取某个游戏的货币信息
	 * @param gameid
	 * @return
	 */
	public static List<MoneyInfo> getGameMoneyInfo(int gameid,int startIndex,int num) {
		return dao.getGameMoneyInfo(gameid,startIndex,num);
	}
	
	/**
	 * 获取游戏总量
	 * @param gameid
	 * @return
	 */
	public static int getGameMoneyMaxCount(int gameid) {
		return dao.getGameMoneyMaxCount(gameid);
	}

}
