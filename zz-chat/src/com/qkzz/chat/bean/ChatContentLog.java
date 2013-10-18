package com.qkzz.chat.bean;

/**
 * 聊天内容日志表
 * @author Administrator
 *
 */
public class ChatContentLog {
	private int channelid;//频道ID
	private long fromuid;//发送者ID
	private String fromname;//发送者昵称
	private long destuid;//接受者ID
	private String destname;//接受者昵称
	private String content;//内容
	private int gameid;//游戏ID
	private long attime;//发言时间
	private String ip;//发言用户IP地址
	
	
	public int getChannelid() {
		return channelid;
	}
	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}
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
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public long getAttime() {
		return attime;
	}
	public void setAttime(long attime) {
		this.attime = attime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
