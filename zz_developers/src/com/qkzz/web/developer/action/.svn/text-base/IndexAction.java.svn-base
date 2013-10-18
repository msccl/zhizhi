package com.qkzz.web.developer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qkzz.util.CookieUtil;
import com.qkzz.web.developer.bean.Developers;

@Controller
public class IndexAction {
	
	/**
	 * 开发者首页，显示开发者信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String devIndex(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieUser(request,response);
		if(ub != null) {
			System.out.println("111111");
			return "redirect:/developers/index";
		}
		System.out.println("2222222");
		request.setAttribute("pageTitle", "开发者管理系统");
		request.setAttribute("user", ub);
		
		return "index";
	}

}
