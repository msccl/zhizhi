package com.qkzz.friend.bean;

/**
 * 使用吱吱功能的游戏激活记录
 * @author Administrator
 *
 */
public class GameActiveLog {
	private long uid;//用户在吱吱的ID
	private String suid;//用户在社交网站编号
	private String sname;//用户在社交网站名称
	private int gameid;//激活时所在游戏ID
	private String domain;//激活时所在社交网站域名
	private String attime;//激活时间
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
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
	public String getAttime() {
		return attime;
	}
	public void setAttime(String attime) {
		this.attime = attime;
	}
	
}
