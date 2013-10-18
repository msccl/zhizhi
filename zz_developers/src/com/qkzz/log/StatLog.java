package com.qkzz.log; 

import org.apache.log4j.Logger;

public class StatLog {
	private StatLog() {
	}
	private static final Logger log = Logger.getLogger(StatLog.class);

	/**
	 * 此方法在Filter中已经包含
	 */
	public static final void pvLog(String datetime, String ip, int uid, String page, String userAgent) {
		log.info(datetime + " | " + ip + " | " + uid + " | " + page + " | " + userAgent);
	}
	
}

