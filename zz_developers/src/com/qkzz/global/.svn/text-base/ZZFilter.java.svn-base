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

import com.qkzz.util.CookieUtil;
import com.qkzz.web.developer.bean.Developers;

public class ZZFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//从cookie获取用户
		Developers ub = CookieUtil.getCookieUser(request,response);
		//判断该用户是否为注册用户
//		if(!ub.isGuest()){
//			//删除online超时用户
//			OnlineService.deleteTimeOut();
//			//是否存在online表
//			if(OnlineService.isExist(ub.getId())){
//				Online oo = OnlineService.getById(ub.getId());
//				ub.setMobile(oo.getMobile());
//			}else{
//				ub = UserService.getById(ub.getId());
//			}
//		}
//		String uri = ToolsKit.getUrl(request);
//		//更新online
//		if(!isValidateURI("/im", uri)){
//			updateOnline(request, ub);
//		}
//		//写日志
//		if(!isValidateURI(".html", uri)){
//			StatLog.pvLog(GetDate.getStringDate(), ToolsKit.getRemoteAddr(request), ub.getId(), uri, request.getHeader("User-Agent"));
//		}
		
		chain.doFilter(request, response);
	}
	
	/**
	 * add/update temporary user online
	 */
//    public static void updateOnline(HttpServletRequest request, User user){
//		if(OnlineService.isExist(user.getId())){
//			OnlineService.update(user.getId(), System.currentTimeMillis(), ToolsKit.getUrl(request));
//		} else {
//			OnlineService.save(user.getId(), user.getName(), user.getMobile(), System.currentTimeMillis(), ToolsKit.getUrl(request));
//		}
//    }
    
   public static boolean isValidateURI(String regex, String uri){
  	  Pattern p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  	  Matcher m=p.matcher(uri);
  	  return m.find();
  }

}
