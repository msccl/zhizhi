/**
 * 
 */
package com.qkzz.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析工具
 * 
 * @author cooper
 * @since jdk1.5
 * @version 1.0 Mar 11, 2008 4:35:57 PM
 */
public class ParseUtil {

	public static List<String> parseStrs(String sourceStr, String patternStr) {
		List<String> ret = new ArrayList<String>();
		StringBuilder input = new StringBuilder(sourceStr);
		Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String match = input.substring(start, end).trim();
			ret.add(match);
		}

		return ret;
	}

	public static List<String> parseStrs(String sourceStr, String patternStr,
			int group) {
		List<String> ret = new ArrayList<String>();
		StringBuilder input = new StringBuilder(sourceStr);
		Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find() && group <= matcher.groupCount()) {
			ret.add(matcher.group(group).trim());
		}

		return ret;
	}

	public static String parseStr(String sourceStr, String patternStr) {
		String ret = "";
		StringBuilder input = new StringBuilder(sourceStr);
		Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {

			int start = matcher.start();
			int end = matcher.end();
			ret = input.substring(start, end).trim();
		}

		return ret;
	}

	public static String parseStr(String sourceStr, String patternStr, int group) {
		String ret = "";
		StringBuilder input = new StringBuilder(sourceStr);
		Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);

		if (matcher.find() && group <= matcher.groupCount()) {

			ret = matcher.group(group).trim();
		}

		return ret;
	}

}
