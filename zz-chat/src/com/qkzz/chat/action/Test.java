package com.qkzz.chat.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.RetBean;
import com.qkzz.chat.bean.TeamFreshBean;
import com.qkzz.chat.service.TeamChatService;
import com.qkzz.common.TypeTrans;

//import net.sf.json.JSONArray;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
//		String content = "<p><br /></p>";
//		System.out.println(content.lastIndexOf("<p><br /></p>"));
//		if(content.lastIndexOf("<p><br /></p>") >= 0) {
//			content = content.substring(0,content.lastIndexOf("<p><br /></p>")).trim();
//		}

		
//		String a= "Ano29";
//		System.out.println(Test.getStrSplit(a,"[^A-Za-z1-9-_]"));

//        Matcher matcher  = Pattern.compile("^[0-9a-zA-Z_-]+$").matcher(str);
//        System.out.println(matcher.find());
//		String str = "http://www.haowen.it/event/133";
//		System.out.println(str.substring(str.lastIndexOf("/")+1));
		
//		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
//		map.put("1", "aaaaa");
//		map.put("2", "bbbbbb");
//		map.put("3", "ccccccc");
//		map.put("4", "ddddddd");
//		
//		Iterator iter = map.entrySet().iterator();
//		while(iter.hasNext()) {
//			Map.Entry entry = (Map.Entry)iter.next();
//			String key = (String)entry.getKey();
//			String value = (String)entry.getValue();
//			System.out.println("key="+key+"====value:"+value);
//		}

		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1111);
//		list.add(2222);
//		list.add(3333);
//		list.add(4444);
//		list.add(5555);
//		list.add(6666);
//		list.add(7777);
//
//		
//		for(Integer i:list) {
//			System.out.println(i);
//		}
//		
//		System.out.println("====================");
//		Collections.reverse(list);
//		
//		for(Integer i:list) {
//			System.out.println(i);
//		}
//		
//		
//		try {
//			System.out.println(java.net.URLEncoder.encode("我有话说","utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
//		map.put("1", "11");
//		map.put("2", "22");
//		map.put("3", "33");
//		map.put("4", "44");
//		map.put("5", "55");
//
//		if(map.containsKey("1")) {
//			System.out.println("11111111111");
//		}
		
//		Iterator iter = map.keySet().iterator();
//		while (iter.hasNext()) {
//		try{
//		ConcurrentHashMap.Entry entry = (ConcurrentHashMap.Entry) iter.next();
//		Object value= entry.getValue();
//		Object key =entry.getKey();
//		System.out.println(key+"---"+value);
//		} catch(Exception e) {
//		e.printStackTrace();
//		}
//		}

//		for(int i=0;i<10;i++) {
//			long start = System.currentTimeMillis();
//			List<Content> list = new ArrayList<Content>();
//			Content c = new Content();
//			c.setFromid(1);
//			c.setFromname("fromname");
//			c.setDestid(2);
//			c.setDestname("destname");
//			c.setContent("1111111111");
//			c.setAttime(11111);
//			list.add(c);
//			Content b = new Content();
//			b.setFromid(2);
//			b.setFromname("fromname2");
//			b.setDestid(3);
//			b.setDestname("destname2");
//			b.setContent("222222,22\"");
//			b.setAttime(222222);
//			list.add(b);
////			list.add(3L);
////			list.add(4L);
//
////			if(list.contains(2L)) {
////			list.remove(2L);
////			}
////			for(Long k:list) {
////			System.out.println(k);
////			}
//
//			RetBean bean = new RetBean();
//			bean.setList(null);
//			bean.setFreshTime(60);
//			bean.setLasttime(100);
//			JSONArray json = JSONArray.fromObject(bean);
//			System.out.println(json.toString());
//			System.out.println(System.currentTimeMillis()-start);
//			System.out.println("================================");
//		}
		for(int i=0;i<256;i++) {
			String a = "alter table hw_user_notify_"+i+" add index(type);";
			System.out.println(a);
		}
//		System.out.println(System.currentTimeMillis());

//		String a = "flex (sdk4.6)发布问题";
//		System.out.println(a.trim().replaceAll("\\p{Punct}", "-"));
//		a = a.replaceAll("\\(", "（").replaceAll("\\)", "）");
//		System.out.println(a);
//		String a = "/img/201112/21/1324483067125.jpg";
//		System.out.println(a.substring(a.lastIndexOf("/")+1));
		
//		try {
//			System.out.println(MD5Encrypt.encoderForString("2343242343242342"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//		System.out.println(com.qkzz.common.DateTrans.getLong2Str(1304773200000L, "yyyy-MM-dd HH:mm:ss"));

//		String a = "2-1";
//		String[] b = a.split("-");
//		System.out.println(b[0]+"=="+b[1]);
		
//		String ss = "<p>获取页面某一元素的绝对X,Y坐标，可以用offset()方法：（body属性设置margin :0;padding:0;）<br /></p><pre class=\"brush:js;toolbar:false;\">var X = $('#DivID').offset().top;" +
//				"var Y = $('#DivID').offset().left;</pre><p>获取相对(父元素)位置:<br /></p><pre class=\"brush:js;toolbar:false;\">var X = $('#DivID').position().top;" +
//						"var Y = $('#DivID').position().left;</pre><br/>";  
//        Regex regex = new Regex("//<///?([/"=:_/. ]|//w)+///?//>");  
//        Response.Write(String.Format("<b>Before:</b>{0}",ss)); // HTML 文本  
//        Response.Write("<br/>");  
//        ss = regex.Replace(ss, String.Empty);  
//        Response.Write(String.Format("<b>After:</b>{0}", ss));// 输出为纯文本
		
//		System.out.println(Test.splitAndFilterString(ss, 100));
	}


	public static String splitAndFilterString(String input, int length) {  
        if (input == null || input.trim().equals("")) {  
            return "";  
        }  
        // 去掉所有html元素,  
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
                "<[^>]*>", "");  
        str = str.replaceAll("[(/>)<]", "");  
        int len = str.length();  
        if (len <= length) {  
            return str;  
        } else {  
            str = str.substring(0, length);  
            str += "......";  
        }  
        return str;  
    }  
	
	/**
     * 生成唯一字符串 
     * 作者:解镭 Email:xielei@live.com 
     * 创建日期：May 14, 2008 
     * 创建时间: 5:07:39 PM
     * @param length 需要长度
     * @param symbol 是否允许出现特殊字符 -- !@#$%^&*()
     * @return
     */
    public static String getUniqueString(int length, boolean symbol)
                    throws Exception {
            Random ran = new Random();
            int num = ran.nextInt(61);
            String returnString = "";
            String str = "";
            for (int i = 0; i < length;) {
                    if (symbol)
                            num = ran.nextInt(70);
                    else
                            num = ran.nextInt(61);
                    str = strArray[num];
                    if (!(returnString.indexOf(str) >= 0)) {
                            returnString += str;
                            i++;
                    }
            }
            return returnString;
    }

    /**
     * 生成唯一字符串 会已时间 加上你需要数量的随机字母 
     * 如:getUniqueString(6,true,"yyyyMMddHHmmss")
     * 返回:20080512191554juHkn4 
     * 作者:解镭 Email:xielei@live.com 
     * 创建日期：May 14, 2008
     * 创建时间: 5:07:39 PM
     * @param length 需要长度
     * @param symbol 是否允许出现特殊字符 -- !@#$%^&*()
     * @param dateformat 时间格式字符串
     * @return 
     */
    public static String getUniqueString(int length, boolean symbol,
                    String dateformat) throws Exception {
            Random ran = new Random();
            int num = ran.nextInt(61);
            Calendar d = Calendar.getInstance();
            Date nowTime = d.getTime();
            SimpleDateFormat sf = new SimpleDateFormat(dateformat);
            String returnString = sf.format(nowTime);
            String str = "";
            for (int i = 0; i < length;) {
                    if (symbol)
                            num = ran.nextInt(70);
                    else
                            num = ran.nextInt(61);
                    str = strArray[num];
                    if (!(returnString.indexOf(str) >= 0)) {
                            returnString += str;
                            i++;
                    }
            }
            return returnString;
    }

    /**
     * 给生成唯一字符串使用
     */
    private static String[] strArray = new String[] {
"a", "b", "c", "d", "e",
    "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
    "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
    "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
    "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4",
    "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "(",
    ")" };
    
    
    
    
    public  static String getStrSplit(String str, String regex) {
        if(null==str||"".equals(str))
            return "";
        Pattern p = Pattern.compile(regex);               //初始化正则
        Matcher m = p.matcher(str);                       //进行正则匹配
        StringBuffer sbr = new StringBuffer();
        while (m.find()) {                                //查找字符串匹配特殊字符的项
            m.appendReplacement(sbr, "");                 //替换要替换的特殊字符
        }
        m.appendTail(sbr);                                //将替换后的字符串进行合并
        return sbr.toString();                            //返回最终字符
    }
}
