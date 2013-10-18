package com.qkzz.chat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.qkzz.chat.bean.Channel;
import com.qkzz.chat.bean.ChatUser;
import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.PrivateContent;
import com.qkzz.chat.bean.RetListBean;
import com.qkzz.chat.dao.ChannelContentDao;
import com.qkzz.chat.dao.ChannelDao;
import com.qkzz.chat.dao.ChatUserDao;
import com.qkzz.chat.dao.PrivateContentDao;
import com.qkzz.chat.dao.WorldChatContentDao;
import com.qkzz.chat.dao.impl.ChannelContentDaoImpl;
import com.qkzz.chat.dao.impl.ChannelDaoImpl;
import com.qkzz.chat.dao.impl.ChatUserDaoImpl;
import com.qkzz.chat.dao.impl.PrivateContentDaoImpl;
import com.qkzz.chat.dao.impl.WorldChatContentDaoImpl;
import com.qkzz.common.GetDate;
import com.qkzz.common.LRUMap;
import com.qkzz.log.ChatLog;
import com.qkzz.user.bean.Member;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.GlobalService;
import com.qkzz.user.service.GuestUserService;
import com.qkzz.user.service.MemberService;

public class ChatService {

	private static ChannelDao channel = new ChannelDaoImpl();
	private static ChatUserDao user = new ChatUserDaoImpl();
	private static ChannelContentDao ccd = new ChannelContentDaoImpl();
	private static PrivateContentDao pcd = new PrivateContentDaoImpl();
	private static WorldChatContentDao wccd = new WorldChatContentDaoImpl();
	
	private static List<Content> worldChannelContentList = new ArrayList<Content> ();//综合频道聊天内容缓存
	private static ConcurrentHashMap<String,List<Content>> curGameContentMap = new ConcurrentHashMap<String,List<Content>> ();//当前游戏聊天内容缓存
	private static ConcurrentHashMap<Integer,Channel> channelMap = new ConcurrentHashMap<Integer,Channel>();//聊天频道缓存
	private static ConcurrentHashMap<Integer,Integer> gameChannelMap = new ConcurrentHashMap<Integer,Integer>();//游戏ID与聊天频道对应关系缓存
	private static List<String> channelFreshList = new ArrayList<String>();//频道列表，主要用于遍历，由于map遍历容易产生内存问题，直接放弃遍历，采用list读取列表拿到key值，然后到map中获取内容list
	private static List<String> privateList = new ArrayList<String>();//私聊最新发言列表，如果有新的发言，需要将其加入到该列表中，用于缓存更新时使用
	private static LRUMap<Long,ChatUser> userMap = new LRUMap<Long,ChatUser>(2000);//聊天用户缓存，自动维护队列大小
	private static int FreshInterval = 0;//每次客户端调用间隔
	private static int worldChatFreshFlag = 0;//世界频道内容刷新标记  0：没有内容不需要刷新   1：有内容，需要刷新
	private static ConcurrentHashMap<String,List<Long>> contentIndexMap = new ConcurrentHashMap<String,List<Long>>();//保存每个频道的内容ID列表，用于客户端读取时的索引，从上一次读取的位置读取最新内容 
	private static List<Long> worldContentIndexList = new ArrayList<Long>();//世界频道内容发表时间索引，可以快速定位某个时间所处位置
	
//	public static ConcurrentHashMap<String,List<Content>> getContentMap() {
//		return contentMap;
//	}
	
//	//启动监控线程
//	static {
//		System.out.println("Channel check thread start.....");
//		ChannelCheckDeamon ium = new ChannelCheckDeamon();
//		ium.start();
//		System.out.println("Channel check thread start over.....");
//	}
	
	/**
	 * 从缓存中获取用户昵称信息
	 * @param uid
	 * @return
	 */
	public static ChatUser getUser(long uid) {
		//需要判断用户第一次来的情况，从用户库中读取信息然后加入
		if(userMap.containsKey(uid)) {
			return userMap.get(uid);
		}
		ChatUser u = null;
		if(uid < 100000000) {
			u = user.getChatUserInfo(uid);
			if(u == null) {
				//从用户库中获取信息并加入到聊天用户表中
				Member member = MemberService.getById(uid);
				if(member != null) {
					u = new ChatUser();
					u.setUid(uid);
					u.setNickname(member.getName());
					u.setStatus(0);
					u.setLasttime(System.currentTimeMillis());
					u.setUpdatetime(GetDate.getStringDate());
					user.addChatUser(uid, member.getName());
				}
			}
			userMap.put(uid, u);
		} else {
			User user = GuestUserService.getByID(uid);
			if(user == null) {
				return null;
			}
			u = new ChatUser();
			u.setUid(uid);
			u.setNickname(user.getName());
			u.setStatus(0);
			u.setUpdatetime(GetDate.getStringDate());
			u.setLasttime(System.currentTimeMillis());
			userMap.put(uid, u);
		}
		return u;
	}
	
	
	/**
	 * 更新聊天频道中用户的昵称信息
	 * @param uid
	 * @param nickname
	 */
	public static void updateUserInfo(long uid,String nickname) {
		ChatUser u = getUser(uid);
		if(u == null) {
			return;
		}
		if(uid < 999999) {
			user.updateChatUserInfo(uid, nickname);
		}
			
		//更新缓存
		u.setNickname(nickname);
		userMap.put(uid, u);
	}
	
	/**
	 * 获取频道信息
	 * @param id
	 * @return
	 */
	public static Channel getChannel(int id) {
		if(channelMap.containsKey(id)) {
			return channelMap.get(id);
		}
		
		Channel c = channel.getChannel(id);
		if(c != null) {
			channelMap.put(id, c);
		}
		return c;
	}
	
	
	/**
	 * 通过游戏ID获取频道ID
	 * @param gameid
	 * @return
	 */
	public static int getChannelIDByGameID(int gameid) {
		if(gameChannelMap.containsKey(gameid)) {
			return gameChannelMap.get(gameid);
		}
		
		//内存中不存在，通过数据库查找
		int channelID = channel.getChannelIDByGameID(gameid);
		if(channelID == -1) {
			return channelID;
		}

		gameChannelMap.put(gameid, channelID);
		return channelID;
		
	}
	
	/**
	 * 获取频道ID列表
	 * @param category
	 * @param startIndex
	 * @param number
	 * @return
	 */
	public static List<String> getChannelFreshList() {
		if(channelFreshList != null && !channelFreshList.isEmpty()) {
			return channelFreshList;
		}
		return null;
	}


	/**
	 * 返回世界频道内容刷新标志
	 * @return
	 */
	public static int getWorldChatFreshFlag() {
		return worldChatFreshFlag;
	}
	
	
	/**
	 * 返回私聊监控列表
	 * @return
	 */
	public static List<String> getPrivateIDList() {
		if(privateList != null && !privateList.isEmpty()) {
			return privateList;
		}
		return null;
	}

	
	/**
	 * 添加当前游戏聊天内容
	 * 发言内容需要过滤
	 * @param channelid
	 * @param content
	 */
	public static void addCurrentGameChatContent(Content content) {
		Channel c = getChannel(content.getChannelid());
		if(c == null) {
			System.out.println("Exception: channel ID "+content.getChannelid()+" is not exist");
			return;
		}
		
		//添加内容到数据表中
		ccd.addContent(content);
		
		//判断频道列表中是否存在该频道ID，如果不存在，增加该ID，用于线程监控用
		String key = new StringBuffer("").append(content.getChannelid()).append("-").append(content.getGameid()).toString();
		if(!channelFreshList.contains(key)) {
			System.out.println("addGameChat==========key:"+key);
			channelFreshList.add(key);
		}
		//将发言记录到日志中
		ChatLog.chatLog(content.getFromuid(), content.getFromname(), content.getDestuid(), content.getDestname(), content.getContent());
	}
	

	public static void addWorldChatContent(Content content) {
		Channel c = getChannel(content.getChannelid());
		if(c == null) {
			System.out.println("Exception: channel ID "+content.getChannelid()+" is not exist");
			return;
		}
		
		//添加内容到数据表中
		wccd.addContent(content);
		
		//修改世界频道刷新标识，如果没有设置，直接设置为1，如果已经设置了，直接略过
		if(worldChatFreshFlag == 0) {
			worldChatFreshFlag = 1;
		}
		//将发言记录到日志中
		ChatLog.chatLog(content.getFromuid(), content.getFromname(), content.getDestuid(), content.getDestname(), content.getContent());
	}

	
	/**
	 * 添加私聊聊天内容
	 * 发言内容需要过滤
	 * @param channelid
	 * @param content
	 */
	public static void addPrivateChatContent(PrivateContent content) {
		pcd.addFromContent(content);
		pcd.addDestContent(content);
		
		//将发言记录到日志中
		ChatLog.chatLog(content.getFromuid(), content.getFromname(), content.getDestuid(), content.getDestname(), content.getContent());
	}

	/**
	 * 获取聊天内容列表
	 * @param channelid
	 * @param startindex
	 * @param num
	 * @return
	 */
	public static RetListBean getCurrentGameChannelContentList(long uid,int channelid,int gameid,long lasttime) {
		Channel c = getChannel(channelid);
		if(c == null) {
			return null;
		}
		
		System.out.println("===="+channelid+"------------gameid:"+gameid+"------------lasttime:"+lasttime);
		RetListBean bean = new RetListBean();
		
		String key = new StringBuffer("").append(channelid).append("-").append(gameid).toString();
		List<Content> contentList = null;
		if(curGameContentMap.containsKey(key)) {
			contentList = curGameContentMap.get(key);
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
					return bean;
				} else {
					Content lastContent = contentList.get(0);
					bean.setList(null);
					bean.setLasttime(new StringBuffer("").append(lastContent.getAttime()).toString());
					return bean;
				}
			} 
		} 
		bean.setList(null);
		bean.setLasttime(new StringBuffer("").append(lasttime).toString());
		return bean;
	}
	

	/**
	 * 获取世界频道聊天内容
	 * @param channelid
	 * @param lasttime
	 * @param num
	 * @return
	 */
	public static RetListBean getWorldChannelContentList(long uid,int channelid,int gameid,long channelOneIndex,long channelTwoIndex) {
		RetListBean bean = new RetListBean();
		Channel c = getChannel(channelid);
		if(c == null) {
			bean.setList(null);
			bean.setLasttime(new StringBuffer("").append(channelOneIndex).toString());
			return bean;
		}
		
		List<Content> retList = null;
		long channelOneLastTime = 0;//综合最后发表时间
		long channelTwoLastTime = 0;//当前最后发表时间
		
		if(worldChannelContentList != null && !worldChannelContentList.isEmpty()) {
			
			if(channelOneIndex != 0) {
				//采用倒叙，最新的内容排在最前，获取到内容后进行排序翻转
				Content lastContent = worldChannelContentList.get(0);
				if(lastContent.getAttime() <= channelOneIndex) {
					bean.setList(null);
					bean.setLasttime(new StringBuffer("").append(channelOneIndex).append(",").append(channelTwoIndex).toString());
					return bean;
				}
				
				retList = new ArrayList<Content>();

				for(Content content:worldChannelContentList) {
					if(channelOneLastTime == 0) {
						channelOneLastTime = content.getAttime();
					}
					
					if(content.getChannelid() == 2 && content.getGameid() != gameid) {
						continue;
					}

					if(content.getChannelid() == 2 && content.getGameid() == gameid && channelTwoLastTime == 0) {
						channelTwoLastTime = content.getAttime();
					}
					
					
					if(content.getAttime() <= channelOneIndex) {
						System.out.println("222222222222");
						break;
					}
					
					if(content.getFromuid() != uid) {
						System.out.println("=====uid:"+uid+"==content:"+content.getContent());
						retList.add(content);
					}
				}
				
				if(!retList.isEmpty() && retList.size() > 1) {
					System.out.println("333333333333333333333333");
					Collections.reverse(retList);
				}
				
				if(retList.isEmpty()) {
					System.out.println("eeeeeeeeeeeeeeeeeeeeee");
					bean.setList(null);
				} else {
					System.out.println("rrrrrrrrrrrrrrrrrrrrrrr");
					bean.setList(retList);
				}
				bean.setLasttime(new StringBuffer("").append(channelOneLastTime).append(",").append(channelTwoLastTime==0?channelTwoIndex:channelTwoLastTime).toString());
				return bean;
			} else {
				Content lastContent = worldChannelContentList.get(0);
				bean.setList(null);
				bean.setLasttime(new StringBuffer("").append(lastContent.getAttime()).append(",").append(channelTwoIndex).toString());
				return bean;
			}
		} 
		
		System.out.println("444444444444444444444444444");

		bean.setList(null);
		bean.setLasttime(new StringBuffer("").append(channelOneIndex).append(",").append(channelTwoIndex).toString());
		return bean;
	}

	
	/**
	 * 通过传递过来的上一次读取的最后时间定位本次读取的初始位置
	 * @param channelid
	 * @param lasttime
	 * @return
	 */
	public static int getCurrentGameChannelContentIndexByLastTime(int channelid,int gameid,long lasttime) {
		Channel c = getChannel(channelid);
		if(c == null) {
			return 0;
		}
		
		String key = new StringBuffer(channelid).append("-").append(gameid).toString();
		List<Long> contentIndexList = null;
		if(contentIndexMap.containsKey(key)) {
			contentIndexList = contentIndexMap.get(key);
			int index = contentIndexList.indexOf(lasttime);
			if(index == -1) {
				return 0;
			} else {
//				if(index < contentIndexList.size()) {
//					index = index + 1;
//				}
				return index;
			}
		}
		return 0;
	}

	/**
	 * 通过传递过来的上一次读取的最后时间定位本次读取的初始位置
	 * @param channelid
	 * @param lasttime
	 * @return
	 */
	public static int getWorldChannelContentIndexByLastTime(long lasttime) {

		if(worldContentIndexList != null) {
			int index =  worldContentIndexList.indexOf(lasttime);
			return (index == -1)?0:index;
		}
		return 0;
	}
	
	
	/**
	 * 获取私聊聊天内容列表
	 * @param channelid
	 * @param startindex
	 * @param num
	 * @return
	 */
	public static List<Content> getPrivateContentList(long uid,long lasttime,int num) {
		return pcd.getList(uid, lasttime, num);
	}


	/**
	 * 刷新频道聊天内容缓存
	 * @param channelid
	 * @param freshInterval
	 */
	public static void freshWorldChannelContentCache(int freshInterval) {

		List<Content> list = wccd.getAllList(freshInterval);
		if(list == null) {
			System.out.println("Fresh list is null");
			if(worldChatFreshFlag == 1) {
				worldChatFreshFlag = 0;
				return;
			}
		}
		
		System.out.println("Fresh list not null,size="+list.size());
		worldChannelContentList = list;
		
		//获取内容列表的ID索引
		List<Long> indexList = new ArrayList<Long>();
		for(int i=0;i<list.size();i++) {
			Content content = list.get(i);
			indexList.add(content.getAttime());
		}
		worldContentIndexList = indexList;

		//刷新之后，将频道对应的刷新标志设置为不刷新，直到有新的留言提交时再重新改变状态
		if(worldChatFreshFlag == 1) {
			worldChatFreshFlag = 0;
		}

		/**
		 * 清理过期数据
		 */
		wccd.clearOutOfDate(30);
	}


	/**
	 * 刷新当前游戏频道内容
	 * @param channelid
	 * @param gameid
	 * @param freshInterval
	 */
	public static void freshCurrentGameContentCache(int channelid,int gameid,int freshInterval) {

		Channel c = getChannel(channelid);
		if(c == null) {
			return;
		}

		List<Content> list = ccd.getAllList(channelid,gameid, freshInterval);
		String key = new StringBuffer("").append(channelid).append("-").append(gameid).toString();
		if(list == null) {
			if(channelFreshList.contains(key)) {
				channelFreshList.remove(key);
				return;
			}
		}
		
		curGameContentMap.put(key, list);
		
		//获取内容列表的ID索引
		List<Long> indexList = new ArrayList<Long>();
		for(int i=0;i<list.size();i++) {
			Content content = list.get(i);
			indexList.add(content.getAttime());
		}
		contentIndexMap.put(key, indexList);

		//刷新之后，将该频道对应的ID从更新队列中删除，直到有新的留言提交时再重新添加到队列中
		if(channelFreshList.contains(key)) {
			channelFreshList.remove(key);
		}
		
		//清理数据表中过期数据
		ccd.clearOutOfDate(gameid&0xff, freshInterval);
	}
	
	
//	/**
//	 * 刷新私聊频道内容缓存
//	 * @param fromuid
//	 * @param destuid
//	 * @param freshInterval
//	 */
//	public static void freshPrivateContentCache(long fromuid,long destuid,int freshInterval) {
//
//		String key = getPrivateKeystr(fromuid,destuid);
//		List<Content> list = pcd.getAllList(fromuid, destuid, freshInterval);
//		if(list == null) {
//			privateList.remove(key);
//			return;
//		}
//		privateContentMap.put(key, list);
//
//		//刷新之后，将该频道对应的ID从更新队列中删除，直到有新的留言提交时再重新添加到队列中
//		privateList.remove(key);
//		
//		//清理数据表中的过期数据
//		long tableID = fromuid & 0xff;
//		if(destuid < fromuid) {
//			tableID = destuid & 0xff;
//		}
//		pcd.clearOutOfDate(Integer.parseInt(Long.toString(tableID)), freshInterval);
//	}
	
	
	/**
	 * 返回刷新间隔
	 * @return
	 */
	public static int getFreshInterval() {
		if(FreshInterval == 0) {
			refreshInterval(1,0);
		}
		return FreshInterval;
	}
	
	/**
	 * 刷新
	 * @param appid
	 */
	public static void refreshInterval(int appid,int subid) {
		//调用全局修改方法
		int newFreshInterval = GlobalService.getFreshInterval(appid, subid);//修改为方法
		FreshInterval = newFreshInterval;
	}
	
//	/**
//	 * 根据用户ID，按照较小UID进行拆分，并获取对应的数据表ID
//	 * @param fromuid
//	 * @param destuid
//	 * @return
//	 */
//	public static long getPrivateContentTableID(long fromuid,long destuid) {
//		long ret = fromuid;
//		if(fromuid > destuid) {
//			ret = destuid;
//		}
//		return ret&0xff;
//	}
	
//	/**
//	 * 根据用户ID，合成查询用的key值，小的uid在前，大的在后
//	 * @param fromuid
//	 * @param destuid
//	 * @return
//	 */
//	public static String getPrivateKeystr(long fromuid,long destuid) {
//		String ret = new StringBuffer().append(fromuid).append("-").append(destuid).toString();
//		if(fromuid > destuid) {
//			ret = new StringBuffer().append(destuid).append("-").append(fromuid).toString();
//		}
//		return ret;
//	}
}	
