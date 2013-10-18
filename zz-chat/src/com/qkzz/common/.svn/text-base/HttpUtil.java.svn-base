package com.qkzz.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	private HttpUtil() {
	}

	/**
	 * 打印出http请求的header和request的数据
	 * 
	 * @param request
	 */
	public static void printRequest(HttpServletRequest request) {
		System.out.println("----------Request Content Start--------------");
		System.out.println("Header:");
		java.util.Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			System.out.println(name + ":" + request.getHeader(name));
		}
		System.out.println("Parameter:");
		enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			System.out.println(name + ":" + request.getParameter(name));
		}
		System.out.println("----------Request Content End--------------");
	}

	/**
	 * 获取request中的整参数，如果不存在或错误则则返回0
	 */
	public static int getInt(HttpServletRequest request, String paraname) {
		try {
			return Integer.parseInt(request.getParameter(paraname));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getInt(HttpServletRequest request, String paraname,
			int defaultVal) {
		try {
			return Integer.parseInt(request.getParameter(paraname));
		} catch (Exception e) {
			return defaultVal;
		}
	}

	public static long getLong(HttpServletRequest request, String paraname) {
		try {
			return Long.parseLong(request.getParameter(paraname));
		} catch (Exception e) {
			return 0;
		}
	}

	public static long getLong(HttpServletRequest request, String paraname,
			int defaultVal) {
		try {
			return Long.parseLong(request.getParameter(paraname));
		} catch (Exception e) {
			return defaultVal;
		}
	}

	
	public static String getString(HttpServletRequest request, String paraname) {
		return request.getParameter(paraname) == null ? "" : request
				.getParameter(paraname);
	}

	public static String getString(HttpServletRequest request, String paraname,
			String defaultVal) {
		return request.getParameter(paraname) == null ? defaultVal : request
				.getParameter(paraname);
	}

	public static String strReplace(String line, String oldString,
			String newString) {
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int oLength = oldString.length();
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

	public static String getMethod(String getURL) {

		String sRet = "";
		try {

			URL url;
			URLConnection urlconn;
			try {
				url = new URL(getURL);
				// System.out.println("geturl:"+getURL);
				urlconn = url.openConnection();
				urlconn.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						urlconn.getInputStream()));
				String str = null;
				while (null != ((str = br.readLine()))) {
					sRet += str;
				}
			} catch (MalformedURLException mfe) {
				mfe.printStackTrace();
				sRet = "MalformedURLException";
			} catch (ConnectException ex) {
				ex.printStackTrace();
				sRet = "ConnectException";
			} catch (IOException ioe) {
				ioe.printStackTrace();
				sRet = "IOException";
			}
		} catch (Exception e) {
			e.printStackTrace();
			sRet = "UnknownException";
		}
		sRet = sRet.trim();
		return sRet;
	}

	public static String formatPageUrl(String srcUrl, String defaultUrl) {
		if (srcUrl == null) {
			return defaultUrl.endsWith("?") ? defaultUrl : defaultUrl + "?";
		}
		if (!srcUrl.startsWith("http://")) {
			srcUrl = "http://" + srcUrl;
		}
		if (!srcUrl.endsWith("?")) {
			srcUrl = srcUrl + "?";
		}
		return srcUrl;
	}

	public static String Post2Url(String destUrl, String content) {
		String sRet = "";
		try {
			URL url;
			URLConnection urlConn;
			DataOutputStream printout;

			url = new URL(destUrl);

			urlConn = url.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);

			// No caching, we want the real thing.
			urlConn.setUseCaches(false);

			// Specify the content type.
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConn.setRequestProperty("Content-Type", "text/plain");
			// Get around any basic authentication checks.
			// Note that you'll need to provide the base64Encode()
			// method.
			// Send POST output.
			printout = new DataOutputStream(urlConn.getOutputStream());

			printout.writeBytes(content);
			printout.flush();
			printout.close();

			// Get response data.
			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));

			String str = null;
			while (null != ((str = br.readLine()))) {
				sRet += str;
			}
			br.close();
		} catch (MalformedURLException me) {
			me.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return sRet;
	}

}
