package com.qkzz.global;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qkzz.log.GlobalLog;

public class QKFilter implements Filter {
	
	// 不过滤的url前缀
	private static String[] escapedPrefix = new String[0];
	// 是否存在特殊的不过滤的url
	private static boolean ESCAPE_URL = false;

	public void init(FilterConfig filterConfig) throws ServletException {
		// 设定不过滤的路径
		String nonfilter = filterConfig.getInitParameter("nonfilter");
		if (nonfilter == null || nonfilter.trim().equals("")) {
			ESCAPE_URL = false;
			escapedPrefix = new String[0];

		} else {
			ESCAPE_URL = true;
			try {
				escapedPrefix = nonfilter.split(",");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

	    req.setCharacterEncoding("utf-8"); //对转换后的对象进行设置编码方�?

	    if (ESCAPE_URL) {
			// 不需要过滤的页面在这里判�?
			String requrl = request.getServletPath();
			for (int i = 0; i < escapedPrefix.length; i++) {
				if (requrl.startsWith(escapedPrefix[i])) {
					chain.doFilter(request, response);
					return;
				}
			}
		}


		String fromUrl = request.getHeader("Referer");

		//stat log
		GlobalLog.statLog(request);
		
		chain.doFilter(request, response);
	}
	
    
   public static boolean isValidateURI(String regex, String uri){
  	  Pattern p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  	  Matcher m=p.matcher(uri);
  	  return m.find();
  }

}
