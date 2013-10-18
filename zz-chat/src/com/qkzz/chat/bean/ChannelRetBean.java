package com.qkzz.chat.bean;

import java.util.List;

/**
 * 普通频道，包括综合频道的聊天内容返回bean
 * @author Administrator
 *
 */
public class ChannelRetBean {

	private List<Content> channelList;//频道内容
//	private List<Content> privateList;//私聊内容
	private int freshinterval;//刷新时间
	private Long lasttime;//最后获取时间
	private String channelLastIndex;//频道最后发言的位置点
	private Long clientTime;//客户端请求时间
	private String requestChannelid;//请求时的频道ID
	private long teamid = 0;//用户所在队伍id，如果没有加入队伍，显示为0

	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	public String getRequestChannelid() {
		return requestChannelid;
	}
	public void setRequestChannelid(String requestChannelid) {
		this.requestChannelid = requestChannelid;
	}
	public Long getClientTime() {
		return clientTime;
	}
	public void setClientTime(Long clientTime) {
		this.clientTime = clientTime;
	}
	public List<Content> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<Content> channelList) {
		this.channelList = channelList;
	}
//	public List<Content> getPrivateList() {
//		return privateList;
//	}
//	public void setPrivateList(List<Content> privateList) {
//		this.privateList = privateList;
//	}
	public int getFreshinterval() {
		return freshinterval;
	}
	public void setFreshinterval(int freshinterval) {
		this.freshinterval = freshinterval;
	}
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long lasttime) {
		this.lasttime = lasttime;
	}
	public String getChannelLastIndex() {
		return channelLastIndex;
	}
	public void setChannelLastIndex(String channelLastIndex) {
		this.channelLastIndex = channelLastIndex;
	}
	
}
