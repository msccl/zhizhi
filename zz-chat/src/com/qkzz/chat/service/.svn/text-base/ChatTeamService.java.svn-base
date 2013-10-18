package com.qkzz.chat.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.qkzz.chat.bean.ChatTeam;
import com.qkzz.chat.bean.ChatTeamBo;
import com.qkzz.chat.bean.ChatTeamIDBo;
import com.qkzz.chat.bean.ChatTeamMember;
import com.qkzz.chat.bean.ChatTeamTempMember;
import com.qkzz.chat.bean.ChatTeamTempMemberBo;
import com.qkzz.chat.bean.TeamFreshBean;
import com.qkzz.chat.dao.ChatTeamDao;
import com.qkzz.chat.dao.ChatTeamMemberAllDao;
import com.qkzz.chat.dao.ChatTeamMemberDao;
import com.qkzz.chat.dao.ChatTeamTempMemberDao;
import com.qkzz.chat.dao.impl.ChatTeamDaoImpl;
import com.qkzz.chat.dao.impl.ChatTeamMemberAllDaoImpl;
import com.qkzz.chat.dao.impl.ChatTeamMemberDaoImpl;
import com.qkzz.chat.dao.impl.ChatTeamTempMemberDaoImpl;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.UserService;

public class ChatTeamService {

	private static ChatTeamDao teamDao = new ChatTeamDaoImpl();
	private static ChatTeamMemberAllDao memberAllDao = new ChatTeamMemberAllDaoImpl();
	private static ChatTeamMemberDao memberDao = new ChatTeamMemberDaoImpl();
	private static ChatTeamTempMemberDao tempMemberDao = new ChatTeamTempMemberDaoImpl();
	
	private static ConcurrentHashMap<Long,ChatTeamBo> chatTeamMap = new ConcurrentHashMap<Long,ChatTeamBo> (500);//队伍信息缓存，通过传入队伍ID获取队伍信息
	private static ConcurrentHashMap<Long,ChatTeamIDBo> chatTeamidByUIDMap = new ConcurrentHashMap<Long,ChatTeamIDBo> (200);//队伍ID信息缓存，通过传入的用户ID获取队伍ID
	private static ConcurrentHashMap<String,ChatTeamTempMemberBo> tempMemberMap = new ConcurrentHashMap<String,ChatTeamTempMemberBo> (20000);//队伍临时成员缓存

	
	/**
	 * 通过队伍ID获取队伍信息
	 * @param teamid
	 * @return
	 */
	public static ChatTeam getTeam(long teamid) {
		if(chatTeamMap.containsKey(teamid)) {
			ChatTeamBo ctb = chatTeamMap.get(teamid);
			if(System.currentTimeMillis() - ctb.getLastFreshTime() < 15*1000L) {
				return ctb.getCt();
			}
		} 

		ChatTeamBo ctb = new ChatTeamBo();
		ChatTeam ct = teamDao.getTeam(teamid);
		if(ct != null) {
			ctb.setCt(ct);
			ctb.setLastFreshTime(System.currentTimeMillis());
			chatTeamMap.put(teamid, ctb);
		}
		return ct;
	}
	

	/**
	 * 通过用户uid获取用户所在的队伍ID，如果没有加入队伍，返回0
	 * @param uid
	 * @return
	 */
	public static long getTeamidByUID(long uid) {
		if(chatTeamidByUIDMap.containsKey(uid)) {
			System.out.println("111111111");
			ChatTeamIDBo cti = chatTeamidByUIDMap.get(uid);
			if(System.currentTimeMillis() - cti.getLastFreshTime() < 15*1000L) {
				return cti.getTeamid();
			}
		} 

		ChatTeamIDBo cti = new ChatTeamIDBo();
		long teamid = memberAllDao.getTeamidByUID(uid);
		cti.setTeamid(teamid);
		cti.setLastFreshTime(System.currentTimeMillis());
		System.out.println("222222===teamid:"+teamid);
		chatTeamidByUIDMap.put(uid, cti);
		return teamid;
	}
	
	
	/**
	 * 创建队伍，返回新建队伍ID
	 * @param bean
	 * @return
	 */
	public static long addTeam(ChatTeam bean) {
		int res = teamDao.addTeam(bean);
		if(res != -1) {
			long teamid = teamDao.getLastInsertID(bean.getLeaderuid());
			long leaderuid = bean.getLeaderuid();
			User user = UserService.getByUid(leaderuid);
			if(user != null) {
				//在成员表中增加记录，包括成员总表
				ChatTeamMember member = new ChatTeamMember();
				member.setUid(bean.getLeaderuid());
				member.setName(user.getName());
				member.setTeamid(teamid);
				memberDao.joinTeam(member);
				memberAllDao.joinTeam(member);
			}
			
			return teamid;
		}
		return -1;
	}

	
	/**
	 * 解散队伍
	 * @param teamid
	 * @return
	 */
	public static int dismissTeam(long teamid) {
		//删除分表中队伍成员
		memberDao.dismissTeam(teamid);
		//删除总表中队伍成员
		memberAllDao.dismissTeam(teamid);
		//删除队伍记录
		return teamDao.delTeam(teamid);
	}
	
	
	/**
	 * 设置新的队长
	 * @param teamid
	 * @param newUid
	 * @return
	 */
	public static int setNewTeamLeader(long teamid,long newUid) {
		return teamDao.setNewTeamLeader(teamid, newUid);
	}

	
	/**
	 * 加入队伍
	 * @param bean
	 * @return
	 */
	public static int joinTeam(ChatTeamMember bean) {
		int res = memberDao.joinTeam(bean);
		if(res != -1) {
			res = memberAllDao.joinTeam(bean);
		}
		return res;
	}
	
	/**
	 * 退出队伍
	 * @param teamid
	 * @param uid
	 * @return
	 */
	public static int quitTeam(long teamid,long uid) {
		int res = memberDao.quitTeam(teamid, uid);
		if(res != -1) {
			res = memberAllDao.quitTeam(teamid, uid);
		}
		return res;
	}

	
	/**
	 * 获取队伍成员列表
	 * @param teamid
	 * @return
	 */
	public static List<ChatTeamMember> getMemberList(long teamid) {
		return memberDao.getMemberList(teamid);
	}
	

	/**
	 * 获取队伍成员数量
	 * @param teamid
	 * @return
	 */
	public static int getTeamMemberNum(long teamid) {
		return memberDao.getTeamMemberNum(teamid);
	}

	
	/**
	 * 是否是队伍成员
	 * @param teamid
	 * @param uid
	 * @return
	 */
	public static boolean isTeamMember(long teamid,long uid) {
		return memberDao.isTeamMember(teamid, uid);
	}


	/**
	 * 获取需要刷新的队伍列表
	 * @return
	 */
	public static List<TeamFreshBean> getTeamFreshList() {
		return  teamDao.getFreshList();
	}

	/**
	 * 添加队伍刷新标记记录
	 * 主要用于线程刷新使用
	 * @param gameid
	 * @param teamid
	 * @return
	 */
	public static int addFreshRecord(int gameid,long teamid) {
		return teamDao.addFreshRecord(gameid, teamid);
	}
	
	
	/**
	 * 清理刷新内存表中过期数据
	 * @param freshInterval
	 */
	public static void clearOutOfDateFreshList(int freshInterval) {
		teamDao.clearOutOfDateFreshList(freshInterval);
	}
	
	/**
	 * 获取临时用户信息
	 * @param uid
	 * @return
	 */
	public static ChatTeamTempMember getTempMember(long uid,long teamid) {
		ChatTeamTempMember member = null;
		String key = new StringBuffer("").append(uid).append("-").append(teamid).toString();
		if(tempMemberMap != null && tempMemberMap.containsKey(key)) {
			ChatTeamTempMemberBo bean =  tempMemberMap.get(key);
			if(System.currentTimeMillis() - bean.getLastFreshTime() > 10*1000) {
				member = tempMemberDao.getTempMember(uid,teamid);
				bean.setBean(member);
				bean.setLastFreshTime(System.currentTimeMillis());
				tempMemberMap.put(key, bean);
			} else {
				member = bean.getBean(); 
			}
		} else {
			member = tempMemberDao.getTempMember(uid,teamid);
			ChatTeamTempMemberBo bean = new ChatTeamTempMemberBo();
			bean.setBean(member);
			bean.setLastFreshTime(System.currentTimeMillis());
			tempMemberMap.put(key, bean);
		}
		return member;
	}
	
	/**
	 * 直接读取数据库的信息
	 * @param uid
	 * @param teamid
	 * @return
	 */
	public static ChatTeamTempMember getTempMemberWithNoCache(long uid,long teamid) {
		return tempMemberDao.getTempMember(uid,teamid);
	}

	
	/**
	 * 给队伍队长查看的申请列表
	 * @param teamid
	 * @return
	 */
	public static List<ChatTeamTempMember> getApplyList(long teamid) {
		return tempMemberDao.getApplyList(teamid);
	}
	
	
	/**
	 * 获取邀请列表
	 */
	public static List<ChatTeamTempMember> getInviteList(long uid) {
		return tempMemberDao.getInviteList(uid);
	}
	
	/**
	 * 添加临时用户
	 * @param bean
	 * @return
	 */
	public static int addTempMember(ChatTeamTempMember bean) {
		return tempMemberDao.addTempMember(bean);
	}
	
	/**
	 * 正式加入后需要更新临时用户记录状态
	 * @param uid
	 * @param teamid
	 * @param status
	 * @return
	 */
	public static int updateStatus(long uid, long teamid, int status) {
		return tempMemberDao.updateStatus(uid, teamid, status);
	}
	
	/**
	 * 按照用户ID获取临时用户列表信息
	 * 判断用户是否有新的邀请
	 * @return
	 */
	public static ConcurrentHashMap<Long,Long> getTeamTempListByUID() {
		return tempMemberDao.getTeamTempListByUID();
	}
	
	/**
	 * 按照队伍ID获取临时用户列表信息
	 * 判断是否有新的申请
	 * @return
	 */
	public static ConcurrentHashMap<Long,Long> getTeamTempListByTeamID() {
		return tempMemberDao.getTeamTempListByTeamID();
	}

}
