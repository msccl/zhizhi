package com.qkzz.chat.bean;

import java.util.List;

/**
 * 当前游戏聊天内容返回bean
 * @author Administrator
 *
 */
public class GameChannelRetBean {

	private List<Content> channelList;//频道内容
	private int freshinterval;//刷新时间
	private long lasttime;//最后获取时间
	private long channelLastIndex;//频道最后发言的位置点
	private long clientTime;//客户端请求时间
	private String requestChannelid;//请求时的频道ID

	public String getRequestChannelid() {
		return requestChannelid;
	}
	public void setRequestChannelid(String requestChannelid) {
		this.requestChannelid = requestChannelid;
	}
	public long getLasttime() {
		return lasttime;
	}
	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}
	public long getChannelLastIndex() {
		return channelLastIndex;
	}
	public void setChannelLastIndex(long channelLastIndex) {
		this.channelLastIndex = channelLastIndex;
	}
	public long getClientTime() {
		return clientTime;
	}
	public void setClientTime(long clientTime) {
		this.clientTime = clientTime;
	}
	public List<Content> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<Content> channelList) {
		this.channelList = channelList;
	}
	public int getFreshinterval() {
		return freshinterval;
	}
	public void setFreshinterval(int freshinterval) {
		this.freshinterval = freshinterval;
	}
	
}
