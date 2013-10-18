package com.qkzz.util;

import java.net.UnknownHostException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class Tools {

	/**
	 * 判断是否已经过期
	 * @param lastupdatetime long
	 * @param duration long
	 * @return boolean
	 */
	public static boolean isExpired(long lastupdatetime, long duration) {
		if (System.currentTimeMillis() - lastupdatetime > duration * 1000) {
			return true;
		}
		return false;
	}
	
	/**
	 * 产生随机数(等介于min,max)
	 * @param min
	 * @param max
	 * @return
	 */
    public static int Rand(int min, int max) {
        Random random = new Random();
        return Math.abs(random.nextInt()) % ((max - min) + 1) + min;
    }

	/**
	 * 判断一个字符串是否为数字字符串(是true,否false)
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		if(str.matches("\\d*")){
			return true; 
		}else{
			return false;
		}
	}

	
    public static void main(String [] args){
    	System.out.println(Tools.Rand(1, 6));
    	System.out.println(Tools.Rand(1, 6));
    	System.out.println(Tools.Rand(1, 6));
    	System.out.println(Tools.isNumeric("1234567890"));
    	System.out.println(Tools.isNumeric("123456789a0"));
    	System.out.println(Tools.isNumeric("AHyssCzsfx"));
    }
	
	
	/**
	 * 查询本地hostname
	 * @return
	 */
	public static String getLocalHostName() {
		String host;
		try {
			java.net.InetAddress ia = java.net.InetAddress.getLocalHost();
			host = ia.getHostName();
		} catch (UnknownHostException e) {
			host = "Unkonwn";
			e.printStackTrace();
		}
		return host;
	}


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
