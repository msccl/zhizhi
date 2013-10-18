package com.qkzz.log;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.qkzz.common.HttpUtil;

public class StatLog {
	private static final Logger log = Logger.getLogger(StatLog.class);

	public static final void pvLog(int uid, String ip, String url, String comefrom, String userAgent) {
		log.info("| " + uid + " | " + ip + " | " + url + " | " + comefrom + " | " + userAgent);
	}

	/**
	 * 此方法应包含在Filter中
	 */
	public static void statLog(HttpServletRequest req) {

		String referer = req.getHeader("Referer");
		String ua = req.getHeader("user-agent");

		//timem, ip, gamecode(gameid), uid, url, refer, ua
		StringBuffer sb = new StringBuffer();
		sb.append(getStringTime());
		sb.append("\t");
		sb.append(getRemoteAddr(req));
		sb.append("\t");
		sb.append(getGameCode(req));
		sb.append("\t");
		sb.append(getUserId(req));
		sb.append("\t");
		sb.append(getUrl(req));
		sb.append("\t");
		sb.append(referer);
		sb.append("\t");
		sb.append(ua);
		log.info(sb.toString());
	}

	/**
	 * 获取游戏代码
	 * @param request
	 * @return
	 */
	public static String getGameCode(HttpServletRequest request){
		String gamecode = HttpUtil.getString(request, "gameid");
		if(gamecode==null || "".equals(gamecode))
			gamecode = "0";
		return gamecode;
	}
	
	/**
	 * 获取用户ID
	 * @param request
	 * @return
	 */
	public static long getUserId(HttpServletRequest request){
		long userid =HttpUtil.getLong(request, "uid");
		if(userid==0){
			userid =HttpUtil.getLong(request, "fromid");
			if(userid==0){
				userid =HttpUtil.getLong(request, "senduid");
			}
		}
		return userid;
	}
	
	/**
	 * 获取IP地址
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
	 * 获取当前URL
	 * @return
	 */
	public static String getUrl(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		url += request.getQueryString()==null?"":"?" + request.getQueryString();
		return url;
	}

	/**
	 * 获得系统当前时间,返回格式: yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getStringTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new java.util.Date());
	}
	
}
