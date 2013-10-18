package com.qkzz.online.dao;

import java.util.List;

import com.qkzz.online.bean.Online;
import com.qkzz.user.bean.User;


public interface OnlineDao {
	
	/**
	 * 获取随机玩家列表
	 * @param gameid
	 * @param count
	 * @return
	 */
	List<Online> getRandUserList(int count);
	
	/**
	 * 判断用户是否全局在线
	 * 不包含游客
	 * @param uid
	 * @return
	 */
	boolean isGlobalOnlne(long uid);
	
	
	/**
	 * 判断用户是否全局在线，需要判断用户签名
	 * @param uid
	 * @param sign
	 * @return
	 */
	boolean isGlobalOnlne(long uid,String sign);

	
	/**
	 * 从在线表中获取用户
	 * @param uid
	 * @return
	 */
	User getByUser(long uid);
	
	/**
	 * 主动清除在线记录
	 * @param uid
	 * @return
	 */
	int clearOnline(long uid);
}
