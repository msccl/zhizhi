package com.qkzz.common;

import java.util.StringTokenizer;

public class UBBCode {
	/**
	 * 进行UBB代码转换
	 * 
	 * @param content
	 *            String - 包含UBB代码的内容
	 * @param bbCode
	 *            String - UBB代码，例如 url,img,多个内容按照|分隔
	 * @return String
	 */
	public static String encode(String content, String bbCode) {
		if (content == null)
			return null;

		String bb = bbCode;
		String s = content.replaceAll("\\(url\\)", "[url]").replaceAll(
				"\\(/url\\)", "[/url]").replaceAll("\\(img\\)", "[img]")
				.replaceAll("\\(/img\\)", "[/img]");

		if (bb == null) {
			return doFilterOne("///", s);
		}

		StringTokenizer token = new StringTokenizer(bb, "|");
		int count = token.countTokens();
		for (int i = 0; i < count; i++) {
			String curUbb = token.nextToken();
			s = doFilterOne(curUbb, s);
		}

		return doFilterOne("///", s).replaceAll("\\??z=", "&amp;z=")
				.replaceAll("jsp\\??", "jsp?").replaceAll("\\.do\\??", ".do?");

	}

	public static String doFilterOne(String ubbCode, String content) {
		String regex = "";
		String rpStr = "";

		if (ubbCode.equals("url")) {
			// URL解析
			regex = "(\\[url\\])(.[^\\[]*)(\\[\\/url\\])";
			rpStr = "<a href=\"$2\">$2</a>";
			content = doFiltr(regex, rpStr, content);

			regex = "(\\[url=(.[^\\[]*)\\])(.[^\\[]*)(\\[\\/url\\])";
			rpStr = "<a href=\"$2\">$3</a>";
			content = doFiltr(regex, rpStr, content);

		} else if (ubbCode.equals("img")) {
			// 图片格式解析
			regex = "\\[img\\]((http:(\\/\\/|\\\\)){1}((\\w)+[.]){1,3}(net|com|cn|org|cc|tv)(((\\/[\\~]*|\\[\\~]*)(\\w)+)|[.](\\w)+)*(\\w)+[.]{1}(gif|jpg|jpeg|bmp|png|mp3|mid|wma|3gp|mmf|amr|jad))\\[\\/img\\]";
			// rpStr = "<a href='$1'><img src='$1' alt='$1'/></a>";
			rpStr = "<img src='$1' alt='$1'/>";
			content = doFiltr(regex, rpStr, content);

		} else if (ubbCode.equals("///")) {
			// 换行解析
			regex = "///";
			rpStr = "<br/>";
			content = doFiltr(regex, rpStr, content);

		} else if (ubbCode.equals("m")) {
			// 媒体格式解析
			regex = "\\[m\\]((http:(\\/\\/|\\\\)){1}((\\w)+[.]){1,3}(net|com|cn|org|cc|tv)(((\\/[\\~]*|\\[\\~]*)(\\w)+)|[.](\\w)+)*(\\w)+[.]{1}(mp3|mid|wma|3gp|jar|sis|mmf|amr|jad|pmd|m4a|acc|aac|ogg|mp4))\\[\\/m\\]";
			rpStr = "<a href='$1'>$1</a>";
			content = doFiltr(regex, rpStr, content);

		}
		return content;

	}

	public static String doFiltr(String regex, String rpStr, String content) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex,
				java.util.regex.Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher m = p.matcher(content);
		content = m.replaceAll(rpStr);
		return content;
	}

}
