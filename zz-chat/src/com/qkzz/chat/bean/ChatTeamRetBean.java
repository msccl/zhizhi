package com.qkzz.chat.bean;

import java.util.List;

/**
 * 获取队伍信息时，同时返回队伍列表
 * @author Administrator
 *
 */
public class ChatTeamRetBean {
	ChatTeam team;
	List<ChatTeamMember> list;
	public ChatTeam getTeam() {
		return team;
	}
	public void setTeam(ChatTeam team) {
		this.team = team;
	}
	public List<ChatTeamMember> getList() {
		return list;
	}
	public void setList(List<ChatTeamMember> list) {
		this.list = list;
	}
	
}
