package com.qkzz.chat.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.chat.bean.ChannelRetBean;
import com.qkzz.chat.bean.ChatContentLog;
import com.qkzz.chat.bean.ChatTeam;
import com.qkzz.chat.bean.ChatTeamMember;
import com.qkzz.chat.bean.ChatTeamRetBean;
import com.qkzz.chat.bean.ChatTeamTempMember;
import com.qkzz.chat.bean.ChatUser;
import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.GameChannelRetBean;
import com.qkzz.chat.bean.PrivateContent;
import com.qkzz.chat.bean.RetBean;
import com.qkzz.chat.bean.RetListBean;
import com.qkzz.chat.bean.RetTeamListBean;
import com.qkzz.chat.bean.TeamChatRetBean;
import com.qkzz.chat.bean.TeamContent;
import com.qkzz.chat.service.ChatContentLogService;
import com.qkzz.chat.service.ChatService;
import com.qkzz.chat.service.ChatTeamService;
import com.qkzz.chat.service.TeamChatService;
import com.qkzz.common.HttpUtil;
import com.qkzz.common.Tools;
import com.qkzz.common.WordsFilter;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.BlackService;
import com.qkzz.user.service.ShortUrlService;
import com.qkzz.user.service.UserService;

public class ChatServlet extends HttpServlet {

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

		int freshTime = 5;//seconds,need to config
		
		String act = HttpUtil.getString(request, "act","");
		
		if(act.equals("sendchat")) {
			int channelid = HttpUtil.getInt(request, "channelid",0);
			long fromuid = HttpUtil.getLong(request, "fromuid",0);
			long destuid = HttpUtil.getLong(request, "destuid",0);
			String chatContent = HttpUtil.getString(request, "content","");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int stat = 0;
			
			if(BlackService.isBlackUser(Tools.getRemoteAddr(request))) {
				out.print("{\"result\":\"-2\"}");//已经被禁言，不能发表
				out.flush();
				out.close();
				return;
			}
			
			String fromname = "";
			String destname = "";
			
			ChatUser from = ChatService.getUser(fromuid);
			if(from != null) {
				fromname = from.getNickname();
			}
			
			ChatUser dest = ChatService.getUser(destuid);
			if(dest != null) {
				destname = dest.getNickname();
			}
			
			System.out.println("content:"+ java.net.URLDecoder.decode(chatContent,"utf-8"));
			
			if(!WordsFilter.isAllowInput(chatContent)) {
				stat = -1;
				chatContent = WordsFilter.filterInput(chatContent);
			}
			
			Content content = new Content();
			content.setChannelid(channelid);
			content.setFromuid(fromuid);
			content.setFromname(fromname);
			content.setDestuid(destuid);
			content.setDestname(destname);
			content.setContent(ShortUrlService.getShortContent(chatContent));
			content.setGameid(gameid);
			content.setTktype(0);
			content.setAttime(System.currentTimeMillis());
			ChatService.addWorldChatContent(content);
			
			//添加聊天log
			ChatContentLog log = new ChatContentLog();
			log.setChannelid(channelid);
			log.setFromuid(fromuid);
			log.setFromname(fromname);
			log.setDestuid(destuid);
			log.setDestname(destname);
			log.setContent(ShortUrlService.getShortContent(chatContent));
			log.setGameid(gameid);
			log.setAttime(System.currentTimeMillis());
			log.setIp(Tools.getRemoteAddr(request));
			ChatContentLogService.addLog(log);
			
			
			out.print("{\"result\":\""+stat+"\"}");//发言成功
			out.flush();
			out.close();

		} else if(act.equals("sendprivatechat")) {
			int channelid = HttpUtil.getInt(request, "channelid",0);
			long fromuid = HttpUtil.getLong(request, "fromuid",0);
			long destuid = HttpUtil.getLong(request, "destuid",0);
			String chatContent = HttpUtil.getString(request, "content","");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int stat = 0;
			
//			if(BlackService.isBlackUser(fromuid)) {
//				out.print("{\"result\":\"-2\"}");//已经被禁言，不能发表
//				out.flush();
//				out.close();
//				return;
//			}
			
			String fromname = "";
			String destname = "";

			ChatUser from = ChatService.getUser(fromuid);
			if(from != null) {
				fromname = from.getNickname();
			}

			ChatUser dest = ChatService.getUser(destuid);
			if(dest != null) {
				destname = dest.getNickname();
			}

			if(!WordsFilter.isAllowInput(chatContent)) {
				stat = -1;
				chatContent = WordsFilter.filterInput(chatContent);
			}

			
			PrivateContent content = new PrivateContent();
			content.setFromuid(fromuid);
			content.setFromname(fromname);
			content.setDestuid(destuid);
			content.setDestname(destname);
			content.setChannelid(channelid);
			content.setContent(ShortUrlService.getShortContent(chatContent));
			content.setTktype(0);
			content.setAttime(System.currentTimeMillis());
			content.setGameid(gameid);
			ChatService.addPrivateChatContent(content);

			out.print("{\"result\":\""+stat+"\"}");//发言成功
			out.flush();
			out.close();

		} else if(act.equals("sendgamechat")) {
			//当前游戏聊天
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int stat = 0;
//			if(gameid == 0) {
//				out.print("{\"result\":\"-1\"}");//参数丢失
//				out.flush();
//				out.close();
//				return;
//			}
			
			long fromuid = HttpUtil.getLong(request, "fromuid",0);
			long destuid = HttpUtil.getLong(request, "destuid",0);
			String chatContent = HttpUtil.getString(request, "content","");
			int channelid = HttpUtil.getInt(request, "channelid",0);

//			if(channelid == 0) {
//				out.print("{\"result\":\"-2\"}");//频道ID不存在，需要添加
//				out.flush();
//				out.close();
//				return;
//			}
			
			if(BlackService.isBlackUser(Tools.getRemoteAddr(request))) {
				out.print("{\"result\":\"-2\"}");//已经被禁言，不能发表
				out.flush();
				out.close();
				return;
			}
			
			if(!WordsFilter.isAllowInput(chatContent)) {
				stat = -1;
				chatContent = WordsFilter.filterInput(chatContent);
			}

			
			String fromname = "";
			String destname = "";

			ChatUser from = ChatService.getUser(fromuid);
			if(from != null) {
				fromname = from.getNickname();
			}

			ChatUser dest = ChatService.getUser(destuid);
			if(dest != null) {
				destname = dest.getNickname();
			}

			//System.out.println("===44==content:"+ java.net.URLDecoder.decode(chatContent,"utf-8"));

			Content content = new Content();
			content.setChannelid(channelid);
			content.setFromuid(fromuid);
			content.setFromname(fromname);
			content.setDestuid(destuid);
			content.setDestname(destname);
			content.setContent(ShortUrlService.getShortContent(chatContent));
			content.setTktype(0);
			content.setGameid(gameid);
			content.setAttime(System.currentTimeMillis());
			ChatService.addCurrentGameChatContent(content);

			//同时将内容加入到世界聊天总表中，用于世界频道的读取
			ChatService.addWorldChatContent(content);
			
			//添加聊天log
			ChatContentLog log = new ChatContentLog();
			log.setChannelid(channelid);
			log.setFromuid(fromuid);
			log.setFromname(fromname);
			log.setDestuid(destuid);
			log.setDestname(destname);
			log.setContent(ShortUrlService.getShortContent(chatContent));
			log.setGameid(gameid);
			log.setAttime(System.currentTimeMillis());
			log.setIp(Tools.getRemoteAddr(request));
			ChatContentLogService.addLog(log);

			
			out.print("{\"result\":\""+stat+"\"}");//发言成功
			out.flush();
			out.close();

		} else if(act.equals("channelchatlist")) {
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			String channelid = HttpUtil.getString(request, "channelid","");
			String startIndex = HttpUtil.getString(request, "startIndex","");//频道聊天上次获取的最后一条聊天内容的发表时间
			long lasttime = HttpUtil.getLong(request, "lasttime",0);//上次获取的最后时间，用于私聊获取
			int count = HttpUtil.getInt(request, "count",100);//每页条数，如果不填默认为从开始位置读取100条
			long uid = HttpUtil.getLong(request, "uid",0);
			long clientTime = System.currentTimeMillis();

			long teamid = 0;//用户所在队伍ID
			
//			System.out.println("channelID:"+channelid+"==gameid:"+gameid+"==startIndex:"+startIndex+"==lasttime:"+lasttime+"==uid:"+uid);
			
			String format = HttpUtil.getString(request, "format","json");
			
			if(channelid.equals("") || startIndex.equals("")) {
				out.print("{\"result\":\"-1\"}");
				out.flush();
				out.close();
				return;
			}
			
			String[] channelTmp = channelid.split(",");
			String[] startIndexTmp = startIndex.split(",");

			if(channelTmp.length != startIndexTmp.length) {
				out.print("{\"result\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}

			boolean haveChannelOne = false;//是否包含频道1，如果包含，那么当前游戏频道就不需要读取，因为频道1为综合内容，已经包含了当前游戏聊天
			if(channelid.indexOf("1") != -1) {
				haveChannelOne = true;
			}

			String retStartIndex = "";//return all the channel startIndex
			List<Content> retChannelList = new ArrayList<Content>();

			for(int i=0;i<channelTmp.length;i++) {

				int curChannelID = Integer.parseInt(channelTmp[i]);
				String curStartIndex = startIndexTmp[i]; 

				if(curChannelID == 1) {
					//综合频道
					
					String lastIndex = curStartIndex;
					RetListBean bean = ChatService.getWorldChannelContentList(uid,curChannelID, gameid,Long.parseLong(curStartIndex),Long.parseLong(startIndexTmp[i+1]));
					if(bean != null) {
//						System.out.println("retListBean is not null,lastindex:"+bean.getLasttime());
						List<Content> endList = bean.getList();
						lastIndex = bean.getLasttime();
						if(endList != null) {
//							System.out.println("=======channel 1 retlist is not null");
//							for(int k=0;k<endList.size();k++) {
//								Content c = endList.get(k);
////								System.out.println("@@@@@@@@@@==="+c.getContent());
//							}
							retChannelList.addAll(endList);
						} else {
							System.out.println("retList is null ");
						}
					} else {
						System.out.println("retList is null");
					}
					retStartIndex = new StringBuffer(retStartIndex).append(lastIndex).append((i==(channelTmp.length-1)?"":",")).toString(); 

				} else if(curChannelID == 2) {
					//当前游戏频道

					if(!haveChannelOne) {
						//存在频道1的情况就不需要重新读取了
//						System.out.println("====channel 2=====curChannelID:"+curChannelID+"========curStartIndex:"+curStartIndex+"====retChannelListSize:"+retChannelList.size());

						String lastIndex = curStartIndex;
						RetListBean bean = ChatService.getCurrentGameChannelContentList(uid,curChannelID,gameid, Long.parseLong(curStartIndex));
						if(bean != null) {
//							System.out.println("channel 2 retlistbean is not null");
							List<Content> endList = bean.getList();
							lastIndex = bean.getLasttime();
							if(endList != null) {
//								System.out.println("channel 2 retlist is not null ,size="+endList.size());
								retChannelList.addAll(endList);
							} else {
								System.out.println("channel 2 retlist is null");
							}
						} else {
							System.out.println("channel 2 retlistbean is null");
						}
						retStartIndex = new StringBuffer(retStartIndex).append(lastIndex).append((i==(channelTmp.length-1)?"":",")).toString(); 
					} 
//					else {
//						retStartIndex = new StringBuffer(retStartIndex).append(curStartIndex).append((i==(channelTmp.length-1)?"":",")).toString(); 
//					}
				} else if(curChannelID == 3) {
					//队伍频道
					
//					System.out.println("====channel 3=====curChannelID:"+curChannelID+"========curStartIndex:"+curStartIndex+"====retChannelListSize:"+retChannelList.size());

					String lastIndex = curStartIndex;
					RetTeamListBean bean = TeamChatService.getTeamChatContentList(uid, curChannelID, gameid, Long.parseLong(curStartIndex));
					if(bean != null) {
//						System.out.println("channel 3 retlistbean is not null");
						List<Content> endList = bean.getList();
						lastIndex = bean.getLasttime();
						teamid = bean.getTeamid();
						if(endList != null) {
//							System.out.println("channel 3 retlist is not null ,size="+endList.size());
							retChannelList.addAll(endList);
						} else {
							System.out.println("channel 3 retlist is null");
						}
					} else {
						System.out.println("channel 3 retlistbean is null");
					}
					retStartIndex = new StringBuffer(retStartIndex).append(lastIndex).append((i==(channelTmp.length-1)?"":",")).toString(); 
				} else if(curChannelID == 4) {
					//待定
					retStartIndex = new StringBuffer(retStartIndex).append(curStartIndex).append((i==(channelTmp.length-1)?"":",")).toString(); 
					continue;
				} else if(curChannelID == 5) {
					//私聊
					if(lasttime == 0) {
						lasttime = System.currentTimeMillis();
					}

					List<Content> privateList = ChatService.getPrivateContentList(uid, lasttime, count);
					
					if(privateList != null && privateList.size() > 0) {
						Content content = privateList.get(privateList.size()-1);
						lasttime = content.getAttime();
						retChannelList.addAll(privateList);
					}
					retStartIndex = new StringBuffer(retStartIndex).append(lasttime).append((i==(channelTmp.length-1)?"":",")).toString();
//					System.out.println("=======ret lasttime:"+lasttime+"===size:"+((privateList == null)?0:privateList.size()));

				} else {
					continue;
				}
				
			}

			ChannelRetBean bean = new ChannelRetBean();
			bean.setChannelList(retChannelList);
			bean.setFreshinterval(freshTime);
			bean.setLasttime(lasttime);
			bean.setChannelLastIndex(retStartIndex);
			bean.setClientTime(clientTime);
			bean.setRequestChannelid(channelid);
			bean.setTeamid(teamid);
			
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
//				System.out.println(json.toString());
				out.print(json.toString());
			}

			out.flush();
			out.close();
//			System.out.println("=======================================================");

		} else if(act.equals("privatechatlist")) {
			long uid = HttpUtil.getLong(request, "uid",0);
			long lasttime = HttpUtil.getLong(request, "lasttime",0);
			int count = HttpUtil.getInt(request, "count",100);//每页条数
			
			String format = HttpUtil.getString(request, "format","json");
			
			if(lasttime == 0) {
				lasttime = System.currentTimeMillis();
			}
			List<Content> list = ChatService.getPrivateContentList(uid, lasttime, count);

			if(list != null && list.size() > 0) {
				Content content = list.get(list.size()-1);
				lasttime = content.getAttime();
			}
			
			RetBean bean = new RetBean();
			bean.setList(list);
			bean.setFreshinterval(freshTime);
			bean.setLasttime(lasttime);
			
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}
			out.flush();
			out.close();

		} else if(act.equals("gamechatlist")) {
			//获取当前游戏聊天内容列表
			//当前游戏聊天
			
			System.out.println("=====gamechatlist");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			
			int channelid = HttpUtil.getInt(request, "channelid",0);

			long startIndex = HttpUtil.getLong(request, "startIndex",0);//频道聊天上次获取的最后一条聊天内容的发表时间
			int count = HttpUtil.getInt(request, "count",100);//每页条数，如果不填默认为从开始位置读取100条
			long uid = HttpUtil.getLong(request, "uid",0);
			long clientTime = System.currentTimeMillis();
			
//			System.out.println("gameid:"+gameid+"======channelID:"+channelid+"====startIndex:"+startIndex+"====count:"+count+"===uid:"+uid);
			
			String format = HttpUtil.getString(request, "format","json");
			
			List<Content> retChannelList = new ArrayList<Content>();;
			String lastIndex = "0";

			
			RetListBean retBean = ChatService.getCurrentGameChannelContentList(uid,channelid,gameid, startIndex);
			if(retBean != null) {
				List<Content> endList = retBean.getList();
				lastIndex = retBean.getLasttime();
				if(endList != null) {
					retChannelList = endList;
				}
			}

			GameChannelRetBean bean = new GameChannelRetBean();
			bean.setChannelList(retChannelList);
			bean.setFreshinterval(freshTime);
			bean.setLasttime(System.currentTimeMillis());
			bean.setChannelLastIndex(Long.parseLong(lastIndex));
			bean.setClientTime(clientTime);
			bean.setRequestChannelid(new StringBuffer(channelid).toString());
			
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}

			out.flush();
			out.close();
		
		} else if(act.equals("editnick")) {
			long uid = HttpUtil.getLong(request, "uid");
			String newnickname = HttpUtil.getString(request, "nickname");
			
			ChatService.updateUserInfo(uid, newnickname);
			out.print("{\"result\":\"0\"}");//编辑成功
			out.flush();
			out.close();
		
		///////////////////////////////
		//  以下都是teamChat部分        //
		///////////////////////////////
		} else if(act.equals("getteam")) {
			//获取Team信息
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);

			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//用户未加入队伍
				out.flush();
				out.close();
				return;
			}
			
			ChatTeam ct = ChatTeamService.getTeam(teamid);
			if(ct == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}
			
			List<ChatTeamMember> list = ChatTeamService.getMemberList(teamid);
			if(list == null) {
				out.print("{\"result\":\"-4\"}");//列表为空
				out.flush();
				out.close();
				return;
			}
			
			ChatTeamRetBean retBean = new ChatTeamRetBean();
			retBean.setTeam(ct);
			retBean.setList(list);
			
			JSONArray json = JSONArray.fromObject(retBean);
			out.print(json.toString());
			out.flush();
			out.close();
			
		} else if(act.equals("addnewteam")) {
			//添加Team
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);

			//判断用户是否已经属于某个队伍，一个用户同一时间只能在一个队伍中
			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid != 0) {
				out.print("{\"result\":\"-2\"}");//已经加入队伍，不能创建
				out.flush();
				out.close();
				return;
			}
			
			//创建队伍
			ChatTeam bean = new ChatTeam();
			bean.setGameid(gameid);
			bean.setLeaderuid(uid);
			bean.setName("队伍");
			bean.setPassword("");
			bean.setLasttime(System.currentTimeMillis());
			long res = ChatTeamService.addTeam(bean);
			
			if(res == -1) {
				out.print("{\"result\":\"-3\"}");//创建失败
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\""+res+"\"}");//创建成功，返回新建队伍ID
			out.flush();
			out.close();
		
		} else if(act.equals("teaminvite")) {
			//邀请成员
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			if(uid == 0) {
				out.print("{\"result\":\"-2\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long inviteuid = HttpUtil.getLong(request, "inviteuid");//邀请的用户ID
			if(inviteuid == 0) {
				out.print("{\"result\":\"-3\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-4\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-5\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			if(team.getLeaderuid() != uid) {
				out.print("{\"result\":\"-6\"}");//只有队长可以邀请
				out.flush();
				out.close();
				return;
			}
			
			//检查用户是否已经在队伍中
			long myteamid = ChatTeamService.getTeamidByUID(inviteuid);
			if(myteamid != 0) {
				out.print("{\"result\":\"-7\"}");//用户已经加入队伍
				out.flush();
				out.close();
				return;
			}
			
			//检查队伍中的人数是否超过5人
			int number = ChatTeamService.getTeamMemberNum(teamid);
			if(number >= 5) {
				out.print("{\"result\":\"-8\"}");//队伍人数超过5人，不能再邀请其他成员加入
				out.flush();
				out.close();
				return;
			}
			
			User user = UserService.getByUid(inviteuid);
			if(user == null) {
				out.print("{\"result\":\"-9\"}");//用户不存在
				out.flush();
				out.close();
				return;
			}
			
			ChatTeamTempMember temp = ChatTeamService.getTempMemberWithNoCache(inviteuid, teamid);
			if(temp != null) {
				if(temp.getType() == 0) {
					out.print("{\"result\":\"-10\"}");//用户已经被邀请
					out.flush();
					out.close();
					return;
				}
			}
			
			
			ChatTeamTempMember bean = new ChatTeamTempMember();
			bean.setName(user.getName());
			bean.setTeamid(teamid);
			bean.setUid(uid);
			bean.setType(0);
			int res = ChatTeamService.addTempMember(bean);
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//邀请成功发出
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-11\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("applyjointeam")) {
			//申请加入
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			if(uid == 0) {
				out.print("{\"result\":\"-2\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-3\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-4\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			//检查用户是否已经在队伍中
			long myteamid = ChatTeamService.getTeamidByUID(uid);
			if(myteamid != 0) {
				out.print("{\"result\":\"-5\"}");//用户已经加入队伍，不能申请加入其他队伍
				out.flush();
				out.close();
				return;
			}
			
			//检查队伍中的人数是否超过5人
			int number = ChatTeamService.getTeamMemberNum(teamid);
			if(number >= 5) {
				out.print("{\"result\":\"-6\"}");//队伍人数超过5人，不能再申请加入
				out.flush();
				out.close();
				return;
			}
			
			User user = UserService.getByUid(uid);
			if(user == null) {
				out.print("{\"result\":\"-7\"}");//用户不存在
				out.flush();
				out.close();
				return;
			}
			
			ChatTeamTempMember temp = ChatTeamService.getTempMemberWithNoCache(uid, teamid);
			if(temp != null) {
				if(temp.getType() == 1) {
					out.print("{\"result\":\"-8\"}");//用户已经发出申请了
					out.flush();
					out.close();
					return;
				}
			}
			
			ChatTeamTempMember bean = new ChatTeamTempMember();
			bean.setName(user.getName());
			bean.setTeamid(teamid);
			bean.setUid(uid);
			bean.setType(1);
			int res = ChatTeamService.addTempMember(bean);
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//加入申请成功发出
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-9\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("getinvitelist")) {
			//获取队伍邀请列表
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			if(uid == 0) {
				out.print("{\"result\":\"-2\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			List<ChatTeamTempMember> list = ChatTeamService.getInviteList(uid);
			if(list == null) {
				out.print("{\"result\":\"-3\"}");//没有邀请列表
				out.flush();
				out.close();
				return;
			}
			
			JSONArray json = JSONArray.fromObject(list);
			out.print(json.toString());
			out.flush();
			out.close();

			
		} else if(act.equals("getapplymemberlist")) {
			//获取申请加入队伍的成员列表
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			if(uid == 0) {
				out.print("{\"result\":\"-2\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			//检查用户是否已经在队伍中
			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid == 0) {
				out.print("{\"result\":\"-5\"}");//用户尚未加入队伍
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-4\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			//检查队伍中的人数是否超过5人
			if(team.getLeaderuid() != uid) {
				out.print("{\"result\":\"-6\"}");//不是队长，不能查看列表
				out.flush();
				out.close();
				return;
			}

			List<ChatTeamTempMember> list = ChatTeamService.getApplyList(teamid);
			if(list == null) {
				out.print("{}");//没有申请列表
				out.flush();
				out.close();
				return;
			}
			
			JSONArray json = JSONArray.fromObject(list);
			out.print(json.toString());
			out.flush();
			out.close();
			
		} else if(act.equals("chekapplymember")) {
			//队长审核申请加入的成员
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			if(uid == 0) {
				out.print("{\"result\":\"-2\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long inviteuid = HttpUtil.getLong(request, "inviteuid");//邀请的用户ID
			if(inviteuid == 0) {
				out.print("{\"result\":\"-3\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-4\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			int status = HttpUtil.getInt(request, "status",-2);//审核状态
			
			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-5\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			if(team.getLeaderuid() != uid) {
				out.print("{\"result\":\"-6\"}");//只有队长可以处理申请
				out.flush();
				out.close();
				return;
			}
			
			//检查用户是否已经在队伍中
			long myteamid = ChatTeamService.getTeamidByUID(inviteuid);
			if(myteamid != 0) {
				out.print("{\"result\":\"-7\"}");//用户已经加入队伍
				out.flush();
				out.close();
				return;
			}
			
			//检查队伍中的人数是否超过5人
			int number = ChatTeamService.getTeamMemberNum(teamid);
			if(number >= 5) {
				out.print("{\"result\":\"-8\"}");//队伍人数超过5人，不能再批准其他成员加入
				out.flush();
				out.close();
				return;
			}
			
			User user = UserService.getByUid(inviteuid);
			if(user == null) {
				out.print("{\"result\":\"-9\"}");//用户不存在
				out.flush();
				out.close();
				return;
			}

			ChatTeamTempMember temp = ChatTeamService.getTempMemberWithNoCache(inviteuid, teamid);
			if(temp == null) {
				out.print("{\"result\":\"-10\"}");//用户没在临时队伍成员列表中，不需要处理
				out.flush();
				out.close();
				return;
			} else {
				if(temp.getType() != 1) {
					out.print("{\"result\":\"-11\"}");//用户没有发送申请
					out.flush();
					out.close();
					return;
				}
			}
			
			if(status == -2) {
				//直接拒绝
				//修改临时记录状态
				ChatTeamService.updateStatus(inviteuid, teamid, -2);
				out.print("{\"result\":\"-12\"}");
				out.flush();
				out.close();
				return;
			}

			
			//加入队伍
			ChatTeamMember bean = new ChatTeamMember();
			bean.setName(user.getName());
			bean.setTeamid(teamid);
			bean.setUid(inviteuid);
			int res = ChatTeamService.joinTeam(bean);
			if(res != -1) {
				//修改临时记录状态
				ChatTeamService.updateStatus(inviteuid, teamid, 0);
				out.print("{\"result\":\"0\"}");//加入成功
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-13\"}");//操作失败
			out.flush();
			out.close();

		} else if(act.equals("checkteaminvite")) {
			//用户处理队伍邀请
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			if(uid == 0) {
				out.print("{\"result\":\"-2\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}

			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-3\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			int status = HttpUtil.getInt(request, "status",-1);//审核状态
			
			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-4\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			//检查用户是否已经在队伍中
			long myteamid = ChatTeamService.getTeamidByUID(uid);
			if(myteamid != 0) {
				out.print("{\"result\":\"-5\"}");//用户已经加入队伍
				out.flush();
				out.close();
				return;
			}
			
			//检查队伍中的人数是否超过5人
			int number = ChatTeamService.getTeamMemberNum(teamid);
			if(number >= 5) {
				out.print("{\"result\":\"-6\"}");//队伍人数超过5人，不能加入
				out.flush();
				out.close();
				return;
			}
			
			User user = UserService.getByUid(uid);
			if(user == null) {
				out.print("{\"result\":\"-7\"}");//用户不存在
				out.flush();
				out.close();
				return;
			}

			ChatTeamTempMember temp = ChatTeamService.getTempMemberWithNoCache(uid, teamid);
			if(temp == null) {
				out.print("{\"result\":\"-8\"}");//用户没在临时队伍成员列表中，不需要处理
				out.flush();
				out.close();
				return;
			} else {
				if(temp.getType() != 0) {
					out.print("{\"result\":\"-9\"}");//队伍没有发送邀请
					out.flush();
					out.close();
					return;
				}
			}
			
			if(status == -1) {
				//直接拒绝
				//修改临时记录状态
				ChatTeamService.updateStatus(uid, teamid, -1);
				out.print("{\"result\":\"-10\"}");
				out.flush();
				out.close();
				return;
			}

			
			//加入队伍
			ChatTeamMember bean = new ChatTeamMember();
			bean.setName(user.getName());
			bean.setTeamid(teamid);
			bean.setUid(uid);
			int res = ChatTeamService.joinTeam(bean);
			if(res != -1) {
				//修改临时记录状态
				ChatTeamService.updateStatus(uid, teamid, 0);
				out.print("{\"result\":\"0\"}");//加入成功
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-11\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("dismissteam")) {
			//解散Team
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			//判断用户是否已经属于某个队伍，一个用户同一时间只能在一个队伍中
			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//没有加入队伍
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			if(team.getLeaderuid() != uid) {
				out.print("{\"result\":\"-4\"}");//不是队长，不能解散队伍
				out.flush();
				out.close();
				return;
			}
			
			int res = ChatTeamService.dismissTeam(teamid);
			if(res == -1) {
				out.print("{\"result\":\"-5\"}");//操作失败
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"0\"}");//解散成功
			out.flush();
			out.close();
			
		} else if(act.equals("setnewteamleader")) {
			//设置新的team leader
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//用户未加入队伍
				out.flush();
				out.close();
				return;
			}
			long leaderuid = HttpUtil.getLong(request, "newleaderuid",0);
			
			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			if(team.getLeaderuid() != uid) {
				out.print("{\"result\":\"-4\"}");//用户不是队长，不能任命新队长
				out.flush();
				out.close();
				return;
			}

			long leaderteamid = ChatTeamService.getTeamidByUID(leaderuid);
			if(leaderteamid == 0) {
				out.print("{\"result\":\"-5\"}");//用户还未加入队伍，不能被设置为队长
				out.flush();
				out.close();
				return;
			}

			if(leaderteamid != teamid ) {
				out.print("{\"result\":\"-6\"}");//用户不在该队伍中，不能被设置为队长
				out.flush();
				out.close();
				return;
			}
			
			if(uid == leaderuid) {
				out.print("{\"result\":\"-7\"}");//不能被设置自己为队长
				out.flush();
				out.close();
				return;
			}
			
			//设置新队长
			int res = ChatTeamService.setNewTeamLeader(teamid, leaderuid);
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//设置成功
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-8\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("jointeam")) {
			//加入team
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			
			//检查用户是否已经在队伍中
			long myteamid = ChatTeamService.getTeamidByUID(uid);
			if(myteamid != 0 && myteamid == teamid) {
				out.print("{\"result\":\"-4\"}");//用户已经加入队伍
				out.flush();
				out.close();
				return;
			}
			
			
			//检查队伍中的人数是否超过5人
			int number = ChatTeamService.getTeamMemberNum(teamid);
			if(number >= 5) {
				out.print("{\"result\":\"-5\"}");//队伍人数超过5人，不能加入
				out.flush();
				out.close();
				return;
			}
			
			User user = UserService.getByUid(uid);
			if(user == null) {
				out.print("{\"result\":\"-6\"}");//用户不存在
				out.flush();
				out.close();
				return;
			}
			
			ChatTeamMember bean = new ChatTeamMember();
			bean.setName(user.getName());
			bean.setTeamid(teamid);
			bean.setUid(uid);
			int res = ChatTeamService.joinTeam(bean);
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//加入成功
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-7\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("quitteam")) {
			//退出team
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			//检查用户是否已经在队伍中
			long myteamid = ChatTeamService.getTeamidByUID(uid);
			if(myteamid == 0 || myteamid != teamid) {
				out.print("{\"result\":\"-4\"}");//用户已经不在该队伍中
				out.flush();
				out.close();
				return;
			}

			if(team.getLeaderuid() == uid) {
				out.print("{\"result\":\"5\"}");//队长不能退出，只能解散队伍
				out.flush();
				out.close();
				return;
			}

			int res = ChatTeamService.quitTeam(teamid, uid);
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//退出成功
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-6\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("memberlist")) {
			//获取team member列表
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			long teamid = HttpUtil.getLong(request, "teamid",0);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//参数错误
				out.flush();
				out.close();
				return;
			}

			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			List<ChatTeamMember> list = ChatTeamService.getMemberList(teamid);
			if(list != null) {
				JSONArray json = JSONArray.fromObject(list);
				out.print(json.toString());
				out.flush();
				out.close();
				return;
			}

			out.print("{\"result\":\"-4\"}");//没有队伍列表
			out.flush();
			out.close();
			
		} else if(act.equals("kickfromteam")) {
			//从team中踢出
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			long uid = HttpUtil.getLong(request, "uid",0);
			long kickuid = HttpUtil.getLong(request, "kickuid",0);//被踢出的用户uid
			
			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//用户未加入队伍，没有资格踢出队伍成员
				out.flush();
				out.close();
				return;
			}
			
			ChatTeam team = ChatTeamService.getTeam(teamid);
			if(team == null) {
				out.print("{\"result\":\"-3\"}");//队伍不存在
				out.flush();
				out.close();
				return;
			}

			
			long kickteamid = ChatTeamService.getTeamidByUID(kickuid);
			if(kickteamid == 0) {
				out.print("{\"result\":\"-4\"}");//用户未加入队伍
				out.flush();
				out.close();
				return;
			}

			if(teamid != kickteamid) {
				out.print("{\"result\":\"-5\"}");//用户不属于队伍，不能踢出
				out.flush();
				out.close();
				return;
			}
			
			if(team.getLeaderuid() != uid) {
				out.print("{\"result\":\"-6\"}");//用户不是队长，不能管理
				out.flush();
				out.close();
				return;
			}

			if(uid == kickuid) {
				out.print("{\"result\":\"-7\"}");//不能自己踢出自己
				out.flush();
				out.close();
				return;
			}
			
			//从队伍中踢出
			int res = ChatTeamService.quitTeam(teamid, kickuid);
			if(res != -1) {
				out.print("{\"result\":\"0\"}");//退出成功
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"result\":\"-8\"}");//操作失败
			out.flush();
			out.close();
			
		} else if(act.equals("sendteamchat")) {
			//游戏队伍聊天
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			
			long fromuid = HttpUtil.getLong(request, "fromuid",0);
			long destuid = HttpUtil.getLong(request, "destuid",0);
			String chatContent = HttpUtil.getString(request, "content","");
			int channelid = HttpUtil.getInt(request, "channelid",0);

			if(channelid == 0) {
				out.print("{\"result\":\"-2\"}");//频道ID不存在，需要添加
				out.flush();
				out.close();
				return;
			}
			
			long teamid = ChatTeamService.getTeamidByUID(fromuid);
			if(teamid == 0) {
				out.print("{\"result\":\"-3\"}");//用户未加入队伍
				out.flush();
				out.close();
				return;
			}

			String fromname = "";
			String destname = "";

			ChatUser from = ChatService.getUser(fromuid);
			if(from != null) {
				fromname = from.getNickname();
			}

			ChatUser dest = ChatService.getUser(destuid);
			if(dest != null) {
				destname = dest.getNickname();
			}

//			System.out.println("===44==content:"+ java.net.URLDecoder.decode(chatContent,"utf-8"));

			TeamContent content = new TeamContent();
			content.setChannelid(channelid);
			content.setFromuid(fromuid);
			content.setFromname(fromname);
			content.setDestuid(destuid);
			content.setDestname(destname);
			content.setContent(ShortUrlService.getShortContent(chatContent));
			content.setGameid(gameid);
			content.setAttime(System.currentTimeMillis());
			content.setTeamid(teamid);
			TeamChatService.addTeamChatContent(content);

			out.print("{\"result\":\"0\"}");//发言成功
			out.flush();
			out.close();

		} else if(act.equals("teamchatlist")) {
			//获取队伍游戏聊天内容列表
			
//			System.out.println("=====gameTeamchatlist");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			if(gameid == 0) {
				out.print("{\"result\":\"-1\"}");//参数丢失
				out.flush();
				out.close();
				return;
			}
			
			int channelid = HttpUtil.getInt(request, "channelid",0);

			long startIndex = HttpUtil.getLong(request, "startIndex",0);//频道聊天上次获取的最后一条聊天内容的发表时间
			int count = HttpUtil.getInt(request, "count",100);//每页条数，如果不填默认为从开始位置读取100条
			long uid = HttpUtil.getLong(request, "uid",0);
			long clientTime = System.currentTimeMillis();
			
			long teamid = ChatTeamService.getTeamidByUID(uid);
			if(teamid == 0) {
				out.print("{\"result\":\"-2\"}");//用户未加入队伍
				out.flush();
				out.close();
				return;
			}
			
//			System.out.println("gameid:"+gameid+"======channelID:"+channelid+"====startIndex:"+startIndex+"====count:"+count+"===uid:"+uid);
			
			String format = HttpUtil.getString(request, "format","json");
			
			List<Content> retChannelList = null;
			String lastIndex = "0";
			
			RetTeamListBean retBean = TeamChatService.getTeamChatContentList(uid,channelid,gameid, startIndex);
			if(retBean != null) {
				List<Content> endList = retBean.getList();
				lastIndex = retBean.getLasttime();
				if(endList != null) {
					retChannelList = new ArrayList<Content>();
					retChannelList = endList;
				}
			}

			TeamChatRetBean bean = new TeamChatRetBean();
			bean.setList(retChannelList);
			bean.setFreshinterval(freshTime);
			bean.setLasttime(Long.parseLong(lastIndex));
			bean.setClientTime(clientTime);
			bean.setRequestChannelid(new StringBuffer(channelid).toString());
			bean.setTeamid(teamid);
			
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}

			out.flush();
			out.close();
		
		} else {
			out.print("{}");
			out.flush();
			out.close();
		}
		
		
	}
}
