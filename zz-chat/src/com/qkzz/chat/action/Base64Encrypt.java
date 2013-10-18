package com.qkzz.chat.action;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @description Base64 Encrypt
 * 
 * @author Sunny
 * 
 */

public class Base64Encrypt {
	/**
	 * decoder base64
	 * 
	 * @param source
	 * @return
	 */
	public static final String decoder(String source) {
		if (source == null) {
			return null;
		}
		try {
			byte[] buff = new BASE64Decoder().decodeBuffer(source);
			return new String(buff);
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * decoder base64
	 * 
	 * @param source
	 * @return
	 */
	public static final byte[] decoder(byte[] source) {
		if (source == null) {
			return null;
		}
		try {
			return new BASE64Decoder()
					.decodeBuffer(new java.io.ByteArrayInputStream(source));
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * encoder base64
	 * 
	 * @param source
	 * @return
	 */
	public static final String encoder(String source) {
		if (source == null) {
			return null;
		}
		return new BASE64Encoder().encode(source.getBytes());
	}

	/**
	 * encoder base64
	 * 
	 * @param source
	 * @return
	 */
	public static final byte[] encoder(byte[] source) {
		if (source == null) {
			return null;
		}
		return new BASE64Encoder().encode(source).getBytes();
	}
}
