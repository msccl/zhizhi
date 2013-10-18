package com.qkzz.chat.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.qkzz.chat.bean.ChatTeamTempMember;

public interface ChatTeamTempMemberDao {

	int addTempMember(ChatTeamTempMember bean);
	
	int updateStatus(long uid,long teamid,int status);
	
	List<ChatTeamTempMember> getApplyList(long teamid);
	
	List<ChatTeamTempMember> getInviteList(long uid);

	ChatTeamTempMember getTempMember(long uid,long teamid);
	
	ConcurrentHashMap<Long,Long> getTeamTempListByUID();
	
	ConcurrentHashMap<Long,Long> getTeamTempListByTeamID();
}
