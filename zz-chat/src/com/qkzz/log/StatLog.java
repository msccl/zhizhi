package com.qkzz.log; 

import org.apache.log4j.Logger;

public class StatLog {
	private StatLog() {
	}
	private static final Logger log = Logger.getLogger(StatLog.class);

	/**
	 * 此方法在Filter中已经包含
	 */
	public static final void pvLog(int uid, String ip, String url,String comefrom , String userAgent) {
		log.info("| " + uid + " | " + ip + " | " + url + " | " + comefrom + " | "+ userAgent);
	}

}

