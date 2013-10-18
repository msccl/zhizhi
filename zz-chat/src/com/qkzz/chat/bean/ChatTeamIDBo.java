package com.qkzz.chat.bean;

/**
 * 主要用于队伍ID的缓存
 * @author Administrator
 *
 */
public class ChatTeamIDBo {
	long teamid;
	long lastFreshTime;
	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	public long getLastFreshTime() {
		return lastFreshTime;
	}
	public void setLastFreshTime(long lastFreshTime) {
		this.lastFreshTime = lastFreshTime;
	}
	
}
