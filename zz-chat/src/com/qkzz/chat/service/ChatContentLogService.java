package com.qkzz.chat.service;

import com.qkzz.chat.bean.ChatContentLog;
import com.qkzz.chat.dao.ChatContentLogDao;
import com.qkzz.chat.dao.impl.ChatContentLogDaoImpl;

public class ChatContentLogService {

	private static ChatContentLogDao dao = new ChatContentLogDaoImpl();
	
	/**
	 * 添加log
	 * @param log
	 * @return
	 */
	public static int addLog(ChatContentLog log) {
		return dao.addLog(log); 
	}
	
	/**
	 * 获取用户的最新日志信息
	 * @param uid
	 * @return
	 */
	public static ChatContentLog getLatestLog(long uid) {
		return dao.getLatestLog(uid);
	}

}
