package com.qkzz.chat.bean;

public class ChatTeamMember {
	 private long uid;//队伍成员ID
	 private String name;//成员用户名
	 private long teamid;//所属队伍ID
	 private String jointime;//加入时间
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	public String getJointime() {
		return jointime;
	}
	public void setJointime(String jointime) {
		this.jointime = jointime;
	}
	 
}
