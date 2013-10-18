package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.ChatTeamMember;

public interface ChatTeamMemberDao {

	/**
	 * 加入队伍
	 * @param bean
	 * @return
	 */
	int joinTeam(ChatTeamMember bean);
	
	/**
	 * 退出队伍
	 * @param teamid
	 * @param uid
	 * @return
	 */
	int quitTeam(long teamid,long uid);
	
	
	/**
	 * 解散队伍时将队伍中的成员记录全部删除
	 * @param teamid
	 * @return
	 */
	int dismissTeam(long teamid);
	
	/**
	 * 获取队伍成员数量
	 * @param teamid
	 * @return
	 */
	int getTeamMemberNum(long teamid);
	
	/**
	 * 是否是队伍成员
	 * @param teamid
	 * @param uid
	 * @return
	 */
	boolean isTeamMember(long teamid,long uid);
	
	/**
	 * 获取队伍成员列表
	 * @param teamid
	 * @return
	 */
	List<ChatTeamMember> getMemberList(long teamid);
	
}
