package com.qkzz.friend.dao;

import com.qkzz.friend.bean.GameActiveLog;

public interface GameActiveLogDao {

	/**
	 * 判断是否已经激活该游戏
	 * @param suid
	 * @param domain
	 * @return
	 */
	boolean isActiveGame(String suid,String domain,int gameid);

	/**
	 * 判断是否已经激活该社交网站
	 * @param suid
	 * @param domain
	 * @return
	 */
	boolean isActiveSocialSite(String suid,String domain);
	
	
	/**
	 * 添加激活记录
	 * @param obj
	 * @return
	 */
	int add(GameActiveLog obj);

	/**
	 * 获取激活记录信息
	 * @param suid
	 * @param domain
	 * @return
	 */
	GameActiveLog getLog(String suid,String domain);
	
	/**
	 * 更新吱吱uid到激活记录中
	 * @param zhizhiuid
	 * @param suid
	 * @param domain
	 * @return
	 */
	int updateZhiZhiUid(long zhizhiuid,String suid,String domain);
}
