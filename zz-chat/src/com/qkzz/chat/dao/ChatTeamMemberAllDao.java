package com.qkzz.chat.dao;

import com.qkzz.chat.bean.ChatTeamMember;

public interface ChatTeamMemberAllDao {

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
	 * 通过用户UID获取队伍信息
	 * @param uid
	 * @return
	 */
	long getTeamidByUID(long uid);

}
