package com.qkzz.user.dao;

public interface GlobalDao {

	/**
	 * 获取应用刷新调用时间
	 * @param appid
	 * @param subid
	 * @return
	 */
	int getAppFreshInterval(int appid,int subid);
}
