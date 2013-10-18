package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.ChatTeam;
import com.qkzz.chat.bean.TeamFreshBean;

public interface ChatTeamDao {

	/**
	 * 通过队伍ID获取队伍信息
	 * @param teamid
	 * @return
	 */
	ChatTeam getTeam(long teamid);
	
	
	/**
	 * 创建队伍
	 * @param bean
	 * @return
	 */
	int addTeam(ChatTeam bean);
	
	/**
	 * 删除队伍记录
	 * @param teamid
	 * @return
	 */
	int delTeam(long teamid);
	
	
	/**
	 * 获取用户创建的队伍ID
	 * @param uid
	 * @return
	 */
	long getLastInsertID(long uid);
	
	/**
	 * 设置新的队长
	 * @param teamid
	 * @param newUid
	 * @return
	 */
	int setNewTeamLeader(long teamid,long newUid);
	
	/**
	 * 通过游戏ID获取频道ID
	 * @param gameid
	 * @return
	 */
	long getTeamIDByGameID(int gameid);
	
	/**
	 * 从内存表中获取队伍刷新列表
	 * @return
	 */
	List<TeamFreshBean> getFreshList();
	
	/**
	 * 添加队伍刷新标记记录
	 * 主要用于线程刷新使用
	 * @param gameid
	 * @param teamid
	 * @return
	 */
	int addFreshRecord(int gameid,long teamid);
	
	/**
	 * 清理刷新内存表中过期数据
	 * @param freshInterval
	 */
	void clearOutOfDateFreshList(int freshInterval);
}
