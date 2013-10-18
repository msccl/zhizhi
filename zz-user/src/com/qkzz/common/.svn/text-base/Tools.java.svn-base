package com.qkzz.common;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 论坛工具类
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class Tools {
  public Tools() {
  }

  private static List<String> serverList = new ArrayList<String>();

  /**
   * 判断是否已经过期
   * @param lastupdatetime long
   * @param duration long
   * @return boolean
   */
  public static boolean isExpired(long lastupdatetime,long duration) {
    if(System.currentTimeMillis() - lastupdatetime > duration*1000) {
      return true;
    }
    return false;
  }

  /**
   * 用于分页程序的最大页码计算
   * @param maxRowCount int - 最大行数
   * @param rowsPerPage int - 每页平均显示数量
   * @return int
   */
  public static int calcMaxPage(int maxRowCount,int rowsPerPage) {
    int maxPage = 1;
    if (maxRowCount % rowsPerPage == 0) {
      maxPage = maxRowCount / rowsPerPage;
    } else {
      maxPage = maxRowCount / rowsPerPage + 1;
    }
    return maxPage;
  }

  /**
   * 计算翻页中的当前页码
   * @param request HttpServletRequest
   * @param paraname String
   * @param minVal int
   * @param maxVal int
   * @return int
   */
  public static int getCurPage(HttpServletRequest request, String paraname,int minVal,int maxVal) {
    try {
     int curPage = Integer.parseInt(request.getParameter(paraname));
      if(curPage < 1) {
        curPage = 1;
      } else if(curPage > maxVal) {
        curPage = maxVal;
      }
      return curPage;
    }
    catch (Exception e) {
      return minVal;
    }
  }

  /**
   * 获取当天是这个星期的第几天(从星期天是第一天计算)
   * @return int
   */
  public static int getDayOfWeek() {
    Calendar calendar = new GregorianCalendar();
    return calendar.get(Calendar.DAY_OF_WEEK);
  }


  
  /**
   * 获取读取数据的起始位置
   * @param curPage 当前页面
   * @param rowsPerPage 每页显示条数
   * @return int
   */
  public static int getStartIndex(int curPage, int rowsPerPage) {
	  int startIndex = 0;
		startIndex = (curPage - 1) * rowsPerPage;
		if (startIndex < 1) {
			startIndex = 0;
		}
	  return startIndex;
  }
  
  /**
   * 针对汉字和字母做不同区分的字符截取
   * @param str
   * @param start
   * @param end
   * @return
   */
  public static String subString(String str, int tocount,String more) {
	  int reint = 0; 
	    String restr = ""; 
	    if (str == null) 
	    return ""; 
	    char[] tempchar = str.toCharArray(); 
	    for (int i= 0; (i< tempchar.length && tocount > reint);i++) { 
	    String s1 = str.valueOf(tempchar[i]); 
	    byte[] b = s1.getBytes(); 
	    reint += b.length; 
	    restr += tempchar[i]; 
	    } 
	    if (tocount == reint || (tocount == reint - 1)) 
	    restr += more; 
	    return restr;   }
  
//  public static void main(String[] args) {
//
//	  String a = "abc d喜欢死了efghijklmnopqrst";
//	  System.out.println("===="+Tools.subString(a, 10, ""));
//  }
  
  
  
  /**  
   * 判断是否是一个中文汉字  
   *   
   * @param c  
   *            字符  
   * @return true表示是中文汉字，false表示是英文字母  
   * @throws UnsupportedEncodingException  
   *             使用了JAVA不支持的编码格式  
   */  
  public static boolean isChineseChar(char c) throws UnsupportedEncodingException {   
      // 如果字节数大于1，是汉字   
      // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了   
      return String.valueOf(c).getBytes("GBK").length > 1;   
  }   

  /**  
   * 按字节截取字符串  
   *   
   * @param orignal  
   *            原始字符串  
   * @param count  
   *            截取位数  
   * @return 截取后的字符串  
   * @throws UnsupportedEncodingException  
   *             使用了JAVA不支持的编码格式  
   */  
  public static String substring(String orignal, int count)   
          throws UnsupportedEncodingException {   
      // 原始字符不为null，也不是空字符串   
      if (orignal != null && !"".equals(orignal)) {   
          // 将原始字符串转换为GBK编码格式   
          orignal = new String(orignal.getBytes(), "GBK");   
          // 要截取的字节数大于0，且小于原始字符串的字节数   
          if (count > 0 && count < orignal.getBytes("GBK").length) {   
              StringBuffer buff = new StringBuffer();   
              char c;   
              for (int i = 0; i < count; i++) {   
                  // charAt(int index)也是按照字符来分解字符串的   
                  c = orignal.charAt(i);   
                  buff.append(c);   
                  if (Tools.isChineseChar(c)) {   
                      // 遇到中文汉字，截取字节总数减1   
                      --count;   
                  }   
              }   
              return buff.toString();   
          }   
      }   
      return orignal;   
  }   

  
  /**
   * 功能：根据限制长度截取字符串（字符串中包括汉字，一个汉字等于两个字符）
   * @param strParameter 要截取的字符串
   * @param limitLength 截取的长度
   * @return 截取后的字符串
   */
  public static String getStrByLength(String strParameter , int limitLength) {
	  String return_str=strParameter;//返回的字符串
	  int temp_int=0;//将汉字转换成两个字符后的字符串长度
	  int cut_int=0;//对原始字符串截取的长度
	  byte[] b=strParameter.getBytes();//将字符串转换成字符数组
	  
	  for(int i=0 ; i<b.length ; i++) {
		  if(b[i]>=0) {
			  temp_int=temp_int+1;
		  } else {
			  temp_int=temp_int+2;//一个汉字等于两个字符
			  i++;
		  }
		  cut_int++;   
  
		  if(temp_int >= limitLength) {
			  if(temp_int%2!=0 && b[temp_int-1]<0) {
				  cut_int--;
			  }
			  return_str=return_str.substring(0,cut_int);
			  break;
		  }
	  }
	  return return_str;
  }

  
  public static void main(String[] args) {   
      // 原始字符串   
      String s = "我太a爱他们了 ZWR 爱JAVA";   
      System.out.println("原始字符串：" + s);   
      try {   
          System.out.println("截取前1位：" + Tools.substring(s, 1));   
          System.out.println("截取前2位：" + Tools.getStrByLength(s, 4));   
          System.out.println("截取前4位：" + Tools.substring(s, 4));   
          System.out.println("截取前6位：" + Tools.substring(s, 6));   
      } catch (UnsupportedEncodingException e) {   
          e.printStackTrace();   
      }   
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
		String url = request.getRequestURL().toString();
		url += request.getQueryString()==null?"":"?" + request.getQueryString();
		return url;
	}

	
}
