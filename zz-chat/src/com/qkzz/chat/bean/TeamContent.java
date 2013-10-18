package com.qkzz.chat.bean;

public class TeamContent {
	private int channelid;
	private long teamid;//频道ID
	private long fromuid;//发言用户UID
	private String fromname;//用户昵称
	private long destuid;//目标用户UID
	private String destname;//目标用户昵称
	private String content;//内容
	private int gameid;//游戏ID
	private long attime;//发言时间

	public long getFromuid() {
		return fromuid;
	}
	public void setFromuid(long fromuid) {
		this.fromuid = fromuid;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	public long getDestuid() {
		return destuid;
	}
	public void setDestuid(long destuid) {
		this.destuid = destuid;
	}
	public String getDestname() {
		return destname;
	}
	public void setDestname(String destname) {
		this.destname = destname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getAttime() {
		return attime;
	}
	public void setAttime(long attime) {
		this.attime = attime;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	public int getChannelid() {
		return channelid;
	}
	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}
	
}
