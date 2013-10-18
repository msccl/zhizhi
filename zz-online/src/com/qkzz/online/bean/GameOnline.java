package com.qkzz.online.bean;

public class GameOnline {
	private long uid;
	private String name;
	private long lasttime;
	private String lasturl;
	private int lastgame;
	private int status;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLasttime() {
		return lasttime;
	}
	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}
	public String getLasturl() {
		return lasturl;
	}
	public void setLasturl(String lasturl) {
		this.lasturl = lasturl;
	}
	public int getLastgame() {
		return lastgame;
	}
	public void setLastgame(int lastgame) {
		this.lastgame = lastgame;
	}
	
}
