package com.qkzz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 开奖 计时
 * @author xinfeng999@gmail.com
 *
 */

public class MDate {

    public static void main(String[] args) {
        System.out.println("日期增加一天= " + add(MDate.getDateString("yyyy-MM-dd HH:mm:ss"), 10));
        System.out.println("日期增加一天= " + add("2009-06-27 10:01:10", 10));
        System.out.println(MDate.get(MDate.add(MDate.getDateString("yyyy-MM-dd HH:mm:ss"), 10)));
        System.out.println(calYesterday());
    }

    /**
     * 通过上面的方法获得两个日期的时间差
     * @param String
     * @return long
     * @throws ParseException 
     */      
    public static long get(String strDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cd = Calendar.getInstance();
            long nowMills = cd.getTimeInMillis();
    		cd.setTime(sdf.parse(strDate));
            long setMills = cd.getTimeInMillis();
            return (setMills-nowMills)/(1000);
        } catch (ParseException e) {}
        return 0l;
     }

    /**
     * 通过上面的方法获得两个日期的时间差
     * @param Date
     * @return long
     * @throws ParseException 
     */      
    public static long get(Date strDate) {
        Calendar cd = Calendar.getInstance();
        long nowMills = cd.getTimeInMillis();
		cd.setTime(strDate);
        long setMills = cd.getTimeInMillis();
        return (setMills-nowMills)/(1000);
     }

    /**
     * 指定格式化日期时间格式,格式当前日期时间
     * @param format 
     * @return 
     */
    public static String getDateString(String format){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * 年,月,日,时,分,秒,毫秒
     * Calendar.YEAR,MONTH,DATE,HOUR,MINUTE,SECOND,MILLISECOND
     * @param String
     * @param int
     * @return String
    */
    public static String add(String strDate, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(strDate));
            cd.add(Calendar.MINUTE, n);
            return sdf.format(cd.getTime());
        } catch (Exception e) {}
        return null;
    }

    /**
     * 年,月,日,时,分,秒,毫秒
     * Calendar.YEAR,MONTH,DATE,HOUR,MINUTE,SECOND,MILLISECOND
     * @param Date
     * @param int
     * @return Date
    */
    public static Date add(Date strDate, int n) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(strDate);
            cd.add(Calendar.MINUTE, n);
            return cd.getTime();
        } catch (Exception e) {}
        return null;
    }
    
    public static String calYesterday(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.setTime(new java.util.Date());
        cd.add(Calendar.DATE, -1);
        return sdf.format(cd.getTime());
    }
    
}
