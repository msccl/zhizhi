package com.qkzz.log;

import org.apache.log4j.Logger;

/**
 * 聊天日志
 * @author Administrator
 *
 */
public class ChatLog {
	private ChatLog() {
	}
	private static final Logger log = Logger.getLogger(ChatLog.class);

	public static final void chatLog(long fromuid,String fromname,long destid,String destname,String content) {
		log.info(new StringBuffer("| ").append(fromuid).append(" | ").append(fromname).
				append(" | ").append(destid).append(" | ").append(destname).append(" | ").
				append("\"").append(content).append("\"").toString());
	}

}
