package com.qkzz.user.service;

import java.util.concurrent.ConcurrentHashMap;
import com.qkzz.user.dao.impl.GlobalDaoImpl;

public class GlobalService {
	private static ConcurrentHashMap<String,Integer> freshIntervalMap = new ConcurrentHashMap<String,Integer>();//刷新时间全局缓存
	
	/**
	 * 获取应用刷新时间
	 * @param appid
	 * @param subid
	 * @return
	 */
	public static int getFreshInterval(int appid,int subid) {
		String key = new StringBuffer(appid).append("-").append(subid).toString();
		if(freshIntervalMap.containsKey(key)) {
			return freshIntervalMap.get(key);
		}
		int freshInterval = new GlobalDaoImpl().getAppFreshInterval(appid, subid);
		freshIntervalMap.put(key, freshInterval);
		return freshInterval;
	}
}
