package cn.yicha.ad.blance;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static void main(String[] args)
	{
		String a = "你的字符串，不过 Http://www.sina.com.cn我来测试http://www.163.com 网易地址";
		Pattern pattern = Pattern.compile("http://[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(a);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){
			buffer.append(matcher.group());
			buffer.append("\r\n");
		}
		System.out.println(buffer.toString());
		System.out.println("===================");
		System.out.println(Utils.doFiltr("Http://www.sina.com.cn", "http://abc.com/acx", a));

		System.out.println(Utils.ShortUrl("http://www.sina.com.cn"));
		System.out.println(Utils.ShortUrl("http://www.163.com"));
	}

	public static String doFiltr(String regex,String rpStr,String content) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex,java.util.regex.Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher m = p.matcher(content);
		content = m.replaceAll(rpStr);
		return content;
	}

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
