package com.qkzz.im.bean;

public class MailAttach {
	private int id;
	private long senduid;//发送者ID
	private long receiveuid;//接收者ID
	private int gameid;//游戏ID
	private String attime;//发送时间
	private int status;//附件状态
	private String attach;//客户端构造的json格式字符串
	private int checktype;//附件检测类型 0：默认检查  1：不检查，主要用于游戏中道具的中转
	
	public int getChecktype() {
		return checktype;
	}
	public void setChecktype(int checktype) {
		this.checktype = checktype;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getSenduid() {
		return senduid;
	}
	public void setSenduid(long senduid) {
		this.senduid = senduid;
	}
	public long getReceiveuid() {
		return receiveuid;
	}
	public void setReceiveuid(long receiveuid) {
		this.receiveuid = receiveuid;
	}
	public String getAttime() {
		return attime;
	}
	public void setAttime(String attime) {
		this.attime = attime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	
}
