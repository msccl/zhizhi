package com.qkzz.util;

import java.text.*;
import java.util.*;

public class DateGet {

  public DateGet() {
  }

  /**
   * 按照规范的格式定义的时间字符串
   * @param dateStr String - 日期: YYYY-MM-DD HH:mm:ss
   * @return String
   */
  public static String wxDate(String dateStr) {
    //计算时间
    SimpleDateFormat fnow = new SimpleDateFormat("yyyy");
    Date cnow = new Date();
    String ds = fnow.format(cnow);
    if(ds.equals(dateStr.substring(0,4))) {
      dateStr = dateStr.substring(5,16);
    } else {
      dateStr = dateStr.substring(2,16);
    }
    return dateStr;
  }

  /**
   * 按照规范格式定义的时间字符串
   * @param date long - 时间毫秒数
   * @return String
   */
  public static String wxDate(long date) {
    String dateStr = patternPrint("yyyy-MM-dd HH:mm:ss",new Date(date));
    SimpleDateFormat fnow = new SimpleDateFormat("yyyy");
    Date cnow = new Date(date);
    String ds = fnow.format(cnow);
    if(ds.equals(dateStr.substring(0,4))) {
      dateStr = dateStr.substring(5,16);
    } else {
      dateStr = dateStr.substring(2,16);
    }
    return dateStr;
  }


  /**
   * 根据传入的pattern格式对日期进行格式化输出
   * pattern格式
   * ===========================================
   * yyyy.MM.dd HH:mm:ss z   两个MM, dd会导致补零
   * yy年M月d日 HH时mm分      两个yy就显示为两位年份
   * EEE, MMM d, ''yy
   * h:mm a
   * hh 'o''clock' a, zzzz
   * yyyyy.MMMMM.dd GGG hh:mm aaa
   * EEE, d MMM yyyy HH:mm:ss Z
   * yyMMddHHmmssZ
   * MM-dd hh:mm:ss
   * ===========================================
   * @param pattern String
   * @param date Date
   * @return String
   */
  public static String patternPrint(String pattern, Date date) {
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    return df.format(date);
  }


  public static String patternPrint(String pattern, long dateLong) {
    Date date = new Date(dateLong);
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    return df.format(date);
  }

  
  /**
   * 获取剩余时间
   * @param date
   * @return
   */
  public static String getRestTime(long endtime) {
	  int day = 0;
	  int hour = 0;
	  int minute = 0;
	  
	  long resttime = endtime - System.currentTimeMillis();
	  if(resttime < 0) {
		  return "已经停止";
	  }
	  if(resttime < 24*60*60*1000) {
		  int tmphour = (int)(resttime/(60*60*1000));
		  if(tmphour >=1) {
			  hour = tmphour;
			  long minuterest = resttime - tmphour*60*60*1000;
			  minute = (int)(minuterest/(60*1000));
		  } else {
			  long minuterest = resttime - tmphour*60*60*1000;
			  minute = (int)(minuterest/(60*1000));
		  }
	  } else {
		  int tmpday = (int)(resttime / (24*60*60*1000));
		  day = tmpday;
		  long hourrest = resttime - tmpday*24*60*60*1000;
		  int tmphour = (int)(hourrest/(60*60*1000));
		  if(tmphour >=1) {
			  hour = tmphour;
			  long minuterest = hourrest - tmphour*60*60*1000;
			  minute = (int)(minuterest/(60*1000));
		  } else {
			  long minuterest = hourrest - tmphour*60*60*1000;
			  minute = (int)(minuterest/(60*1000));
		  }
	  }
	  
	  String ret = "";
	  if(day != 0) {
		  ret = ret + day + "天";
	  }
	  if(hour != 0) {
		  ret = ret + hour + "小时";
	  }
	  if(minute != 0) {
		  ret = ret + minute + "分钟";
	  }
	  
	  return ret;
  }
  
  public static void main(String[] arg) {
	System.out.println(DateGet.getRestTime(System.currentTimeMillis()+25*60*60*1000+6*60*1000));  
  }

}
