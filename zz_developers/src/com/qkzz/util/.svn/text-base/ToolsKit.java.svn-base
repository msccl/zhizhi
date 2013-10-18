package com.qkzz.util;

import javax.servlet.http.HttpServletRequest;

public class ToolsKit {

	
	/**
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("X-Real-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}
	
	/**
	 * get active URL
	 * @return
	 */
	public static String getUrl(HttpServletRequest request){
		String url = request.getRequestURI();
		url += request.getQueryString()==null?"":"?" + request.getQueryString();
		return url;
	}
	

}
