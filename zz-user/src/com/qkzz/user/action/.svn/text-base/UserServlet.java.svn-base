package com.qkzz.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.chat.bean.ChatContentLog;
import com.qkzz.chat.service.ChatContentLogService;
import com.qkzz.common.HttpUtil;
import com.qkzz.common.LRUMap;
import com.qkzz.common.MD5;
import com.qkzz.common.Tools;
import com.qkzz.common.TypeTrans;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.log.PVLog;
import com.qkzz.online.bean.OnlineQueue;
import com.qkzz.online.service.OnlineQueueService;
import com.qkzz.online.service.OnlineService;
import com.qkzz.user.bean.Icon;
import com.qkzz.user.bean.Member;
import com.qkzz.user.bean.PlayLog;
import com.qkzz.user.bean.ReportList;
import com.qkzz.user.bean.User;
import com.qkzz.user.bean.UserMood;
import com.qkzz.user.service.GuestUserService;
import com.qkzz.user.service.IconService;
import com.qkzz.user.service.MemberService;
import com.qkzz.user.service.PlayLogService;
import com.qkzz.user.service.ReportService;
import com.qkzz.user.service.UserMoodService;
import com.qkzz.user.service.UserService;
import com.qkzz.user.service.bo.MoodListBean;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757860734741145731L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		String act = HttpUtil.getString(request, "act","");
		String ip = HttpUtil.getString(request, "ip");
		String format = HttpUtil.getString(request, "format","json");
		
		if(act.equals("register")) {
			//注册
			String name = HttpUtil.getString(request, "name");
			String password = HttpUtil.getString(request, "password");
			String email = HttpUtil.getString(request, "email");
			
			if(name.equals("") || name.trim().equals("") || password.equals("") || password.trim().equals("")|| email.equals("") || email.trim().equals("")) {
				out.print("{\"result\":\"-1\"}");//字符串为空(用户名，密码，email均不能为空)
				out.flush();
				out.close();
				return;
			}
			
			if(TypeTrans.isNumber(name)) {
				out.print("{\"result\":\"-4\"}");//用户名不能是纯数字
				out.flush();
				out.close();
				return;
			}
			
			if(password.length()<6){
				out.print("{\"result\":\"-5\"}");//密码不能小于6位
				out.flush();
				out.close();
				return;
			}
			
			if(!email.matches("^\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$")){
				out.print("{\"result\":\"-6\"}");//邮箱格式不对
				out.flush();
				out.close();
				return;
			}
			
			if(MemberService.isValidateName(name)) {
				out.print("{\"result\":\"-2\"}");//用户名已存在
				out.flush();
				out.close();
				return;
			}
			
			//开始注册流程
			Member member = new Member();
			member.setName(name);
			member.setPassword(password);
			int res = UserService.addMember(member);
			if(res != -1) {
				Member m = MemberService.getByLogin(name, password);
				if(m != null) {
					User user = new User();
					user.setUid(m.getId());
					user.setLastip(ip);
					user.setEmail(email);
					user.setNickname(name);
					UserService.addUser(user);
					out.print("{\"result\":\"0\"}");//用户注册成功
					out.flush();
					out.close();
					return;
				}
			}

			out.print("{\"result\":\"-3\"}");//用户注册失败
			out.flush();
			out.close();
			
		} else if(act.equals("adddomainuser")) {
			//注册
			String name = HttpUtil.getString(request, "name");
			String domainUID = HttpUtil.getString(request, "uid");
			String domain = HttpUtil.getString(request, "domain");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);//如果通过网页登陆，有可能不会传递gameid，默认为0
			String url = HttpUtil.getString(request, "url");//用户当前所在url

			if(name.equals("") || name.trim().equals("") || domainUID.equals("") || domain.equals("")) {
				out.print("{\"result\":\"-1\",\"uid\":\"\",\"name\":\"\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
				
			if(TypeTrans.isNumber(name)) {
				out.print("{\"result\":\"-2\",\"uid\":\"\",\"name\":\"\"}");//用户名不能是纯数字
				out.flush();
				out.close();
				return;
			}
				
			if(MemberService.isValidateName(name)) {
				out.print("{\"result\":\"-3\",\"uid\":\"\",\"name\":\"\"}");//用户名存在
				out.flush();
				out.close();
				return;
			}

			//开始注册流程
			Member member = new Member();
			member.setName(name);
			member.setPassword("");
			int res = MemberService.addDomainUser(name, domainUID, domain);
			if(res != -1) {
				Member m = MemberService.getByName(name);
				if(m != null) {
					User user = new User();
					user.setUid(m.getId());
					user.setLastip(ip);
					user.setEmail("");
					user.setNickname(name);
					UserService.addUser(user);

					//添加到在线列表中
					//生成本次签名 ： md5(username+uid+当前时间)
					String key = new StringBuffer(name).append(m.getId()).append(System.currentTimeMillis()).toString();
					String sign = MD5.encoderForString(key);
					
					//加入在线
					OnlineQueue queue = new OnlineQueue();
					queue.setGameid(gameid);
					queue.setUid(m.getId());
					queue.setName(name);
					queue.setLasttime(System.currentTimeMillis());
					queue.setLasturl(url);
					queue.setStatus(1);//全局在线
					queue.setSign(sign);
					OnlineQueueService.addToQueue(queue);
					
					out.print("{\"result\":\"0\",\"uid\":\""+m.getId()+"\",\"name\":\""+m.getName()+"\"}");//用户添加成功
					out.flush();
					out.close();
					return;
				}
			} 
			
			out.print("{\"result\":\"-4\",\"uid\":\"\",\"name\":\"\"}");//用户添加失败
			out.flush();
			out.close();
		
		} else if(act.equals("login")) {
			//登陆
			String name = HttpUtil.getString(request, "name");
			String password = HttpUtil.getString(request, "password");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);//如果通过网页登陆，有可能不会传递gameid，默认为0
			String url = HttpUtil.getString(request, "url");//用户当前所在url
			
			//验证用户登录
			User user = UserService.getByLogin(name, password);
			if(user == null) {
				if(MemberService.isValidateName(name)){
					out.print("{\"result\":\"-2\"}");//密码错误
					out.flush();
					out.close();
					return;
				}else{
					out.print("{\"result\":\"-3\"}");//用户名不存在
					out.flush();
					out.close();
					return;
				}
			}

			//生成本次签名 ： md5(username+uid+当前时间)
			String key = new StringBuffer(name).append(user.getUid()).append(System.currentTimeMillis()).toString();
			String sign = MD5.encoderForString(key);
			
			//加入在线
			OnlineQueue queue = new OnlineQueue();
			queue.setGameid(gameid);
			queue.setUid(user.getUid());
			queue.setName(user.getName());
			queue.setLasttime(System.currentTimeMillis());
			queue.setLasturl(url);
			queue.setStatus(1);//全局在线
			queue.setSign(sign);
			OnlineQueueService.addToQueue(queue);
			
			//记录用户曾经玩过的游戏记录
			PlayLog log = new PlayLog();
			log.setUid(user.getUid());
			log.setGameid(gameid);
			log.setUrl(url);
			PlayLogService.add(log);
			
			out.print("{\"result\":\"0\",\"sign\":\""+sign+"\",\"uid\":\""+user.getUid()+"\"}");//用户登陆成功，返回签名
			out.flush();
			out.close();
			
		} else if(act.equals("logout")) {
			//退出登陆
			long uid = HttpUtil.getLong(request, "uid");
			String sign = HttpUtil.getString(request, "sign");
			
			if(uid == 0 || sign.equals("")) {
				out.print("{\"status\":\"-1\"}");
				out.flush();
				out.close();
				return;
			}

			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

			if(!OnlineService.isGlobalOnlne(uid, sign)) {
				out.print("{\"status\":\"-3\"}");
				out.flush();
				out.close();
				return;
			}
			
			OnlineService.clearOnline(uid);
			
			out.print("{\"status\":\"0\"}");//用户退出登陆成功
			out.flush();
			out.close();

		
		} else if(act.equals("info")) {
			//获取信息
			long uid = HttpUtil.getLong(request, "uid",0);
			User user = UserService.getByUid(uid);
			if(user == null) {
				out.print("{}");//用户不存在，信息为空
				out.flush();
				out.close();
				return;
			}
			JSONArray json = JSONArray.fromObject(user);
			out.print(json.toString());
			out.flush();
			out.close();
			
		} else if(act.equals("getidbyname")) {
			//通过用户账号获取id
			String name = HttpUtil.getString(request, "name","");
			if(name.equals("")) {
				out.print("{\"uid\":\"-1\"}");//用户不存在，信息为空
				out.flush();
				out.close();
				return;
			}

			long uid = MemberService.getIDByName(name);
			out.print("{\"uid\":\""+uid+"\"}");
			out.flush();
			out.close();
			
		} else if(act.equals("isuser")) {
			//通过用户账号获取id
			String info = HttpUtil.getString(request, "info","");
			if(info.equals("")) {
				out.print("{\"uid\":\"-1\",\"name\":\"\"}");//参数不能为空
				out.flush();
				out.close();
				return;
			}

			if(TypeTrans.isNumber(info)) {
				//info按照uid来看
				long uid = Long.parseLong(info);
				Member member = MemberService.getById(uid);
				if(member == null) {
					out.print("{\"uid\":\"0\",\"name\":\"\"}");//用户不存在，信息为空
					out.flush();
					out.close();
					return;
				}
				out.print("{\"uid\":\""+uid+"\",\"name\":\""+member.getName()+"\"}");
			} else {
				Member member = MemberService.getByName(info);
				if(member == null) {
					out.print("{\"uid\":\"0\",\"name\":\"\"}");//用户不存在，信息为空
					out.flush();
					out.close();
					return;
				}
				out.print("{\"uid\":\""+member.getId()+"\",\"name\":\""+member.getName()+"\"}");
			}
			out.flush();
			out.close();

		} else if(act.equals("iszzuser")) {
			//判断外站用户是否为zz用户，用于外站登陆
			String domainUID = HttpUtil.getString(request, "uid");
			String domain = HttpUtil.getString(request, "domain");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(domainUID.equals("") || domain.equals("")) {
				out.print("{\"status\":\"-1\",\"uid\":\"\",\"name\":\"\"}");//参数不能为空
				out.flush();
				out.close();
				return;
			}

			Member member = MemberService.getDoaminUser(domainUID, domain);
			if(member == null) {
				out.print("{\"status\":\"-2\",\"uid\":\"\",\"name\":\"\"}");//用户不存在，信息为空
				out.flush();
				out.close();
				return;
			}

			//直接设为在线状态
			//生成本次签名 ： md5(username+uid+当前时间)
			String key = new StringBuffer(member.getName()).append(member.getId()).append(System.currentTimeMillis()).toString();
			String sign = MD5.encoderForString(key);
			
			//加入在线
			OnlineQueue queue = new OnlineQueue();
			queue.setGameid(gameid);
			queue.setUid(member.getId());
			queue.setName(member.getName());
			queue.setLasttime(System.currentTimeMillis());
			queue.setLasturl("");
			queue.setStatus(1);//全局在线
			queue.setSign(sign);
			OnlineQueueService.addToQueue(queue);
			
			out.print("{\"status\":\"0\",\"uid\":\""+member.getId()+"\",\"name\":\""+member.getName()+"\"}");

			out.flush();
			out.close();
			
		} else if(act.equals("hassetpwd")) {
			//用户是否已经设置了密码，主要针对外站用户
			//开始注册的时候只有用户名，没有设置密码
			
			long uid = HttpUtil.getLong(request, "uid",0);
			String sign = HttpUtil.getString(request, "sign");
			
			if(uid == 0 || sign.equals("")) {
				out.print("{\"status\":\"-1\"}");
				out.flush();
				out.close();
				return;
			}

			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

			if(!OnlineService.isGlobalOnlne(uid, sign)) {
				out.print("{\"status\":\"-3\"}");
				out.flush();
				out.close();
				return;
			}
			
			if(MemberService.hasSetPassword(uid)) {
				out.print("{\"status\":\"0\"}");
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"status\":\"1\"}");
			out.flush();
			out.close();
			
		} else if(act.equals("update")) {
			
			long uid = HttpUtil.getLong(request, "uid",0);
			String sign = HttpUtil.getString(request, "sign");
			int sex = HttpUtil.getInt(request, "sex",-1);
			String email = HttpUtil.getString(request, "email");
			String mobile = HttpUtil.getString(request, "mobile");
			String mood = HttpUtil.getString(request, "mood");
			String birth = HttpUtil.getString(request, "birth");
			String phone = HttpUtil.getString(request, "phone","");
			String nickname = HttpUtil.getString(request, "nickname");
			
//			if(uid == 0 || sign.equals("")) {
//				out.print("{\"status\":\"-1\"}");
//				out.flush();
//				out.close();
//				return;
//			}
			
			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

//			if(!OnlineService.isGlobalOnlne(uid, sign)) {
//				out.print("{\"status\":\"-3\"}");
//				out.flush();
//				out.close();
//				return;
//			}
			if(mobile!=null && !"".equals(mobile)){
				Member mem = new Member();
				mem.setId(uid);
				mem.setMobile(mobile);
				UserService.updateMember(mem);
			}
			
			if(mood!=null && !"".equals(mood)){
				UserService.updateUserMood(uid, mood);
			}
			
			User user = new User();
			user.setEmail(email);
			user.setSex(sex);
			user.setUid(uid);
			user.setBirth(birth);
			user.setPhone(phone);
			user.setNickname(nickname);
			UserService.updateUserInfo(user);
			
			out.print("{\"status\":\"1\"}");
			out.flush();
			out.close();
			
		} else if(act.equals("moodupdate")) {
			
			long uid = HttpUtil.getLong(request, "uid",0);
			String sign = HttpUtil.getString(request, "sign");
			String mood = HttpUtil.getString(request, "mood");
			
//			if(uid == 0 || sign.equals("")) {
//				out.print("{\"status\":\"-1\"}");
//				out.flush();
//				out.close();
//				return;
//			}
			
			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

//			if(!OnlineService.isGlobalOnlne(uid, sign)) {
//				out.print("{\"status\":\"-3\"}");
//				out.flush();
//				out.close();
//				return;
//			}
			
			if(UserService.updateUserMood(uid, mood)==-1) {
				out.print("{\"status\":\"0\"}");
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"status\":\"1\"}");
			out.flush();
			out.close();
			
		} else if(act.equals("moodlist")) {
			long uid = HttpUtil.getLong(request, "uid",0);
			String sign = HttpUtil.getString(request, "sign");
			int startIndex = HttpUtil.getInt(request, "start",0);
			int count = HttpUtil.getInt(request, "count",0);
			
//			if(uid == 0 || sign.equals("")) {
//				out.print("{\"status\":\"-1\"}");
//				out.flush();
//				out.close();
//				return;
//			}

			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

//			if(!OnlineService.isGlobalOnlne(uid, sign)) {
//				out.print("{\"status\":\"-3\"}");
//				out.flush();
//				out.close();
//				return;
//			}
			
			List<UserMood> list = UserMoodService.getByList(uid, startIndex, count);
			if(list!=null){
				int total = UserMoodService.countByList(uid);
				
				MoodListBean bean = new MoodListBean();
				bean.setStatus(1);
				bean.setStart(startIndex);
				bean.setCount(count);
				bean.setTotal(total);
				bean.setList(list);
				
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
				out.flush();
				out.close();
			}else{
				out.print("{\"status\":\"0\"}");
				out.flush();
				out.close();
			}
		} else if(act.equals("reportuser")) {
			//举报用户
			long reportuid = HttpUtil.getLong(request, "reportuid",0);//举报人ID
			long uid = HttpUtil.getLong(request, "uid",0);//被举报人ID
			String sign = HttpUtil.getString(request, "sign");
			String reason = HttpUtil.getString(request, "reason");
			String reportip = Tools.getRemoteAddr(request);
			
			
			if(reportuid == 0 || uid == 0) {
				out.print("{\"status\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			if(MemberService.getById(reportuid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-3\"}");
				out.flush();
				out.close();
				return;
			}
			
			if(!ReportService.canAddReport(reportuid, uid, reportip)) {
				out.print("{\"status\":\"-4\"}");//10分钟内同一个IP只能举报一次
				out.flush();
				out.close();
				return;
			}

			ReportList bean = new ReportList();
			bean.setReportuid(reportuid);
			bean.setUid(uid);
			bean.setIp(reportip);
			bean.setReason(reason);
			
			//查找聊天中被举报人的IP
			String blockip = ""; 
			ChatContentLog log = ChatContentLogService.getLatestLog(uid);
			if(log != null) {
				blockip = log.getIp();
			}
			
			System.out.println("bip:"+blockip+"====uid:"+uid);
			if(!blockip.equals("")) {
				bean.setIp(blockip);
			} else {
				bean.setIp("");
			}
			int ret = ReportService.addReport(bean);
			
			if(ret != -1) {
				out.print("{\"status\":\"0\"}");//举报成功
				out.flush();
				out.close();
				return;
			}

			out.print("{\"status\":\"-5\"}");//举报操作失败
			out.flush();
			out.close();
		}else if(act.equals("icon")) {
			List<Icon> list = new ArrayList<Icon>();
			LRUMap<Integer, Icon> iconMap = IconService.getByMap();
			for (Icon obj: iconMap.values()) {
				list.add(obj);
			}
			//return JSON
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(list);
				out.print(json.toString());
			}
		}else if(act.equals("updateface")) {
			long uid = HttpUtil.getLong(request, "uid",0);
			String sign = HttpUtil.getString(request, "sign");
			int iconid = HttpUtil.getInt(request, "iconid",0);
			
			if(uid == 0) {
				out.print("{\"status\":\"-1\"}");
				out.flush();
				out.close();
				return;
			}

			if(MemberService.getById(uid) == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

//			if(!OnlineService.isGlobalOnlne(uid, sign)) {
//				out.print("{\"status\":\"-3\"}");
//				out.flush();
//				out.close();
//				return;
//			}
			
			String url = IconService.getByUrl(iconid);
			
			System.out.println(uid+","+url);
			
			int status = UserService.updateFace(uid, url);
			if(status!=-1){
				out.print("{\"status\":\"1\"}");
				out.flush();
				out.close();
			}else{
				out.print("{\"status\":\"0\"}");
				out.flush();
				out.close();
			}
			
		}else if(act.equals("checkin")) {
			//用户打开游戏页面时签到，并记录访问日志，该日志也就是游戏页面的访问次数PV
			PVLog.pvLog(request);
			out.print("{\"status\":\"0\"}");
			out.flush();
			out.close();
			
		}else if(act.equals("getguestid")) {
			//为游客申请随机ID
			//需要传递游客昵称，gameid，当前所在url

			String name = HttpUtil.getString(request, "nickname","");
			if(name.equals("")) {
				name = "游客";
			}
			
			
			//生成本次签名 ： md5(username+随机值+当前时间)
			String key = new StringBuffer(name).append(com.qkzz.user.service.GuestUserService.Rand(90000000l,999999999)).append(System.currentTimeMillis()).toString();
			String sign = MD5.encoderForString(key);

			User user = GuestUserService.createUser(name, request,sign);
			
			if(user == null) {
				out.print("{\"result\":\"-1\"}");
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"0\",\"info\":[{\"guestid\":\""+user.getUid()+"\",\"nickname\":\""+name+"\",\"sign\":\""+sign+"\"}]}");
			out.flush();
			out.close();

		} else {
			//其他，报错
			out.print("{}");//用户不存在，信息为空
			out.flush();
			out.close();
		}
	}
}
