package com.qkzz.friend.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.common.HttpUtil;
import com.qkzz.common.Tools;
import com.qkzz.common.TypeTrans;
import com.qkzz.friend.bean.FriendGroup;
import com.qkzz.friend.bean.FriendUser;
import com.qkzz.friend.bean.FriendUserInfo;
import com.qkzz.friend.bean.FriendUserListInfo;
import com.qkzz.friend.bean.FriendUserRetBean;
import com.qkzz.friend.bean.GameActiveLog;
import com.qkzz.friend.bean.SocialFriendListQueue;
import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.service.FriendGroupService;
import com.qkzz.friend.service.FriendUserService;
import com.qkzz.friend.service.GameActiveService;
import com.qkzz.friend.service.SocialFriendUserService;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.user.bean.Member;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.MemberService;
import com.qkzz.user.service.UserService;

public class FriendServlet extends HttpServlet {

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

		String format = HttpUtil.getString(request, "format","json");
		
		PrintWriter out = response.getWriter();
		String act = HttpUtil.getString(request, "act","");
		//create,read.delete,list
		if(act.equals("add")) {
			
			long uid = HttpUtil.getLong(request, "uid");
			long groupid = HttpUtil.getLong(request, "groupid");
			
			if(uid <= 0) {
				out.print("{\"result\":\"-1\",\"info\":\"null\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			
			String info = HttpUtil.getString(request, "info","");
			if(info.equals("")) {
				out.print("{\"result\":\"-2\",\"info\":\"null\"}");//参数不能为空
				out.flush();
				out.close();
				return;
			}

			long frienduid = 0;
			String friendname = "";
			if(TypeTrans.isNumber(info)) {
				//info按照uid来看
				frienduid = Long.parseLong(info);
				Member member = MemberService.getById(uid);
				if(member == null) {
					out.print("{\"result\":\"-3\",\"info\":\"null\"}");//好友不存在
					out.flush();
					out.close();
					return;
				}
				friendname = member.getName();
			} else {
				Member member = MemberService.getByName(info);
				if(member == null) {
					out.print("{\"result\":\"-4\",\"info\":\"null\"}");//好友不存在
					out.flush();
					out.close();
					return;
				}
				frienduid = member.getId();
				friendname = member.getName();
			}

			//判断是否已经添加
			if(FriendUserService.isFriendUser(uid, frienduid))  {
				out.print("{\"result\":\"-5\",\"info\":\"null\"}");//已经是好友
				out.flush();
				out.close();
				return;
			}
			
			//如果没有携带分组ID，直接使用默认分组ID
			if(groupid == 0) {
				//获取用户好友默认分组ID
				groupid = FriendGroupService.getDefaultGroupID(uid);
			} else {
				//判断该分组ID是否是添加者的分组
				if(!FriendGroupService.isMyFriendGroup(uid, groupid)) {
					out.print("{\"result\":\"-6\",\"info\":\"null\"}");
					out.flush();
					out.close();
					return;
				}
			}

//			FriendUser obj = new FriendUser();
//			obj.setUid(uid);
//			obj.setFuid(frienduid);
//			obj.setFname(friendname);
//			obj.setGroupid(groupid);
//			int status = FriendUserService.save(obj);
//			
//			if(status==1){
//				out.print("{\"result\":\"1000\"}");//字符串为空
//				out.flush();
//				out.close();
//				return;
//			}else{
//				out.print("{\"result\":\"-6\"}");//字符串为空
//				out.flush();
//				out.close();
//				return;
//			}
			
			FriendUser obj = new FriendUser();
			obj.setUid(uid);
			obj.setFuid(frienduid);
			obj.setFname(friendname);
			obj.setGroupid(groupid);
			int status = FriendUserService.save(obj);
			
			if(status==1){
				
				//添加成功后返回好友的相关信息
				FriendUserInfo fui = new FriendUserInfo();
				User user = UserService.getByUid(frienduid);

				if(user != null) {
					fui.setUid(frienduid);
					fui.setName(user.getName());
					fui.setSex(user.getSex());
					fui.setProvinceid(user.getProvinceid());
					fui.setCityid(user.getCityid());
					fui.setAreaid(user.getAreaid());
					fui.setBirth(user.getBirth());
					fui.setFaceurl(user.getFaceurl());
					fui.setType(user.getType());
					fui.setEmail(user.getEmail());
					fui.setVisitnum(user.getVisitnum());
					fui.setRegtime(user.getRegtime());
					fui.setLasttime(user.getLasttime());
					fui.setLastip(user.getLastip());
					fui.setMobile(user.getMobile());
					fui.setIslock(0);
					fui.setGroupid(groupid);
					fui.setIsonline(user.getIsonline());
					fui.setMood(user.getMood());
					fui.setGameid(user.getGameid());
					fui.setGamename(user.getGamename());

					FriendUserRetBean bean = new FriendUserRetBean();
					bean.setResult(1000);
					bean.setInfo(fui);

					JSONArray json = JSONArray.fromObject(bean);
					out.print(json.toString());
					out.flush();
					out.close();
					return;
				}
			}
			
			out.print("{\"result\":\"-7\",\"info\":\"null\"}");//字符串为空
			out.flush();
			out.close();

		} else if(act.equals("addfriend")) {
			//功能与act=add相同，只是返回值格式不同，新增了该接口
			
			long uid = HttpUtil.getLong(request, "uid");
			long groupid = HttpUtil.getLong(request, "groupid");
				
			if(uid <= 0) {
				out.print("[{\"result\":\"-1\"}]");//字符串为空
				out.flush();
				out.close();
				return;
			}

			String info = HttpUtil.getString(request, "info","");
			if(info.equals("")) {
				out.print("[{\"result\":\"-2\"}]");//参数不能为空
				out.flush();
				out.close();
				return;
			}

			long frienduid = 0;
			String friendname = "";
			if(TypeTrans.isNumber(info)) {
				//info按照uid来看
				frienduid = Long.parseLong(info);
				Member member = MemberService.getById(uid);
				if(member == null) {
					out.print("[{\"result\":\"-3\"}]");//好友不存在
					out.flush();
					out.close();
					return;
				}
				friendname = member.getName();
			} else {
				Member member = MemberService.getByName(info);
				if(member == null) {
					out.print("[{\"result\":\"-4\"}]");//好友不存在
					out.flush();
					out.close();
					return;
				}
				frienduid = member.getId();
				friendname = member.getName();
			}

			//判断是否已经添加
			if(FriendUserService.isFriendUser(uid, frienduid))  {
				out.print("[{\"result\":\"-5\"}]");//已经是好友
				out.flush();
				out.close();
				return;
			}

			//如果没有携带分组ID，直接使用默认分组ID
			if(groupid == 0) {
				//获取用户好友默认分组ID
				groupid = FriendGroupService.getDefaultGroupID(uid);
			} else {
				//判断该分组ID是否是添加者的分组
				if(!FriendGroupService.isMyFriendGroup(uid, groupid)) {
					out.print("[{\"result\":\"-6\"}]");
					out.flush();
					out.close();
					return;
				}
			}

			FriendUser obj = new FriendUser();
			obj.setUid(uid);
			obj.setFuid(frienduid);
			obj.setFname(friendname);
			obj.setGroupid(groupid);
			int status = FriendUserService.save(obj);

			if(status==1){

				//添加成功后返回好友的相关信息
				FriendUserInfo fui = new FriendUserInfo();
				User user = UserService.getByUid(frienduid);

				if(user != null) {
					fui.setUid(frienduid);
					fui.setName(user.getName());
					fui.setSex(user.getSex());
					fui.setProvinceid(user.getProvinceid());
					fui.setCityid(user.getCityid());
					fui.setAreaid(user.getAreaid());
					fui.setBirth(user.getBirth());
					fui.setFaceurl(user.getFaceurl());
					fui.setType(user.getType());
					fui.setEmail(user.getEmail());
					fui.setVisitnum(user.getVisitnum());
					fui.setRegtime(user.getRegtime());
					fui.setLasttime(user.getLasttime());
					fui.setLastip(user.getLastip());
					fui.setMobile(user.getMobile());
					fui.setIslock(0);
					fui.setGroupid(groupid);
					fui.setIsonline(user.getIsonline());
					fui.setMood(user.getMood());
					fui.setGameid(user.getGameid());
					fui.setGamename(user.getGamename());

					FriendUserRetBean bean = new FriendUserRetBean();
					bean.setResult(1000);
					bean.setInfo(fui);

					JSONArray json = JSONArray.fromObject(bean);
					out.print(json.toString());
					out.flush();
					out.close();
					return;
				}
			}

			out.print("[{\"result\":\"-7\"}]");//字符串为空
			out.flush();
			out.close();

		} else if(act.equals("addbyname")) {
			
			long uid = HttpUtil.getLong(request, "uid",0);
			long groupid = HttpUtil.getLong(request, "groupid");
			String friendname = HttpUtil.getString(request, "friendname","");
			
			System.out.println("===addByName:===uid:"+uid+"====name:"+friendname);
			
			if(uid<=0) {
				out.print("{\"result\":\"-1\",\"info\":\"null\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			if(friendname.equals("")) {
				out.print("{\"result\":\"-2\",\"info\":\"null\"}");//好友账户为空
				out.flush();
				out.close();
				return;
			}
			
			Member friend = MemberService.getByName(friendname);
			//检查接收用户是否存在
			if(friend == null){
				out.print("{\"result\":\"-3\",\"info\":\"null\"}");//好友不存在
				out.flush();
				out.close();
				return;
			}

			//判断是否已经添加
			if(FriendUserService.isFriendUser(uid, friend.getId())) {
				out.print("{\"result\":\"-5\",\"info\":\"null\"}");
				out.flush();
				out.close();
				return;
			}

			//如果没有携带分组ID，直接使用默认分组ID
			if(groupid == 0) {
				//获取用户好友默认分组ID
				groupid = FriendGroupService.getDefaultGroupID(uid);
			} else {
				//判断该分组ID是否是添加者的分组
				if(!FriendGroupService.isMyFriendGroup(uid, groupid)) {
					out.print("{\"result\":\"-6\",\"info\":\"null\"}");
					out.flush();
					out.close();
					return;
				}
			}
			
			FriendUser obj = new FriendUser();
			obj.setUid(uid);
			obj.setFuid(friend.getId());
			obj.setFname(friendname);
			obj.setGroupid(groupid);
			int status = FriendUserService.save(obj);
			
			if(status==1){
				
				//添加成功后返回好友的相关信息
				FriendUserInfo info = new FriendUserInfo();
				User user = UserService.getByUid(friend.getId());

				if(user != null) {
					info.setUid(friend.getId());
					info.setName(friend.getName());
					info.setSex(user.getSex());
					info.setProvinceid(user.getProvinceid());
					info.setCityid(user.getCityid());
					info.setAreaid(user.getAreaid());
					info.setBirth(user.getBirth());
					info.setFaceurl(user.getFaceurl());
					info.setType(user.getType());
					info.setEmail(user.getEmail());
					info.setVisitnum(user.getVisitnum());
					info.setRegtime(user.getRegtime());
					info.setLasttime(user.getLasttime());
					info.setLastip(user.getLastip());
					info.setMobile(friend.getMobile());
					info.setIslock(friend.getIslock());
					info.setGroupid(groupid);
					info.setIsonline(user.getIsonline());
					info.setMood(user.getMood());
					info.setGameid(user.getGameid());
					info.setGamename(user.getGamename());

					FriendUserRetBean bean = new FriendUserRetBean();
					bean.setResult(1000);
					bean.setInfo(info);

					JSONArray json = JSONArray.fromObject(bean);
					out.print(json.toString());
					out.flush();
					out.close();
					return;
				}
			}
			
			out.print("{\"result\":\"-4\",\"info\":\"null\"}");//字符串为空
			out.flush();
			out.close();

		}else if(act.equals("list")) {
			
			long uid = HttpUtil.getLong(request, "uid");
			int pn = HttpUtil.getInt(request, "page",1);
			int count = HttpUtil.getInt(request, "count",0);
			int startIndex = (pn-1)*count;

			List<FriendUserListInfo> list = FriendUserService.getFriendList(uid, startIndex, count);
			if(list == null || list.size() == 0) {
				out.print("{}");
				out.flush();
				out.close();
				return;
			}
			//return JSON
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(list);
				out.print(json.toString());
				out.flush();
				out.close();
			}

		}else if(act.equals("delete")) {
			
			long uid = HttpUtil.getLong(request, "uid");
			long frienduid = HttpUtil.getLong(request, "frienduid");
			int status = FriendUserService.delete(uid, frienduid);
			
			//return
			out.print("{\"result\":\""+status+"\"}");//字符串为空
			out.flush();
			out.close();
			return;
			
		}else if(act.equals("deletebyname")) {
			
			long uid = HttpUtil.getLong(request, "uid");
			String friendname = HttpUtil.getString(request, "friendname","");
			
			System.out.println("===delByName:===uid:"+uid+"====name:"+friendname);
			
			if(uid<=0) {
				out.print("{\"result\":\"-2\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			if(friendname.equals("")) {
				out.print("{\"result\":\"-3\"}");//好友账户为空
				out.flush();
				out.close();
				return;
			}
			long frienduid = MemberService.getIDByName(friendname);
			
			//检查接收用户是否存在
			if(frienduid == 0){
				out.print("{\"result\":\"-4\"}");//用户ID不存在
				out.flush();
				out.close();
				return;
			}

			//判断是否为好友
			if(!FriendUserService.isFriendUser(uid, frienduid)) {
				out.print("{\"result\":\"-5\"}");
				out.flush();
				out.close();
				return;
			}

			int status = FriendUserService.delete(uid, frienduid);
			
			//return
			out.print("{\"result\":\""+status+"\"}");//字符串为空
			out.flush();
			out.close();
			return;

		}else if(act.equals("changeremark")) {
			//修改好友姓名备注
			long uid = HttpUtil.getLong(request, "uid");
			long frienduid = HttpUtil.getLong(request, "frienduid");
			String remark = HttpUtil.getString(request, "remark","");
			
			if(uid<=0) {
				out.print("{\"result\":\"-2\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			if(remark.equals("")) {
				out.print("{\"result\":\"-3\"}");//好友账户为空
				out.flush();
				out.close();
				return;
			}

			//判断是否为好友
			if(!FriendUserService.isFriendUser(uid, frienduid)) {
				out.print("{\"result\":\"-4\"}");
				out.flush();
				out.close();
				return;
			}

			int status = FriendUserService.changeRemark(uid, frienduid, remark);
			
			//return
			out.print("{\"result\":\""+status+"\"}");//字符串为空
			out.flush();
			out.close();
			return;
			
		}else if(act.equals("addgroup")) {
			//添加好友分组
			
			long uid = HttpUtil.getLong(request, "uid");
			String groupname = HttpUtil.getString(request, "name","");
			
			System.out.println("===add group:===uid:"+uid+"====groupname:"+groupname);
			
			if(uid == 0 || groupname.equals("")) {
				out.print("{\"result\":\"-1\",\"groupid\":\"\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}

//			if(FriendGroupService.isGroupNameExist(uid, groupname)) {
//				out.print("{\"result\":\"-2\",\"groupid\":\"\"}");//分组名称存在
//				out.flush();
//				out.close();
//				return;
//			}
			
			if(FriendGroupService.getTotalGroupNum(uid) >= 15) {
				out.print("{\"result\":\"-2\",\"groupid\":\"\"}");//分组数量不能超过15
				out.flush();
				out.close();
				return;
			}

			int status = FriendGroupService.addGroup(uid, groupname);

			if(status != -1) {
				//return
				//获取分组ID
				long groupid = FriendGroupService.getLastInsertGroupID(uid);
				
				out.print("{\"result\":\"0\",\"groupid\":\""+groupid+"\"}");
				out.flush();
				out.close();
				return;
			}

			out.print("{\"result\":\"-3\",\"groupid\":\"\"}");//字符串为空
			out.flush();
			out.close();
		
		}else if(act.equals("delgroup")) {
			//删除好友分组，将分组内的好友转移到默认分组
			
			long uid = HttpUtil.getLong(request, "uid");
			long groupid = HttpUtil.getLong(request, "groupid");
			
			System.out.println("===del group:===uid:"+uid+"====groupid:"+groupid);
			
			if(uid == 0 || groupid == 0) {
				out.print("{\"result\":\"-1\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}

			//判断分组是否存在
			FriendGroup fg = FriendGroupService.getGroup(uid, groupid);
			if(fg == null) {
				out.print("{\"result\":\"-2\"}");//分组不存在
				out.flush();
				out.close();
				return;
			}

			//判断该分组ID是否是添加者的分组
			if(!FriendGroupService.isMyFriendGroup(uid, groupid)) {
				out.print("{\"result\":\"-5\"}");
				out.flush();
				out.close();
				return;
			}

			
			if(fg.getIsdefault() == 1) {
				out.print("{\"result\":\"-4\"}");//默认分组不能删除
				out.flush();
				out.close();
				return;
			}
			
			//将好友转移到默认分组
			FriendUserService.changeGroupToDefault(uid, groupid);

			//删除分组
			int status = FriendGroupService.delGroup(uid, groupid);

			if(status != -1) {
				out.print("{\"result\":\"0\"}");
				out.flush();
				out.close();
				return;
			}

			out.print("{\"result\":\"-3\"}");//字符串为空
			out.flush();
			out.close();

		}else if(act.equals("editgroup")) {
			//编辑好友分组
			
			long uid = HttpUtil.getLong(request, "uid");
			long groupid = HttpUtil.getLong(request, "groupid");
			String newGroupName = HttpUtil.getString(request, "name");
			
			System.out.println("===edit group:===uid:"+uid+"====groupid:"+groupid+"====newname:"+newGroupName);
			
			if(uid == 0 || groupid == 0 || newGroupName.equals("")) {
				out.print("{\"result\":\"-1\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}

			//判断分组是否存在
			FriendGroup fg = FriendGroupService.getGroup(uid, groupid);
			if(fg == null) {
				out.print("{\"result\":\"-2\"}");//分组不存在
				out.flush();
				out.close();
				return;
			}

			//判断该分组ID是否是添加者的分组
			if(!FriendGroupService.isMyFriendGroup(uid, groupid)) {
				out.print("{\"result\":\"-4\"}");
				out.flush();
				out.close();
				return;
			}
			
			//编辑分组
			int status = FriendGroupService.editGroup(uid, groupid, newGroupName);

			if(status != -1) {
				out.print("{\"result\":\"0\"}");
				out.flush();
				out.close();
				return;
			}

			out.print("{\"result\":\"-3\"}");//字符串为空
			out.flush();
			out.close();
		
		}else if(act.equals("grouplist")) {
			//获取好友分组列表
			
			long uid = HttpUtil.getLong(request, "uid");

			List<FriendGroup> list = FriendGroupService.getGroupList(uid);
			if(list == null || list.size() == 0) {
				out.print("{}");
				out.flush();
				out.close();
				return;
			}
			//return JSON
			JSONArray json = JSONArray.fromObject(list);
			out.print(json.toString());
			out.flush();
			out.close();
		
		}else if(act.equals("changegroup")) {
			//转换好友分组
			
			long uid = HttpUtil.getLong(request, "uid");
			long friendUid = HttpUtil.getLong(request, "frienduid");
			long groupid = HttpUtil.getLong(request, "groupid");
			
			System.out.println("===change group:===uid:"+uid+"====groupid:"+groupid+"===friendUid:"+friendUid);
			
			if(uid == 0 || groupid <= 0 || friendUid <= 0) {
				out.print("{\"result\":\"-1\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}

			//判断是否为好友
			if(!FriendUserService.isFriendUser(uid, friendUid)) {
				out.print("{\"result\":\"-2\"}");//目标分组不存在
				out.flush();
				out.close();
				return;
			}
			
			//判断分组是否存在
			FriendGroup fg = FriendGroupService.getGroup(uid, groupid);
			if(fg == null) {
				out.print("{\"result\":\"-3\"}");//目标分组不存在
				out.flush();
				out.close();
				return;
			}

			//判断该分组ID是否是添加者的分组
			if(!FriendGroupService.isMyFriendGroup(uid, groupid)) {
				out.print("{\"result\":\"-5\"}");
				out.flush();
				out.close();
				return;
			}
			
			//转换分组
			int status = FriendUserService.changeGroup(uid, friendUid, groupid);

			if(status != -1) {
				out.print("{\"result\":\"0\"}");
				out.flush();
				out.close();
				return;
			}

			out.print("{\"result\":\"-4\"}");//字符串为空
			out.flush();
			out.close();
			
		}else if(act.equals("activegame")) {
			//从游戏中激活用户在社交网站的账户，并在吱吱中注册新用户对应上
			
			String socialuid = HttpUtil.getString(request, "suid","");
			String socialname = HttpUtil.getString(request, "sname","");
			String domain = HttpUtil.getString(request, "domain","");
			String gamecode = HttpUtil.getString(request, "gameid");
			
			System.out.println("===social user active:===suid:"+socialuid+"====domain:"+domain);
			
			if(socialuid.equals("") || domain.equals("") || gamecode.equals("")) {
				out.print("{\"result\":\"-1\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}

			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-2\"}");//gameid不存在
				out.flush();
				out.close();
				return;
			}

			
			//判断是否已经激活游戏
			if(GameActiveService.isActiveGame(socialuid, domain,gameid)) {
				out.print("{\"result\":\"-3\"}");//已经激活
				out.flush();
				out.close();
				return;
			}
			

			//添加激活记录
			GameActiveLog log = new GameActiveLog();
			log.setSuid(socialuid);
			log.setSname(socialname);
			log.setDomain(domain);
			log.setGameid(gameid);
			log.setUid(0);//默认未生成
			int res = GameActiveService.add(log);
			

			if(res != -1) {
				
				//判断该用户是否已经加入吱吱用户列表
				Member member= MemberService.getDoaminUser(socialuid, domain);
				if(member != null) {
					out.print("{\"result\":\"-4\"}");//已经加入吱吱列表
					out.flush();
					out.close();
					return;
				}
				
				//添加用户到吱吱用户表中
				String name = socialname+"@"+domain;
				int addres = MemberService.addDomainUser(name, socialuid, domain);
				if(addres != -1) {
					long uid = MemberService.getIDByName(name);
					if(uid != 0) {
						User user = new User();
						user.setUid(uid);
						user.setLastip(Tools.getRemoteAddr(request));
						user.setEmail("");
						user.setNickname(socialname);
						UserService.addUser(user);
						
						//更新激活记录中的uid字段
						GameActiveService.updateZhiZhiUid(uid, socialuid, domain);
						
						out.print("{\"result\":\"0\",\"uid\":\""+uid+"\"}");//用户注册成功
						out.flush();
						out.close();
						return;
					}
				}
			}

			out.print("{\"result\":\"-5\"}");//激活失败
			out.flush();
			out.close();
		}else if(act.equals("sociallist")) {
			//获取用户的社交好友列表，社交好友列表=吱吱好友+社交网站好友
			
//			long uid = HttpUtil.getLong(request, "uid");
			String socialuid = HttpUtil.getString(request, "suid","");
			String domain = HttpUtil.getString(request, "domain","");
			String gamecode = HttpUtil.getString(request, "gameid");
			
			if(domain.equals("") || gamecode.equals("")) {
				out.print("{\"result\":\"-1\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}
			
			//通过社交信息获取用户在吱吱的用户ID，前提是用户已经通过激活接口激活并成功加入吱吱
			Member member= MemberService.getDoaminUser(socialuid, domain);
			if(member == null) {
				out.print("{\"result\":\"-2\"}");//该用户尚未激活，无法调用社交好友列表
				out.flush();
				out.close();
				return;
			}
			
			long uid = member.getId();
			
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-3\"}");//gameid不存在
				out.flush();
				out.close();
				return;
			}
			
			List<SocialFriendUserListInfo> ret = new ArrayList<SocialFriendUserListInfo>();

			//获取社交好友列表
			List<SocialFriendUserListInfo> socialList = SocialFriendUserService.getList(uid, domain);
			if(socialList != null && socialList.size() > 0) {
				ret.addAll(socialList);
			}
			
			//获取吱吱好友列表
			List<SocialFriendUserListInfo> list = FriendUserService.getSocialFriendList(uid, gameid);
			if(list != null && list.size() > 0) {
				ret.addAll(list);
			}
			
			//return JSON
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(ret);
				out.print(json.toString());
				out.flush();
				out.close();
			}

		}else if(act.equals("socialfriendqueue")) {
			//社交好友处理队列接口，每次游戏将用户好友列表传递到本地接口，放入队列中等待处理，判断哪些已经激活，哪些需要处理等等
			
//			long uid = HttpUtil.getLong(request, "uid");//吱吱用户ID
			String socialuid = HttpUtil.getString(request, "suid","");//社交网站uid
			String domain = HttpUtil.getString(request, "domain","");//社交网站域名
			String gamecode = HttpUtil.getString(request, "gameid");//游戏ID
			String friendlist = HttpUtil.getString(request, "frdlist","");//社交网站好友列表
			
			if(socialuid.equals("") || domain.equals("") || gamecode.equals("") || friendlist.equals("")) {
				out.print("{\"result\":\"-1\"}");//参数传递错误
				out.flush();
				out.close();
				return;
			}

			//通过社交信息获取用户在吱吱的用户ID，前提是用户已经通过激活接口激活并成功加入吱吱
			Member member= MemberService.getDoaminUser(socialuid, domain);
			if(member == null) {
				out.print("{\"result\":\"-2\"}");//该用户尚未激活，无法调用该接口
				out.flush();
				out.close();
				return;
			}

			long uid = member.getId();

			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-3\"}");//gameid不存在
				out.flush();
				out.close();
				return;
			}
			
			SocialFriendListQueue bean = new SocialFriendListQueue();
			bean.setUid(uid);
			bean.setSuid(socialuid);
			bean.setDomain(domain);
			bean.setGameid(gameid);
			bean.setFrdlist(friendlist);
			int res = SocialFriendUserService.addSocialFriendListQueue(bean);

			//return JSON
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//成功添加到处理队列
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-4\"}");//添加失败
			out.flush();
			out.close();

		} else {
			//其他，报错
			out.print("{}");
			out.flush();
			out.close();
		}	

	}
}
