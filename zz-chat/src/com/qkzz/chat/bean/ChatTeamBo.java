package com.qkzz.chat.bean;

/**
 * 主要用于队伍信息缓存
 * @author Administrator
 *
 */
public class ChatTeamBo {
	ChatTeam ct;
	long lastFreshTime;
	public ChatTeam getCt() {
		return ct;
	}
	public void setCt(ChatTeam ct) {
		this.ct = ct;
	}
	public long getLastFreshTime() {
		return lastFreshTime;
	}
	public void setLastFreshTime(long lastFreshTime) {
		this.lastFreshTime = lastFreshTime;
	}
	
}
