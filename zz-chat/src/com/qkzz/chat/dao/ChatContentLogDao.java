package com.qkzz.chat.dao;

import com.qkzz.chat.bean.ChatContentLog;

public interface ChatContentLogDao {

	/**
	 * 添加log
	 * @param log
	 * @return
	 */
	int addLog(ChatContentLog log);
	
	/**
	 * 获取用户的最新日志信息
	 * @param uid
	 * @return
	 */
	ChatContentLog getLatestLog(long uid);
}
