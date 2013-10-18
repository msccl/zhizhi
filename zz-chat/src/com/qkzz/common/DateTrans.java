package com.qkzz.common;

import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.*;

public class DateTrans {

	/**
	 * 默认的时间格式如下：<br/> DAY_FORMAT[0] : yyyyMMdd<br/> DAY_FORMAT[1] :
	 * yyyy-MM-dd<br/> DAY_FORMAT[2] : yyyy/MM/dd<br/> DAY_FORMAT[3] :
	 * yyyy年MM月dd日<br/>
	 */
	public static String[] DAY_FORMAT = { "yyyyMMdd", "yyyy-MM-dd",
			"yyyy/MM/dd", "yyyy年MM月dd日", "yyyy-MM-dd HH:mm:ss", };

	public static final SimpleDateFormat SIMPLE_DAY_FMT = new SimpleDateFormat(
			"yyyyMMdd");

	private static SimpleDateFormat standardFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat[] day_format = {
			new SimpleDateFormat("yyyyMMdd"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy/MM/dd"),
			new SimpleDateFormat("yyyy年MM月dd日"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), };

	public static String standardNow() {
		return standardFormat.format(new java.util.Date());
	}

	/**
	 * 指定格式获取当前日期
	 * 
	 * @param format
	 *            String
	 * @return String
	 */
	public static String today(String format) {
		return new SimpleDateFormat(format).format(new java.util.Date());
	}

	/**
	 * 当天日期的简单格式
	 * 
	 * @return String
	 */
	public static String today() {
		return SIMPLE_DAY_FMT.format(new java.util.Date());
	}

	public static String today(int index) {
		return day_format[index].format(new java.util.Date());
	}

	public static String yestoday(int index) {
		return day_format[index].format(new java.util.Date(System
				.currentTimeMillis()
				- 1000 * 60 * 60 * 24L));
	}

	public static String yestoday() {
		return SIMPLE_DAY_FMT.format(new java.util.Date(System
				.currentTimeMillis()
				- 1000 * 60 * 60 * 24L));
	}

	public static String tomorrow(int index) {
		return day_format[index].format(new java.util.Date(System
				.currentTimeMillis()
				+ 1000 * 60 * 60 * 24L));
	}

	public static String tomorrow() {
		return SIMPLE_DAY_FMT.format(new java.util.Date(System
				.currentTimeMillis()
				+ 1000 * 60 * 60 * 24L));
	}

	/**
	 * 判断日期1是否在日期2后面，纯粹根据ascii值来判断的。
	 * 
	 * @param day1
	 *            String
	 * @param day2
	 *            String
	 * @return boolean
	 */
	public static boolean isAfter(String day1, String day2) {
		for (int i = 0; i < day1.length(); i++) {
			if (i >= day2.length()) {
				return true;
			}
			char c = day1.charAt(i);
			char d = day2.charAt(i);
			if (c > d) {
				return true;
			}
			if (c < d) {
				return false;
			}
		}
		return false;
	}

	/**
	 * 获取某一天的前一天格式化日期，格式yyyyMMdd 如输入"20060401"，返回"20060331"，在多天显示的时候使用
	 */
	public static String yestoday(String theday) {
		java.util.Calendar cal = getDay(theday);
		return SIMPLE_DAY_FMT.format(new Date(cal.getTime().getTime() - 1000
				* 60 * 60 * 24L));
	}

	/**
	 * 某一天的后一天格式化日期，格式yyyyMMdd 如输入"20060331"，返回"20060401"，在多天显示的时候使用
	 */
	public static String tomorrow(String theday) {
		java.util.Calendar cal = getDay(theday);
		return SIMPLE_DAY_FMT.format(new Date(cal.getTime().getTime() + 1000
				* 60 * 60 * 24L));
	}

	public static String getToday(String format) {
		return new java.text.SimpleDateFormat(format)
				.format(new java.util.Date());
	}

	public static String getTomorrow(String format) {
		return new java.text.SimpleDateFormat(format)
				.format(new java.util.Date(new java.util.Date().getTime()
						+ 1000 * 60 * 60 * 24));
	}

	public static java.util.Calendar getDay(String day) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(Integer.parseInt(day.substring(0, 4)), Integer.parseInt(day
				.substring(4, 6)) - 1, Integer.parseInt(day.substring(6, 8)));
		return cal;
	}

	public static String getLong2Str(long time, String format) {
		return new SimpleDateFormat(format).format(new java.util.Date(time));
	}

	/**
	 * 将字符型 strDate 转换为 日期时间,返回格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            String
	 * @return long
	 */
	public static long getStrToLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate.getTime();
	}

	public static String getPeriodDesc(long atmills) {
		long aa = System.currentTimeMillis() - atmills;
		if (aa <= 1000) {
			return "1秒前";
		}
		long bb = aa / 1000;// 秒
		if (bb < 60) {
			return bb + "秒前";
		}
		long cc = bb / 60;// minitue
		if (cc < 60) {
			return cc + "分钟前";
		}
		long dd = cc / 60;
		if (dd < 24) {
			return dd + "小时前";
		}
		long ee = dd / 24;
		if (ee < 7) {
			return ee + "天前";
		}
		long ff = ee / 7;
		if (ff <= 4) {
			return ff + "周前";
		}
		return standardDateFormat(new java.util.Date(atmills));
	}

	public static String standardDateFormat(Date time) {
		StringBuilder pattern = new StringBuilder();
		Calendar thisCalendar = Calendar.getInstance();
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);
		if (thisCalendar.get(Calendar.YEAR) == timeCalendar.get(Calendar.YEAR)) {
			// 同一年
			if ((thisCalendar.get(Calendar.MONTH) == timeCalendar
					.get(Calendar.MONTH))
					&& (thisCalendar.get(Calendar.DATE) == timeCalendar
							.get(Calendar.DATE))) {
				// 同一天
				pattern.append("HH:mm");
			} else {
				// 不同天
				pattern.append("MM-dd HH:mm");
			}
		} else {
			pattern.append("yyyy-MM-dd HH:mm");
		}
		return new SimpleDateFormat(pattern.toString()).format(time);
	}

	public static String currentGreeting() {

		int now = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

		if (now < 6)
			return "凌晨了";
		else if (now < 8)
			return "早上好";
		else if (now < 12)
			return "上午好";
		else if (now < 14)
			return "中午好";
		else if (now < 18)
			return "下午好";
		else if (now < 22)
			return "晚上好";
		else
			return "夜深了";
	}
}
