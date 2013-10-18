package com.qkzz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期标准格式化
 * 
 * @author Sunny
 * 
 */
public class DateFormat {
	/**
	 * 获得日期显示格式,按照全站规范定义
	 * 
	 * @param time
	 * @return
	 */
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

	/**
	 * 标准的时间格式化，是否带毫秒值
	 * 
	 * @param time
	 * @param hasSecond
	 * @return
	 */
	public static String standardDateFormat(Date time, boolean hasSecond) {
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
		if (hasSecond) {
			pattern.append(":ss");
		}
		return new SimpleDateFormat(pattern.toString()).format(time);
	}

	/**
	 * 获得当前时间和参数时间的时间差值,以xx秒 xx分钟 xx小时 xx天显示
	 * 
	 * @param time
	 * @return
	 */
	public static String viewDateStandardFormat(Date date) {

		long longTime = date.getTime();
		long aa = System.currentTimeMillis() - longTime;
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
		return standardDateFormat(date);
	}
	/**
	 * 获得当前时间和参数时间的时间差值,以xx秒 xx分钟 xx小时 xx天显示
	 * 
	 * @param time
	 * @return
	 */
	public static String viewDateDiff(Date date) {

		long longTime = date.getTime();
		long aa = System.currentTimeMillis() - longTime;
		if (aa <= 1000) {
			return "1秒";
		}
		long bb = aa / 1000;// 秒
		if (bb < 60) {
			return bb + "秒";
		}
		long cc = bb / 60;// minitue
		if (cc < 60) {
			return cc + "分钟";
		}
		long dd = cc / 60;
		if (dd < 24) {
			return dd + "小时";
		}
		long ee = dd / 24;
		if (ee < 7) {
			return ee + "天";
		}
		long ff = ee / 7;
		if (ff <= 4) {
			return ff + "周";
		}
		return standardDateFormat(date);
	}
	/**
	 * 时间加减
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date dateAdd(Date date, int num) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, num);
			date = cal.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间加减，输出format格式字符串
	 * 
	 * @param date
	 * @param format
	 * @param num
	 * @return
	 */
	public static String dateAddByDateForString(Date date, String format,
			int num) {
		String ret = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, num);
			date = cal.getTime();
			ret = sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @param num
	 * @return
	 */
	public static String dateAddByStringForString(String date, String format,
			int num) {
		String ret = null;
		SimpleDateFormat sdf;
		Date dateTime;
		try {
			sdf = new SimpleDateFormat(format);
			dateTime = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateTime);
			cal.add(Calendar.DATE, num);
			dateTime = cal.getTime();
			ret = sdf.format(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static Date simpleDateParse(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String simpleDateFormat(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据时间段，返回给定格式的日期list<String>
	 * 
	 * @param startDate
	 * @param endDate
	 * @param format
	 * @return
	 */
	public static List<String> getDateList(Date startDate, Date endDate,
			String format) {
		try {
			List<String> list = new ArrayList<String>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			Calendar calend = Calendar.getInstance();
			calend.setTime(endDate);
			while (cal.before(calend)) {
				list.add(simpleDateFormat(cal.getTime(), format));
				cal.add(Calendar.DATE, 1);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	public static void main(String[] argv) {
//		System.out.println("2008-01-01 14:58:06".indexOf(simpleDateFormat(
//				new Date(), "yyyy-MM-dd")));
//	}
}
