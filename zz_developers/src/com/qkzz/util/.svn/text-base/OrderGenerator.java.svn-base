package com.qkzz.util;

import java.text.SimpleDateFormat;

public class OrderGenerator {

	public static String createIndentnumber() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		return sdf.format(new java.util.Date());
	}

	public static String createIndentnumber(String uid) {
		return createIndentnumber() + uid + Tools.Rand(0, 9);
	}

	public static String createIndentnumber(String uid, String touid) {
		return createIndentnumber() + uid + Tools.Rand(0, 9) + "-"
				+ Tools.Rand(10, 99) + touid + Tools.Rand(0, 9);
	}

}
