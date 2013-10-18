package com.qkzz.chat.bean;

public class Content {

	private int channelid;		//频道ID
//	private long uid;//用户ID
	private int tktype;			//类型 0：普通内容 1：系统(可带链接)
	private long fromuid;		//发送者UID
	private long destuid;		//接收者UID
	private String fromname;	//发送者昵称
	private String destname;	//接收者昵称
	private String content;		//内容
	private long attime;		//发送时间
	private int gameid;			//游戏ID
	private String game;		//游戏名称
	private String gurl;		//game地址
	private int showlink;		//是否显示游戏链接 0:显示 1：不显示
	
	public int getTktype() {
		return tktype;
	}
	public void setTktype(int tktype) {
		this.tktype = tktype;
	}
	public long getFromuid() {
		return fromuid;
	}
	public void setFromuid(long fromuid) {
		this.fromuid = fromuid;
	}
	public long getDestuid() {
		return destuid;
	}
	public void setDestuid(long destuid) {
		this.destuid = destuid;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
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
//	public long getUid() {
//		return uid;
//	}
//	public void setUid(long uid) {
//		this.uid = uid;
//	}
	public int getChannelid() {
		return channelid;
	}
	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getGurl() {
		return gurl;
	}
	public void setGurl(String gurl) {
		this.gurl = gurl;
	}
	public int getShowlink() {
		return showlink;
	}
	public void setShowlink(int showlink) {
		this.showlink = showlink;
	}
}
