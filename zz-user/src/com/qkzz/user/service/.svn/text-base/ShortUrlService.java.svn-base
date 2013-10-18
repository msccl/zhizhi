package com.qkzz.user.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qkzz.common.MD5;
import com.qkzz.user.dao.ShortUrlDao;
import com.qkzz.user.dao.impl.ShortUrlDaoImpl;

public class ShortUrlService {
	
	private static String domain = "http://zizi.im/";//短地址域名
	private static String regex = "((http|ftp|https)\\://[ \\w]+(.[\\w]+)([\\w\\-\\.,@?^=%&amp;:/~\\+#]*))";

	private static ShortUrlDao dao = new ShortUrlDaoImpl();
	
	public static void main(String[] args)
	{
//		String a = "你的字符串，不过 Http://www.sina.com.cn我来测试http://www.163.com 网易地址";
		String a = "你的字符串，http://zizi.im/abc.jsp請使用BitSpirit或BitComet進行下http://www.baidu.com:8080/s?wd=url%20java%20regex&pn=50載請使用BitSpirit或BitComet進行下載http://blog.csdn.net/jiaguanghan123/article/details/5217511#quote我勒个去";
//		Pattern pattern = Pattern.compile("http://[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(a);
//		StringBuffer buffer = new StringBuffer();
//		while(matcher.find()){
//			buffer.append(matcher.group());
//			buffer.append("\r\n");
//		}
//		System.out.println(buffer.toString());
//		System.out.println("===================");
//		System.out.println(ShortUrlService.replaceUrl(a,"Http://www.sina.com.cn", "http://abc.com/acx"));
//
//		System.out.println(ShortUrlService.ShortUrl("http://www.sina.com.cn"));
//		System.out.println(ShortUrlService.ShortUrl("http://www.163.com"));

		System.out.println(ShortUrlService.getShortContent(a));
	
	}


	/**
	 * 获取内容中的所有地址列表
	 * @param content
	 * @return
	 */
	public static String getShortContent(String content) {
		
		if(!hasUrl(content)) {
			return content;
		}
		
//		Pattern pattern = Pattern.compile("http://[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
//		Pattern pattern = Pattern.compile("http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?",Pattern.CASE_INSENSITIVE);
//		Pattern pattern = Pattern.compile("^http:\\/\\/[A-Za-z0-9]+\\.[A-Za-z0-9]+[\\/=\?%\-&_~`@[\\]\\':+!]*([^<>\\"\\"])*$",Pattern.CASE_INSENSITIVE);
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
//		Pattern pattern = Pattern.compile("^(http|www|ftp|)?(://)?(//w+(-//w+)*)(//.(//w+(-//w+)*))*((://d+)?)(/(//w+(-//w+)*))*(//.?(//w)*)(//?)?(((//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*(//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*)*(//w*)*)$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			String url = matcher.group();
			if(url.startsWith("http://zizi.im/")) {
				continue;
			}
			System.out.println("short from:"+url);
			String shortCode = ShortUrl(url);
			
			//转换url并加入到数据库中保存
			if(!dao.isExist(shortCode)) {
				dao.add(shortCode, url);
			}
			String shortUrl = new StringBuffer("<a href='").append(domain).append(shortCode).append("' target='_blank'><font color='#478fe8'><u>").append(domain).append(shortCode).append("</u></font></a>").toString();
			//进行替换操作
//			content = replaceUrl(content,url,shortUrl);
			content = content.replace(url, shortUrl);
		}
		return content;
	}

	
	/**
	 * 判断内容中是否包含url地址
	 * @param content
	 * @return
	 */
	public static boolean hasUrl(String content) {
		Pattern pattern = Pattern.compile("http://[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		if(matcher.find()){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 使用新地址替换内容中的老地址
	 * @param regex
	 * @param rpStr
	 * @param content
	 * @return
	 */
	public static String replaceUrl(String content,String regex,String replaceStr) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex,java.util.regex.Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher m = p.matcher(content);
		content = m.replaceAll(replaceStr);
		return content;
	}

	
	/**
	 * 为url进行段地址编码
	 * @param url
	 * @return
	 */
	public static String ShortUrl(String url)  
	{  
		//可以自定义生成MD5加密字符传前的混合KEY
		String key = "james";
		//要使用生成URL的字符
		String[] chars = new String[]{
				"a","b","c","d","e","f","g","h",
				"i","j","k","l","m","n","o","p",
				"q","r","s","t","u","v","w","x",
				"y","z","0","1","2","3","4","5",
				"6","7","8","9","A","B","C","D",
				"E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T",
				"U","V","W","X","Y","Z"
		};

		//对传入网址进行MD5加密
		String hex = MD5.encoderForString(key+url);
		String resUrl = "";

		for (int i = 0; i < 4; i++)	{
			//把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
			String subHex = hex.substring(i * 8, (i+1)*8); // 随机抽出一段字符串

			long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

			String outChars = "";
			for (int j = 0; j < 6; j++)	{
				//把得到的值与0x0000003D进行位与运算，取得字符数组chars索引
				int index = (int) (Long.valueOf("0000001f", 16) & idx);//这里的"000001f"不能大于数组chars的长度，并且让其二进制格式从左到右尽可能多的为1,取值一般为35,61,63

				//把取得的字符相加
				outChars += chars[index];
				//每次循环按位右移5位
				idx = idx >> 5;
			}
			//把字符串存入对应索引的输出数组
			resUrl = outChars;
		}
		return resUrl;
	}  

}
