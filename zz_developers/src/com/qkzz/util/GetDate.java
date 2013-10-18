package com.qkzz.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {

  public GetDate() {}

  /**
   * 获得系统当前时间,返回格式: yyyy-MM-dd HH:mm:ss
   * @return Date
   */
  public static Date getNowDate() {
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = formatter.format(currentTime);
    ParsePosition pos = new ParsePosition(8);
    Date currentTime_2 = formatter.parse(dateString, pos);
    return currentTime_2;
  }

  /**
   * 获得系统当前时间,返回格式: yyyy-MM-dd
   * @return Date
   */
  public static Date getNowDateShort() {
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = formatter.format(currentTime);
    ParsePosition pos = new ParsePosition(8);
    Date currentTime_2 = formatter.parse(dateString, pos);
    return currentTime_2;
  }


  public static String getDateString(String format){
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    String dateString = formatter.format(currentTime);
    return dateString;
  }

  /**
   * 获得系统当前时间,返回格式: yyyy-MM-dd HH:mm:ss
   * @return String
   */
  public static String getStringDate() {
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = formatter.format(currentTime);
    return dateString;
  }

  /**
   * 获得系统当前时间,返回格式: yyyy-MM-dd
   * @return String
   */
  public static String getStringDateShort() {
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = formatter.format(currentTime);
    return dateString;
  }

  /**
   * 将日期型 dateDate 转换为 日期时间,返回格式: yyyy-MM-dd HH:mm:ss
   * @param dateDate Date
   * @return String
   */
  public static String dateToStr(java.util.Date dateDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = formatter.format(dateDate);
    return dateString;
  }

  /**
   * 将字符型 strDate 转换为 日期时间,返回格式: yyyy-MM-dd HH:mm:ss
   * @param strDate String
   * @return Date
   */
  public static Date strToDate(String strDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = formatter.parse(strDate, pos);
    return strtodate;
  }

  /**
   * 将字符型 strDate 转换为 日期时间,返回格式: yyyy-MM-dd
   * @param strDate String
   * @return Date
   */
  public static Date strToBirthday(String strDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = formatter.parse(strDate, pos);
    return strtodate;
  }

  /**
   * 将字符型 strDate 转换为 日期时间,返回格式: yyyy-MM-dd HH:mm:ss
   * @param strDate String
   * @return long
   */
  public static long strToLong(String strDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = formatter.parse(strDate, pos);
    return strtodate.getTime();
  }


}
