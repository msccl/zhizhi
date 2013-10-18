package com.qkzz.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qkzz.web.developer.bean.Developers;

public class CookieUtil {

	public CookieUtil() {
	}
	
	public static final String AUTO_UID = "AutoUID";
	public static final String AUTO_LOGIN = "/developers/login";

	/**
	 * 根据name值,从cookie当中取值
	 * 
	 * @param name
	 *            要获取的name
	 * @param request
	 *            cookie存在的对象
	 * @return 与name对应的cookie值
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cs = request.getCookies();
		String value = null;
		if (cs != null) {
			for (Cookie c : cs) {
				if (name.equals(c.getName())) {
					try {
						if (null != c.getValue() && !"".equals(c.getValue())) {
							value = URLDecoder.decode(c.getValue(), "UTF-8");
						}
					} catch (UnsupportedEncodingException e) {
					}
					return value;
				}
			}
		}
		return value;
	}

	/**
	 * 添加一个cookie值
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param time
	 *            cookie的有效期
	 * @param response
	 *            保存cookie的对象
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, Integer time) {
		try {
			if (null != value && !"".equals(value)) {
				value = URLEncoder.encode(value, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
//		cookie.setDomain("zizi.im");
		cookie.setMaxAge(time);
		response.addCookie(cookie);
	}

	/**
	 * cookie Login
	 * 
	 * @param request
	 * @return
	 */
	public static Developers getCookieUser(HttpServletRequest request, HttpServletResponse response) {
		String cookie = CookieUtil.getCookie(request, AUTO_UID);
		Developers user = null;
		if (cookie != null){
    		String[] object = getUser(request);
    		if(object!=null && object.length>1){
	    		user = new Developers();
	    		int uid = Integer.parseInt(object[0]);
				String name = object[1];
				CookieUtil.setCookie(response, AUTO_UID, createString(uid,name), 60*30);
				user.setId(uid);
				user.setName(name);
    			
    		}
		}
		return user;
	}
	
    /**
     * 判断用户是否登录并获取用户ID
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    public static Developers getCookieLoginUser(HttpServletRequest request,HttpServletResponse response) {
		try {
	    	String cookie = getCookie(request, AUTO_UID);
	    	Developers user = null;
			@SuppressWarnings("unused")
			HttpSession seesion = request.getSession();
			String uri = ToolsKit.getUrl(request)==null?"":"?url="+URLEncoder.encode(ToolsKit.getUrl(request),"UTF-8");
	    	if(cookie==null){
	    		response.sendRedirect(AUTO_LOGIN + uri);
	    		return null;
	    	}else{
	    		String[] object = getUser(request);
	    		if(object!=null && object.length>1){
		    		user = new Developers();
		    		int uid = Integer.parseInt(object[0]);
					String name = object[1];
					CookieUtil.setCookie(response, AUTO_UID, createString(uid,name), 60*30);
					user.setId(uid);
					user.setName(name);
	    			
	    		}
	    	}
	    	return user;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
   public static String createString(int uid, String name){
    	return  createString(uid, name,0);
   }
   public static String createString(int uid, String name,int message){
   	return  uid +","+ name +","+ message;
   }
   
	public static String[] getUser(HttpServletRequest request) {
		String str = getCookie(request, AUTO_UID);
		if(str!=null){
			String[] object = str.split(",");
			if(object.length>1){
				return object;
			}
		}
		return null;
	}
	
}
