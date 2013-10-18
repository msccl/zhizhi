package com.qkzz.chat.bean;

import java.util.List;

/**
 * 用于频道返回值
 * @author dell
 *
 */
public class RetTeamListBean {

	private List<Content> list;
	private long teamid;//用户所在队伍id，如果没有加入队伍，显示为0
	private String lasttime;
	
	public List<Content> getList() {
		return list;
	}
	public void setList(List<Content> list) {
		this.list = list;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	
}
