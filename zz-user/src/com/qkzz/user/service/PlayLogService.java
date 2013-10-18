package com.qkzz.user.service;

import com.qkzz.user.bean.PlayLog;
import com.qkzz.user.dao.PlayLogDao;
import com.qkzz.user.dao.impl.PlayLogDaoImpl;

public class PlayLogService {
	
	public static PlayLogDao dao = new PlayLogDaoImpl();
	
	
	/**
	 * 添加记录
	 * @param log
	 * @return
	 */
	public static int add(PlayLog log) {
		return dao.add(log);
	}
	
	/**
	 * 获取最近的记录
	 * @param uid
	 * @return
	 */
	public static PlayLog getLog(long uid) {
		return dao.getLog(uid);
	}
	
	
}
