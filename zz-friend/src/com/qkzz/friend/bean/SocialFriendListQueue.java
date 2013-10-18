package com.qkzz.friend.bean;

public class SocialFriendListQueue {
	private long id;
	private long uid;//用户在吱吱的ID
	private String suid;//用户在社交网站编号
	private int gameid;//激活时所在游戏ID
	private String domain;//激活时所在社交网站域名
	private String frdlist;//所在社交网站好友列表内容，等待处理
	private String attime;//获取时间
	private int status;//状态 0：未处理 1：已处理
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getSuid() {
		return suid;
	}
	public void setSuid(String suid) {
		this.suid = suid;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getFrdlist() {
		return frdlist;
	}
	public void setFrdlist(String frdlist) {
		this.frdlist = frdlist;
	}
	public String getAttime() {
		return attime;
	}
	public void setAttime(String attime) {
		this.attime = attime;
	}
	
}
