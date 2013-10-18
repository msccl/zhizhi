package com.qkzz.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Util {

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getValue(HttpServletRequest request, String name) {

		try {

			return request.getParameter(name).trim();

		} catch (NullPointerException e) {

			return "";

		}

	}

	public static String getValue(HttpServletRequest request, String name,
			String charset) {

		try {

			return URLDecoder.decode(request.getParameter(name), charset)
					.trim();

		} catch (NullPointerException e) {

			return "";

		} catch (UnsupportedEncodingException e) {

			return "";

		}

	}

	public static boolean isEmptyStrings(String[] src) {

		if (null == src || src.length < 1)
			return true;

		for (int i = 0; i < src.length; i++)
			if (isEmptyString(src[i]))
				return true;

		return false;

	}

	public static boolean isEmptyString(String src) {

		return src == null || src.trim().length() < 1;

	}

	public static String trimString(String src) {

		if (isEmptyString(src))
			return "";

		else
			return src.trim();
	}

	public static boolean isInteger(String src) {

		try {

			Integer.parseInt(src);

		} catch (Exception e) {

			return false;

		}

		return true;
	}

	public static boolean isLong(String src) {

		try {

			Long.parseLong(src);

		} catch (Exception e) {

			return false;

		}

		return true;
	}

	public static int parseInt(String src) {

		try {

			return Integer.parseInt(src);

		} catch (Exception e) {

			return -1;

		}
	}

	public static long parseLong(String src) {

		try {

			return Long.parseLong(src);

		} catch (Exception e) {

			return -1l;

		}
	}

	public static long parseLong(String src, int c) {

		try {

			return Long.parseLong(src, c);

		} catch (Exception e) {

			return -1l;

		}
	}

	public static String getDateString(String format, long millis) {

		Date date = new Date(millis);

		if (isEmptyString(format))
			format = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);

	}

	public static String getDateString(String format, String millis) {

		long timeMillis = 0l;
		try {
			timeMillis = Long.parseLong(millis);
		} catch (Exception e) {
			timeMillis = System.currentTimeMillis();
		}

		return getDateString(format, timeMillis);

	}
	
	@SuppressWarnings("deprecation")
	public static String getDateString(long time) {
		String ret = "";
		Date now = new Date();
		Date date = new Date(time);
		if (now.getYear() == date.getYear()) {
			if (now.getMonth() == date.getMonth()) {
				if (now.getDate() == date.getDate()) {
					ret = "今天,  ";
				} else if (now.getDate()-1 == date.getDate()) {
					ret = "昨天,  ";
				} else if (now.getDate()-2 == date.getDate()) {
					ret = "前天,  ";
				} else if (now.getDate()-3 == date.getDate()) {
					ret = date.getDate() - now.getDate() + "天前,  ";
				} else if (now.getDate()-7 >= date.getDate()) {
					ret = "上周,  ";
				} else {
					ret = date.getDate() / 7 + (date.getDate() % 7 > 1 ? 1 : 0) + "周前,  ";
				}
			} else {
				ret = date.getMonth() - now.getMonth() + "月前,  ";
			}
		} else {
			ret = now.getYear() - date.getYear() + "年前,  ";
		}	
		int hours = date.getHours()>12?date.getHours()-12:date.getHours();
		ret += ( hours < 10 ? "0" + hours : hours ) + ":" + ( date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes() ) + " ";
		
		switch (date.getHours()) {
			case 0:
			case 1: ret += "深夜"; break;
			case 2:
			case 3:
			case 4:
			case 5: ret += "凌晨"; break;
			case 6:
			case 7:
			case 8: ret += "早上"; break;
			case 9:
			case 10: ret += "上午"; break;
			case 11:
			case 12: ret += "中午"; break;
			case 13:
			case 14:
			case 15: 
			case 16: ret += "下午"; break;
			case 17:
			case 18: ret += "傍晚"; break;
			case 19:
			case 20:
			case 21: ret += "晚上"; break;
			case 22:
			case 23: ret += "深夜"; break;
		}
		return ret;
	}
	

	public static void writeToFile(String fileName, String content)
			throws IOException {
        StringBuffer sb = new StringBuffer() ;
        sb.append(content) ;		
		writeToFile(fileName, content.toString().getBytes("utf-8"));
	}

	public static void writeToFile(String fileName, byte[] content)
			throws IOException {

		File f = new File(fileName);

		if (!f.exists()) {

			if (f.getParentFile() != null)
				f.getParentFile().mkdirs();

			f.createNewFile();

		}

		FileOutputStream fos = new FileOutputStream(f);

		try {
			fos.write(content);

			fos.flush();

		} finally {
			fos.close();
		}
	}

//	/**
//	 * 压缩图片
//	 * 
//	 * @param src
//	 *            源图片//
//	 * @param savedir
//	 *            目标文件夹//
//	 * @param fileName
//	 *            文件名//
//	 * @param type
//	 *            0.用户,小组的icon(只有50x50,100x100两种size)
//	 *            1.item的图片(50x50,100x100,150x150,300x300,550x550五种size)
//	 * @return
//	 * @throws Exception
//	 */
//	public static int draw(String src, String savedir, String fileName, int type)
//			throws Exception {
//
//		StringBuilder sb = new StringBuilder(Constant.IMG_DRAW_PATH).append(
//				"imgcut.php").append("?src=").append(src).append("&savedir=")
//				.append(savedir).append("&filename=").append(fileName).append(
//						"&type=").append(type);
//
//		URL url = new URL(sb.toString());
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//		return conn.getResponseCode();
//	}

	public static void deleteFile(String fileName) {

		File f = new File(fileName);

		if (f.exists())
			f.delete();
	}

	public static void copyFile(String img, String src) throws IOException {

		File f = new File(img);

		if (!f.exists()) {

			if (f.getParentFile() != null)
				f.getParentFile().mkdirs();

			f.createNewFile();

		}

		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(f);

		byte[] buf = new byte[1024];
		int i = 0;

		try {
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} finally {
			fis.close();
			fos.close();
		}

	}

	/**
	 * 字符串替换
	 * 
	 * @param src
	 *            目标字符串
	 * @param regExp
	 *            需要替换的字符串的正则
	 * @param rpStr
	 *            替换成
	 * @return
	 */
	public static String doFilter(String src, String regExp, String rpStr) {

		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(src);

		src = m.replaceAll(rpStr);

		return src;
	}

	public static String substring(String src, int length) {

		if (isEmptyString(src))
			return "";

		if (src.length() <= length)
			return src;

		return src.substring(0, length);

	}

	public static String intercept(String src, int length) {

		if (isEmptyString(src))
			return "";

		int len = src.length();
		int lng = src.getBytes().length;

		return lng < length ? src : src.substring(0,
				(int) ((length - 6) * len / lng))
				+ "…";
	}

//	public static Object getService(String beanName) {
//
//		return new ClassPathXmlApplicationContext("../bbsContext.xml")
//				.getBean(beanName);
//	}

	public static int random() {

		return Math.abs(new Random().nextInt());

	}

	public static boolean isToday(long millis) throws Exception {

		long oneday = 24 * 60 * 60 * 1000l;
		long utc = 8 * 60 * 60 * 1000l;

		return (System.currentTimeMillis() + utc) / oneday - (millis + utc)
				/ oneday == 0;

	}

	public static String getImageExt(String img_url, String contentType) {

		String ext = "";
		int index = -1;

		if (!isEmptyString(contentType)
				&& (index = contentType.indexOf("/")) > -1) {

			ext = contentType.substring(index + 1, contentType.length())
					.toLowerCase();

		} else {

			index = img_url.lastIndexOf(".");
			if (index > 0)
				ext = img_url.substring(index + 1, img_url.length())
						.toLowerCase();
		}

		if ("png".equals(ext) || "gif".equals(ext))
			return "png";
		else
			return "jpg";

	}

	/**
	 * 检测图片类型
	 * 
	 * @param textObj
	 * @return
	 */
	public static String getImageType(byte[] textObj) {

		String type = "";

		ByteArrayInputStream bais = null;

		MemoryCacheImageInputStream mcis = null;

		try {
			bais = new ByteArrayInputStream(textObj);

			mcis = new MemoryCacheImageInputStream(bais);

			Iterator<ImageReader> it = ImageIO.getImageReaders(mcis);

			while (it.hasNext()) {

				ImageReader reader = it.next();

				if (reader instanceof GIFImageReader) {
					type = "gif";
				} else if (reader instanceof JPEGImageReader) {
					type = "jpg";
				} else if (reader instanceof PNGImageReader) {
					type = "png";
				}
			}
		} catch (Exception e) {
		}

		return type;
	}

//	public static String getFileExt(String fileName) {
//
//		String ext = ParseUtil.parseStr(fileName, "^.+(\\.[^\\?]+)(\\?.+)?", 1);
//
//		for (String str : Constant.IMG_EXT) {
//			if (ext.endsWith(str))
//				return ext;
//		}
//
//		return "";
//	}

	@SuppressWarnings("unchecked")
	public static String getAccessPage(HttpServletRequest request,
			HttpServletResponse response) {

		StringBuilder url = new StringBuilder().append(request.getRequestURI());

		String queryString = request.getQueryString();

		if (!isEmptyString(queryString))
			url.append("?").append(queryString);
//		System.out.println("accesspage==="+Constant.WEB_ROOT + response.encodeURL(url.toString()));
		return response.encodeURL(url.toString());
	}

	public static String getHost(String url) {

		String host = URI.create(url).getHost();
		return isEmptyString(host) ? "" : host;

	}

	public static String interceptString(String src, int length) {

		if (isEmptyString(src))
			return "";

		int len = src.length();
		int lng = src.getBytes().length;

		if (length < 1)
			return src;

		return lng < length ? src : src
				.substring(0, (int) (length * len / lng))
				+ "…";
	}

	public static String getUrlRoot(String url) {

		if (isEmptyString(url))
			return "";

		String ret = "";
		StringBuilder input = new StringBuilder(url);
		Pattern pattern = Pattern.compile("(http://[^/]*)",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(input);

		if (matcher.find())
			ret = matcher.group(1).trim();

		return ret.replace("http://", "");

	}

	public static String getRandomString() {
		return getRandomString(12);
	}

	public static String getRandomString(int num) {
		Random rd = new Random();
		StringBuffer content = new StringBuffer(num);

		for (int i = 0; i < 12; i++) {
			int n;
			while (true) {
				n = rd.nextInt('z' + 1);
				if (n >= '0' && n <= '9')
					break;
				if (n >= 'a' && n <= 'z')
					break;
				if (n >= 'A' && n <= 'Z')
					break;
			}
			content.append((char) n);
		}
		return content.toString();
	}

//	public static String getRandomLink(String email, String validate) {
//
//		return new StringBuffer().append(Constant.WEB_ROOT).append(
//				"/getPassword/").append(StringConvert.encodeData(email))
//				.append("/").append(validate).toString();
//	}

	
//	public static void sendMail(final String subject, final String body,
//			final String toAddr, final String sign) {
//		new Thread(new Runnable() {
//			public void run() {
//				try {
//					String address[] = toAddr.split(",");
//					if (address != null)
//						for (String addr : address) {
//							MailServer mail = new MailServer("mail.blueya.com"); // 设置smtp服务器
//							mail.setNeedAuth(true); // 服务器需要身份验证
//							mail.setSubject(subject);
//							mail.setBody(body);
//							mail.setTo(addr);
//							mail.setFrom(MailServer.WEBMARSTER_ADDRESS, sign);
//							mail.setNamePass(MailServer.WEBMARSTER_ADDRESS,
//									MailServer.WEBMARSTER_PASSWORD); // 发件人的邮箱帐号，密码
//							mail.sendout();
//						}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//	}

	
	
	/**
	 * 判断Email (Email由帐号@域名组成，格式为xxx@xxx.xx)<br>
	 * 帐号由英文字母、数字、点、减号和下划线组成，<br>
	 * 只能以英文字母、数字、减号或下划线开头和结束。<br>
	 * 域名由英文字母、数字、减号、点组成<br>
	 * www.net.cn的注册规则为：只提供英文字母、数字、减号。减号不能用作开头和结尾。(中文域名使用太少，暂不考虑)<br>
	 * 实际查询时-12.com已被注册。<br>
	 * 以下是几大邮箱极限数据测试结果<br>
	 * 163.com为字母或数字开头和结束。<br>
	 * hotmail.com为字母开头，字母、数字、减号或下划线结束。<br>
	 * live.cn为字母、数字、减号或下划线开头和结束。hotmail.com和live.cn不允许有连续的句号。
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {

		String regex = "^[\\w_-]+([\\.\\w_-]*[\\w_-]+)?@[\\w-]+\\.[a-zA-Z]+(\\.[a-zA-Z]+)?$";

		return regex(regex, email, true);
	}

	/**
	 * 判断半角标点符号(仅 US-ASCII)
	 * 
	 * @param src
	 * @return
	 */
	public static boolean isPunct(String src) {

		String regex = "\\p{Punct}";

		return regex(regex, src, false);
	}

	/**
	 * 判断账单金额
	 * 
	 * @param src
	 * @return
	 */
	public static boolean isBillMoney(String src) {

		String regex = "^\\d{1,7}(\\.\\d{0,2})?$";

		return regex(regex, src, true);
	}

	/**
	 * 正则验证
	 * 
	 * @param regex
	 * @param src
	 * @param match
	 *            true为全匹配， false为包含
	 * @return
	 */
	public static boolean regex(String regex, String src, boolean match) {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(src);

		if (match)
			return matcher.matches();

		return matcher.find();
	}

	/**
	 * 日期格式为yyyyMMdd中间用spliteStr连接
	 * 
	 * @param splitStr
	 * @param src
	 *            不包含时间
	 * @return
	 */
	public static long parseDate(String splitStr, String src) {

		String[] time = src.split(splitStr);
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(time[0]), Integer.parseInt(time[1]) - 1, Integer
				.parseInt(time[2]));

		return c.getTimeInMillis();
	}

	public static String getMoney(String format, double money) {

		if (isEmptyString(format))
			format = "0.00";

		DecimalFormat decimalFormat = new DecimalFormat(format);

		return decimalFormat.format(money);
	}

	/**
	 * 
	 * 基本功能：替换指定的标签属性
	 * <p>
	 * 
	 * @param str
	 * @param tagName
	 *            要替换属性的标签正则
	 * @param tagAttrib
	 *            要替换的标签属性
	 * @param oldValueRegxp
	 *            要替换的标签属性值正则
	 * @param newValue
	 *            新属性值
	 * @return String
	 */
	public static String replaceHtmlTag(String str, String tagRegxp,
			String tagAttrib, String newValue, boolean append) {
		String regxpForTag = "(<\\s*" + tagRegxp + "\\s+[^>]*\\s*>)";
		String regxpForTagAttrib = "(?i)" + tagAttrib
				+ "=(\"([^\"]+)\"|\'([^\']+)\')";
		Pattern patternForTag = Pattern.compile(regxpForTag,
				Pattern.CASE_INSENSITIVE);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib,
				Pattern.CASE_INSENSITIVE);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			// System.out.println(matcherForTag.group(1));
			String newAttr = "";
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
					.group(1));
			if (matcherForAttrib.find()) {
				if (isEmptyString(newValue))
					newAttr = matcherForTag.group(1).replaceAll(
							"(?i)" + tagAttrib + "="
									+ matcherForAttrib.group(1), "");
				else
					newAttr = matcherForTag.group(1).replaceAll(
							matcherForAttrib.group(1), "\"" + newValue + "\"");
			} else if (append) {
				newAttr = matcherForTag.group(1).replace(
						">",
						new StringBuilder(" ").append(tagAttrib).append("=\"")
								.append(newValue).append("\">").toString());
			} else {
				newAttr = matcherForTag.group(1);
			}
			matcherForTag.appendReplacement(sb, newAttr);
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}

	public static String filterHtml(String str) {
		if (isEmptyString(str))
			return "";
		Pattern pattern = Pattern.compile("<([^>]*)>");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static boolean hasTag(String tagRegxp, String src) {
		if (isEmptyString(tagRegxp) || isEmptyString(src))
			return false;
		Pattern pattern = Pattern
				.compile("<\\s*" + tagRegxp + "\\s+[^>]*\\s*>",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(src);
		return matcher.find();
	}

	/**
	 * 去除所有标签的id属性
	 */
	public static String filterElementId(String str) {
		if (isEmptyString(str))
			return "";
		return replaceHtmlTag(str, "[^>]*", "id", "", false);
	}

	/**
	 * 替换a标签的target属性值为_blank
	 */
	public static String filterHrefTarget(String str) {
		if (isEmptyString(str))
			return "";
		return replaceHtmlTag(str, "(?i)a", "target", "_blank", true);
	}

	public static void main(String args[]) {

		String str = "<div><img src=\"http://pic1.myphoto.net.cn/attachments/day_090103/20090103_fdc85ed6d0e69e3576f9QnznHVGc9xj0.jpg\" alt=\"\" width=\"640\" height=\"480\" /></div><A class=\"pl\" id=\"vs\" tArget=\"_self\">开始时间: </a>1月2日 周五 15:55<br><A class=\"pl\" Id=\"vs\">开始时间: </a><span class='pl' ID='in_tablem'>结束时间: </span>";
		System.out.println(str);
		System.out.println(filterElementId(str));
		System.out.println(filterHrefTarget(str));
		System.out.println(hasTag("img", str));

	}
	
	
//	/**
//	 * 创建小组或注册用户时copy默认的icon
//	 * 
//	 * @param savedir
//	 * @param fileName
//	 * @return
//	 * @throws Exception
//	 */
//	public static int copyIcon(String savedir, String fileName)
//			throws Exception {
//
//		StringBuilder sb = new StringBuilder(Constant.IMG_DRAW_PATH).append(
//				"9dcopyimg.php").append("?savedir=").append(savedir).append(
//				"&filename=").append(fileName);
//
//		URL url = new URL(sb.toString());
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//		return conn.getResponseCode();
//	}

	/**
	 * 格式化double类型的变量，保留几位小数
	 * @param d
	 * @param format：格式为".00"
	 * @return
	 */
	public static double doubleFormat(double d,String format) {
		
		double d1 = new Double(new DecimalFormat(format).format(d));
//		double d2 = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		return d1;
	}
	
	/**
	 * 去除内容中包含的html代码
	 * @param src
	 * @return
	 */
	public static String removeHtmlCode(String src) {
	    String regEx="<.+?>"; //表示标签
	    Pattern p=Pattern.compile(regEx);
	    Matcher m=p.matcher(src);
	    return m.replaceAll("");
	}
}
