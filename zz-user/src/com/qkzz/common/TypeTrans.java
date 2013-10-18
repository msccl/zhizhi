package com.qkzz.common;

import java.util.StringTokenizer;

public class TypeTrans {
	private TypeTrans() {
	}

	public static String getDate(String format) {
		java.util.Date datetime = new java.util.Date();
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(format);
		String strDate = fmt.format(datetime);
		return strDate;
	}

	public static final String delimiter = "#";

	public static String[] split(String src) {
		return split(src, delimiter);
	}

	static java.util.Random rand = new java.util.Random(new java.util.Date()
			.getTime());

	static char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static String randAsciiString(int len) {
		StringBuffer sb = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			sb.append(chars[rand.nextInt(chars.length)]);
		}
		return new String(sb);
	}

	public static String randNumberString(int len) {
		StringBuffer sb = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			sb.append(chars[rand.nextInt(10)]);
		}
		return new String(sb);
	}

	/**
	 * get a random number in range :[0,limit)
	 * 
	 * @param limit
	 *            int
	 * @return int
	 */
	public static int getRand(int limit) {
		if (limit < 1) {
			return 0;
		}
		return rand.nextInt(limit);
	}

	public static int getRand(int min, int max) {
		if (max <= min)
			return min;
		return min + rand.nextInt(max - min);
	}

	/**
	 * 指定分割串的字符串分割函数
	 * 
	 * @param source
	 *            String
	 * @param delimiter
	 *            String
	 * @return String[]
	 */
	public static String[] split(String source, String delimiter) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		StringTokenizer st = new StringTokenizer(source, delimiter);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 字符串数组转整形数组，若非数值，则设置为defaultvalue
	 * 
	 * @param src
	 *            String[]
	 * @param defaultvalue
	 *            int
	 * @return int[]
	 */
	public static int[] str2int(String[] src, int defaultvalue) {
		int[] ret = new int[src.length];
		for (int i = 0; i < src.length; i++) {
			try {
				ret[i] = Integer.parseInt(src[i]);
			} catch (NumberFormatException ex) {
				ret[i] = defaultvalue;
			}
		}
		return ret;
	}

	public static int getInt(String src, int defaultvalue) {
		if (src == null)
			return defaultvalue;
		int ret = defaultvalue;
		try {
			ret = Integer.parseInt(src);
		} catch (NumberFormatException ex) {
		}
		return ret;
	}

	public static boolean isNumber(String sIn) {
		boolean bCheck = true;

		if (sIn == null || sIn.length() == 0)
			return false;

		char[] cIn = sIn.toCharArray();

		for (int i = 0; i < cIn.length; i++) {
			if (cIn[i] < '0' || cIn[i] > '9') {
				bCheck = false;

				break;
			}
		}

		return bCheck;
	}

	public static String toHTMLString(String in) {
		// 检查input是不是null或则是空字符串，如果是就直接返回input
		if (in == null || in.length() == 0) {
			return in;
		}

		StringBuffer out = new StringBuffer();
		for (int i = 0; in != null && i < in.length(); i++) {
			char c = in.charAt(i);
			if (c == '\'') {
				out.append("&#39;");
			} else if (c == '\"') {
				out.append("&#34;");
			} else if (c == '<') {
				out.append("&lt;");
			} else if (c == '>') {
				out.append("&gt;");
			} else if (c == '&') {
				out.append("&amp;");
			} else if (c == '%') {
				out.append("&#37;");
			} else if (c == '$') {
				out.append("$$");
			} else if (c >= 0 && c < 32) {
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

	public static long toTime(String date) {// format "yyyyMMddHHmmss"
		java.util.Calendar c = java.util.Calendar.getInstance();
		int y = Integer.parseInt(date.substring(0, 4));
		int M = Integer.parseInt(date.substring(4, 6)) - 1;
		int d = Integer.parseInt(date.substring(6, 8));
		int h = Integer.parseInt(date.substring(8, 10));
		int m = Integer.parseInt(date.substring(10, 12));
		int s = Integer.parseInt(date.substring(12));
		c.set(y, M, d, h, m, s);
		return c.getTime().getTime();
	}

	public static String strReplace(String line, String oldString,
			String newString) {
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int oLength = oldString.length();
			// int nLength = newString.length();
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(line.substring(0, i)).append(newString);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				stringbuffer.append(line.substring(j, i)).append(newString);
				i += oLength;
				j = i;
			}
			stringbuffer.append(line.substring(j));
			return stringbuffer.toString();
		}
		return line;
	}

}
