package com.qkzz.chat.bean;

public class PrivateContent {
//	private String keystr;//最小UID-最大UID
	private long channelid;//发言时所在的频道ID
	private long fromuid;//发言者UID
	private String fromname;//发言用户昵称
	private long destuid;//目标用户UID
	private String destname;//目标用户昵称
	private String content;//内容
	private int tktype;//类型 0：普通内容 1：系统(可带链接)
	private long attime;//发言时间
	private int gameid;
	
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	//	public String getKeystr() {
//		return keystr;
//	}
//	public void setKeystr(String keystr) {
//		this.keystr = keystr;
//	}
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
	public int getTktype() {
		return tktype;
	}
	public void setTktype(int tktype) {
		this.tktype = tktype;
	}
	public long getAttime() {
		return attime;
	}
	public void setAttime(long attime) {
		this.attime = attime;
	}
	public long getChannelid() {
		return channelid;
	}
	public void setChannelid(long channelid) {
		this.channelid = channelid;
	}
	
}
