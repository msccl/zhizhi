package com.qkzz.log;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.qkzz.common.HttpUtil;
import com.qkzz.common.Tools;

/**
 * 用于记录每次游戏所在页面打开时的访问记录，也就是页面的PV记录
 * @author Administrator
 *
 */
public class PVLog {
	private static final Logger log = Logger.getLogger(PVLog.class);

	public static final void pvLog(HttpServletRequest request) {
		String uid = HttpUtil.getString(request, "uid");
		String url = HttpUtil.getString(request, "url");
		String gameid = HttpUtil.getString(request, "gameid");
		String referer = request.getHeader("Referer");
		String ua = request.getHeader("user-agent");
		String ip = Tools.getRemoteAddr(request);
		
		String logstr = new StringBuffer(getStringTime()).append("\t").
		append(ip).append("\t").
		append(uid).append("\t").
		append(gameid).append("\t").
		append(url).append("\t").
		append(referer).append("\t").
		append(ua).toString();
		
		log.info(logstr);
	}

	public static String getStringTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new java.util.Date());
	}

}
