package com.qkzz.web.developer.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qkzz.util.Constant;
import com.qkzz.util.CookieUtil;
import com.qkzz.util.LoadResour;
import com.qkzz.util.MD5Encrypt;
import com.qkzz.util.OperationFile;
import com.qkzz.util.Tools;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.service.DevelopersService;

@Controller
public class DevelopersAction {

	private String imgpath = "/img";
	
	@RequestMapping(value = "/developers/register", method = RequestMethod.GET)
	public String regDevelopers(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieUser(request,response);
		request.setAttribute("navid", 1);//用于页面顶部导航
		if (ub != null) {
			return Constant.USER_INDEX;
		}
		
		request.setAttribute("pageTitle", "用户注册");
		
		return "developers_jsp/register";

	}

	/**
	 * 注册信息确认页
	 * @param obj
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/developers/registerConfirm", method = RequestMethod.POST)
	public String registerConfirm(Developers obj, 
			BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		//用户登录状态验证
		Developers ub = CookieUtil.getCookieUser(request,response);
		request.setAttribute("navid", 1);//用于页面顶部导航
		if (ub != null) {
			System.out.println("11111111");
			return Constant.USER_INDEX;
		} else {
			System.out.println("222222222");
		}
		
		request.setAttribute("info", obj);
		request.setAttribute("passwordc", request.getParameter("passwordc"));
		System.out.println("33333333");
		return "developers_jsp/registerconfirm";
	}
	
	
	@RequestMapping(value = "/developers/register", method = RequestMethod.POST)
	public void create(@RequestParam("valicode") String valicode, Developers obj, 
			BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//用户登录状态验证
		Developers ub = CookieUtil.getCookieUser(request,response);
		PrintWriter out = response.getWriter();
		
		request.setAttribute("navid", 1);//用于页面顶部导航
		if (ub != null) {
//			return Constant.USER_INDEX;
			out.println("{\"result\":\"-1\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		
		System.out.println("----------注册提交");
		
		//表单验证
		if(!StringUtils.hasText(valicode)){
//			request.setAttribute("ERR_MSG", "请输入验证码");
//			return "developers_jsp/register";
			
			out.println("{\"result\":\"-2\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		
		String vali = "";
		HttpSession hson = request.getSession();
		if(hson.getAttribute("validateCode")!=null){
			vali = (String)hson.getAttribute("validateCode");
		}
		
		System.out.println("vali:"+vali+",valicode:"+valicode);
		
		if(!vali.equals(valicode)){
//			request.setAttribute("ERR_MSG", "验证码输入错误");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-3\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		System.out.println("----------001");

		if(!StringUtils.hasText(obj.getEmail())){
//			request.setAttribute("ERR_MSG", "请输入Email.");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-4\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		
		if(DevelopersService.isEmailExist(obj.getEmail().trim())){
//			request.setAttribute("ERR_MSG", "邮箱已存在,请重新输入");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-5\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		
		System.out.println("----------002");
		
//		if(!StringUtils.hasText(obj.getName())){
//			request.setAttribute("ERR_MSG", "请输入用户名");
//			return "developers_jsp/register";
//		}
		System.out.println("----------003");
		
		if(!StringUtils.hasText(obj.getPassword())){
//			request.setAttribute("ERR_MSG", "请输入密码");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-6\"}");
	        out.flush();    
	        out.close();
	        return;

		}
		if(!StringUtils.hasText(request.getParameter("passwordc"))){
//			request.setAttribute("ERR_MSG", "请输入确认密码");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-7\"}");
	        out.flush();    
	        out.close();
	        return;
			
		}
		if(!obj.getPassword().equals(request.getParameter("passwordc"))){
//			request.setAttribute("ERR_MSG", "密码和确认密码不一致.");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-8\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		System.out.println("----------004");
		
		
		//写入用户信息表
		obj.setName("");
		obj.setIdentifier(MD5Encrypt.encoderForString(System.currentTimeMillis()+""+Tools.Rand(100, 999)));
		int res = DevelopersService.add(obj);
		if (res < 0) {
//			request.setAttribute("ERR_MSG", "注册失败,请联系管理员");
//			return "developers_jsp/register";

			out.println("{\"result\":\"-9\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		
		System.out.println("----------注册成功.");
		
		Developers user = DevelopersService.getByLogin(obj.getEmail(), obj.getPassword());

//		request.getSession().setAttribute("userSessionInfo", user);
		
        /* addCookie */
//		CookieUtil.setCookie(response, CookieUtil.AUTO_UID, CookieUtil.createString(user.getId(), user.getEmail()), 60*30);

//		return "redirect:/developers/register/1";

		out.println("{\"result\":\"0\"}");
        out.flush();    
        out.close();

	}
	
    /** register success, tips */
    @RequestMapping(value = "/developers/register/{tips}", method = RequestMethod.GET)
	public String tips(@PathVariable("tips")int flag,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.getCookieUser(request,response);

		request.setAttribute("navid", 1);//用于页面顶部导航
		model.addAttribute("pageTitle", "操作提示");
		model.addAttribute("flag", flag);
		
		return "developers_jsp/register_tips";
	}


    /** 登录页面 
     * @throws UnsupportedEncodingException */
	@RequestMapping(value = "/developers/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "url", required = false) String url,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//		Developers ub = CookieUtil.getCookieUser(request,response);
//		if (ub != null) {
//			return Constant.USER_INDEX;
//		}
		
		request.setAttribute("navid", 1);//用于页面顶部导航
		request.setAttribute("pageTitle", "用户登录");
		request.setAttribute("url", url==null?"":url);
		request.setAttribute("en_url", url==null?"":"?url="+URLEncoder.encode(url,"UTF-8"));
		
		return "developers_jsp/login";
	}
	
	/** 登录提交 
	 * @throws UnsupportedEncodingException */
	@RequestMapping(value = "/developers/login", method = RequestMethod.POST)
	public String processSubmit(@RequestParam(value = "email", required = false) String email, 
			@RequestParam(value = "password", required = false) String password,
//			@RequestParam(value = "valicode", required = false) String valicode,
			@RequestParam(value = "url", required = false) String url,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//		Developers ub = CookieUtil.getCookieUser(request,response);
//		if (ub != null) {
//			return Constant.USER_INDEX;
//		}
		
		System.out.println("----------登录提交:"+email+","+ password);
		
		request.setAttribute("navid", 1);//用于页面顶部导航
		request.setAttribute("pageTitle", "用户登录");
//		if(!StringUtils.hasText(valicode)){
//			request.setAttribute("ERR_MSG", "验证码不能为空");
//			request.setAttribute("url", url==null?"":url);
//			request.setAttribute("en_url", url==null?"":"?url="+URLEncoder.encode(url,"UTF-8"));
//			return "developers_jsp/login";
//		}
//		String vali = "";
//		HttpSession hson = request.getSession();
//		if(hson.getAttribute("validateCode")!=null){
//			vali = (String)hson.getAttribute("validateCode");
//		}
//		if(!vali.equals(valicode)){
//			request.setAttribute("ERR_MSG", "验证码输入错误");
//			request.setAttribute("url", url==null?"":url);
//			request.setAttribute("en_url", url==null?"":"?url="+URLEncoder.encode(url,"UTF-8"));
//			return "developers_jsp/login";
//		}

		if(!StringUtils.hasText(email)){
			request.setAttribute("ERR_MSG", "登陆Email不能为空");
			request.setAttribute("url", url==null?"":url);
			request.setAttribute("en_url", url==null?"":"?url="+URLEncoder.encode(url,"UTF-8"));
			return "developers_jsp/login";
		}
		
		if(!StringUtils.hasText(password)){
			request.setAttribute("ERR_MSG", "密码不能为空");
			request.setAttribute("url", url==null?"":url);
			request.setAttribute("en_url", url==null?"":"?url="+URLEncoder.encode(url,"UTF-8"));
			return "developers_jsp/login";
		}

		//user login
		Developers ub = DevelopersService.getByLogin(email, password);
		if (ub == null) {
			request.setAttribute("ERR_MSG", "登录失败,请核对用户Email和密码并重新登录");
			request.setAttribute("url", url==null?"":url);
			request.setAttribute("en_url", url==null?"":"?url="+URLEncoder.encode(url,"UTF-8"));
			return "developers_jsp/login";
		}

		if (ub.getIsverified() == 0) {
			request.setAttribute("ERR_MSG", "您的注册信息尚未通过审核，请稍后尝试登陆！");
			return "developers_jsp/login";
		}
		
		System.out.println("----------登录成功:"+email+","+ password);
		
		request.getSession().setAttribute("userSessionInfo", ub);
		
		/* addCookie, Keep cookies 10 Minutes */
		CookieUtil.setCookie(response, CookieUtil.AUTO_UID, CookieUtil.createString(ub.getId(),ub.getEmail()), 60*30);
		
		if(StringUtils.hasText(url)){
			return "redirect:"+url;
		}
		return Constant.USER_INDEX;

	}
	
	/** 安全退出 */
	@RequestMapping(value = "/developers/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		/* addCookie, Keep cookies */
		CookieUtil.setCookie(response, CookieUtil.AUTO_UID, null, 0);
		request.getSession().removeAttribute("userSessionInfo");
		
		return "redirect:" + Constant.DEV_INDEX;
	}

    
	/**
	 * 用户详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/developers/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request,response);
		request.setAttribute("navid", 4);//用于页面顶部导航
		ub = DevelopersService.getByID(ub.getId());
		
		request.setAttribute("pageTitle", "详细");
		request.setAttribute("user", ub);
		
		return "developers_jsp/detail";

	}

	
	/**
	 * 编辑信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/developers/edit", method = RequestMethod.GET)
	public String editInfo(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request,response);
		request.setAttribute("navid", 4);//用于页面顶部导航
		ub = DevelopersService.getByID(ub.getId());

		request.setAttribute("pageTitle", "用户编辑");
		request.setAttribute("user", ub);

		return "developers_jsp/edit";

	}

	
	@RequestMapping(value = "/developers/edit", method = RequestMethod.POST)
	public void doEditInfo(@RequestParam("logo") MultipartFile image,
			Developers obj, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request,response);
		request.setAttribute("navid", 4);//用于页面顶部导航
		request.setAttribute("user", ub);

		request.setAttribute("pageTitle", "用户编辑");
		PrintWriter out = response.getWriter();
		
		int uid = ub.getId();
		if(uid == 0) {
			//用户ID丢失
			out.println(-1);
	        out.flush();    
	        out.close();
	        return;
		}
		
		Developers bean = DevelopersService.getByID(uid);
		if(bean == null) {
			//用户不存在
			//用户ID丢失
			out.println(-2);
	        out.flush();    
	        out.close();
	        return;
		}
	        
		//需要重新上传处理
		if(!"".equals(image.getOriginalFilename())){

			// create file name
			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(image.getOriginalFilename());
			
			// create file path
			String filepath = imgpath + LoadResour.getFilePath();
			//System.out.println(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			LoadResour.mkdir(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			
			// upload file
			//FileCopyUtils.copy(swf.getBytes(), new File(LoadResour.getMMSRootPath("HTMLRoot")	+ filepath + filename));
			image.transferTo(new File(new File(LoadResour.getMMSRootPath("HTMLRoot")), filepath + filename));
			
			System.out.println("-----------------------------------");
			System.out.println(image.getOriginalFilename());
			System.out.println("-----------------------------------");
			
			//删除原 文件
			if(StringUtils.hasText(bean.getLogo())){
				OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), bean.getLogo());
				OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
			}
			
			bean.setLogo("http://s.7kzz.com" + filepath + filename);
		}
		
		bean.setName(obj.getName());
		bean.setIntro(obj.getIntro());
		bean.setMembers(obj.getMembers());
		bean.setProvinceid(obj.getProvinceid());
		bean.setCityid(obj.getCityid());
		bean.setAddress(obj.getAddress());
		bean.setPhone(obj.getPhone());
		bean.setTags(obj.getTags());
		
		int res = DevelopersService.editInfo(bean);
		if(res>0){
			out.println(1);
		}else{
			out.println(0);
		}
        out.flush();  
        out.close();
	}
	
	
	/**
	 * 开发者首页，显示开发者信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/developers/index", method = RequestMethod.GET)
	public String devIndex(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request,response);
		
		request.setAttribute("navid", 1);//用于页面顶部导航
		request.setAttribute("pageTitle", "开发者信息");
		request.setAttribute("user", ub);
		
		return "developers_jsp/index";
	}

}
