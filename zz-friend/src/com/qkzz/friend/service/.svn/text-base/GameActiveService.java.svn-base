package com.qkzz.friend.service;

import com.qkzz.friend.bean.GameActiveLog;
import com.qkzz.friend.dao.GameActiveLogDao;
import com.qkzz.friend.dao.impl.GameActiveLogDaoImpl;

public class GameActiveService {
	
	private static GameActiveLogDao dao = new GameActiveLogDaoImpl();

	/**
	 * 判断是否已经激活该社交网站
	 * @param suid
	 * @param domain
	 * @return
	 */
	public static boolean isActiveSocialSite(String suid,String domain) {
		return dao.isActiveSocialSite(suid, domain);
	}
	
	
	/**
	 * 判断是否已经激活该游戏
	 * @param suid
	 * @param domain
	 * @return
	 */
	public static boolean isActiveGame(String suid,String domain,int gameid) {
		return dao.isActiveGame(suid, domain, gameid);
	}


	/**
	 * 添加激活记录
	 * @param obj
	 * @return
	 */
	public static int add(GameActiveLog obj) {
		return dao.add(obj);
	}

	/**
	 * 获取激活记录信息
	 * @param suid
	 * @param domain
	 * @return
	 */
	public static GameActiveLog getLog(String suid,String domain) {
		return dao.getLog(suid, domain);
	}
	
	/**
	 * 更新吱吱uid到激活记录中
	 * @param zhizhiuid
	 * @param suid
	 * @param domain
	 * @return
	 */
	public static int updateZhiZhiUid(long zhizhiuid,String suid,String domain) {
		return dao.updateZhiZhiUid(zhizhiuid, suid, domain);
	}


}
