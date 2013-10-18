package com.qkzz.web.developer.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
  
public class UserHandlerInterceptor extends HandlerInterceptorAdapter {
  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
//		String uri = "?url="+URLEncoder.encode(ToolsKit.getUrl(request),"UTF-8");

        System.out.println(request.getRequestURL()+url);
        if(url.endsWith("/") || url.endsWith("/index") || url.endsWith("/developers/login") || url.endsWith("/developers/logout")||url.endsWith("/developers/register")||url.endsWith("/developers/registerConfirm")||url.endsWith("/developers/register/1")||url.endsWith("/validate")){
            return true;
        }
        if(request.getSession() != null && request.getSession().getAttribute("userSessionInfo") != null){
        	return true;
        }
        
        response.sendRedirect(request.getContextPath() + "/developers/login");
        return false;
    }  
      
} 