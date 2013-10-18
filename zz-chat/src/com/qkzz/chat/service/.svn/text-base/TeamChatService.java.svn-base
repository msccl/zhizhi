package com.qkzz.chat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.qkzz.chat.bean.Channel;
import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.RetListBean;
import com.qkzz.chat.bean.RetTeamListBean;
import com.qkzz.chat.bean.TeamContent;
import com.qkzz.chat.bean.TeamFreshBean;
import com.qkzz.chat.dao.TeamContentDao;
import com.qkzz.chat.dao.impl.TeamContentDaoImpl;

public class TeamChatService {

	private static TeamContentDao contentDao = new TeamContentDaoImpl();
	
	private static ConcurrentHashMap<String,List<Content>> teamChatContentMap = new ConcurrentHashMap<String,List<Content>> ();//当前游戏队伍聊天内容缓存
	private static ConcurrentHashMap<String,TeamFreshBean> teamFreshListMap = new ConcurrentHashMap<String,TeamFreshBean> ();//当前游戏队伍刷新列表缓存

//	//启动监控线程
//	static {
//		System.out.println("Team check thread start.....");
//		TeamCheckDeamon thread = new TeamCheckDeamon();
//		thread.start();
//		System.out.println("Team check thread start over.....");
//	}
	
	
	/**
	 * 添加队伍聊天记录
	 * @param content
	 */
	public static void addTeamChatContent(TeamContent content) {
		Channel c = ChatService.getChannel(content.getChannelid());
		if(c == null) {
			System.out.println("Exception: channel ID "+content.getChannelid()+" is not exist");
			return;
		}
		
		//添加内容到数据表中
		contentDao.addContent(content);

		//加入本地刷新列表缓存
		String key  = new StringBuffer(content.getGameid()).append("-").append(content.getTeamid()).toString();
		TeamFreshBean bean = new TeamFreshBean();
		bean.setGameid(content.getGameid());
		bean.setTeamid(content.getTeamid());
		bean.setLasttime(System.currentTimeMillis());
		teamFreshListMap.put(key, bean);
		
//		//将发言记录到日志中
//		ChatLog.chatLog(content.getFromuid(), content.getFromname(), content.getDestuid(), content.getDestname(), content.getContent());
	}


	
	/**
	 * 获取聊天内容列表
	 * @param channelid
	 * @param startindex
	 * @param num
	 * @return
	 */
	public static RetTeamListBean getTeamChatContentList(long uid,int channelid,int gameid,long lasttime) {
		Channel c = ChatService.getChannel(channelid);
		if(c == null) {
			return null;
		}
		
		long teamid = ChatTeamService.getTeamidByUID(uid);
		if(teamid == 0) {
			return null;
		}
		
		System.out.println("===="+channelid+"------------gameid:"+gameid+"------------lasttime:"+lasttime);
		RetTeamListBean bean = new RetTeamListBean();
		
		String key = new StringBuffer("").append(gameid).append("-").append(teamid).toString();
		List<Content> contentList = null;
		if(teamChatContentMap.containsKey(key)) {
			contentList = teamChatContentMap.get(key);
			System.out.println("11111111111111==="+contentList.size());

			List<Content> retList = null;
			long retLastTime = 0;
			if(contentList != null && !contentList.isEmpty()) {
				
				if(lasttime != 0) {
					//采用倒叙，最新的内容排在最前，获取到内容后进行排序翻转
					Content lastContent = contentList.get(0);
					if(lastContent.getAttime() < lasttime) {
						bean.setList(null);
						bean.setLasttime(new StringBuffer("").append(lasttime).toString());
						bean.setTeamid(teamid);
						return bean;
					}
					
					retList = new ArrayList<Content>();

					for(Content content:contentList) {
						if(retLastTime == 0) {
							retLastTime = content.getAttime();
						}
						
						if(content.getAttime() < lasttime) {
							break;
						}
						
						if(content.getFromuid() != uid) {
							retList.add(content);
						}
					}
					
					if(!retList.isEmpty() && retList.size() > 1) {
						Collections.reverse(retList);
					}
					
					if(retList.isEmpty()) {
						bean.setList(null);
					} else {
						bean.setList(retList);
					}
					bean.setLasttime(new StringBuffer("").append(retLastTime==0?lasttime:retLastTime).toString());
					bean.setTeamid(teamid);
					return bean;
				} else {
					Content lastContent = contentList.get(0);
					bean.setList(null);
					bean.setLasttime(new StringBuffer("").append(lastContent.getAttime()).toString());
					bean.setTeamid(teamid);
					return bean;
				}
			} 
		} 
		bean.setList(null);
		bean.setLasttime(new StringBuffer("").append(lasttime).toString());
		bean.setTeamid(teamid);
		return bean;
	}


	/**
	 * 刷新内容到本地缓存
	 * @param gameid
	 * @param teamid
	 * @param freshInterval
	 */
	public static void freshTeamContentCache(int gameid,long teamid,int freshInterval) {

		List<Content> list = contentDao.getAllList(teamid, freshInterval);
		String key = new StringBuffer("").append(gameid).append("-").append(teamid).toString();
		if(list != null) {
			teamChatContentMap.put(key, list);
		}
		//清理数据表中过期数据
		contentDao.clearOutOfDate(teamid, freshInterval);
	}

	
	/**
	 * 返回本地刷新列表，用于更新到内存表中
	 * @return
	 */
	public static ConcurrentHashMap<String,TeamFreshBean> getTeamFreshMap() {
		if(teamFreshListMap.isEmpty()) {
			return null;
		}
		return teamFreshListMap;
	}
	
	/**
	 * 从刷新列表中删除记录
	 * @param gameid
	 * @param teamid
	 */
	public static void removeFromFreshList(int gameid,long teamid) {
		if(teamFreshListMap != null && !teamFreshListMap.isEmpty()) {
			String key  = new StringBuffer(gameid).append("-").append(teamid).toString();
			if(teamFreshListMap.containsKey(key)) {
				teamFreshListMap.remove(key);
			}
		}
	}
}
