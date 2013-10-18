package com.qkzz.util;

import java.io.IOException;

public class Base64 {

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static String decode(String str) {
		try {
			byte[] bt = null;
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
			return new String(bt);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String aa = "1";
		aa = Base64.encode(aa.getBytes());
		System.out.println("----aa:" + aa);
		String str = aa;
		String str2 = Base64.decode(str);
		System.out.println("-----str2:" + str2);
	}

}
