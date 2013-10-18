package com.qkzz.im.bean;

public class Inbox {

	private int id;
	private long senduid;
	private long receiveuid;
	private String title;
	private String content;
	private String createtime;
	private int isdel;
	private String htmlcode;
	private int status;
	private int type;
	private String sendname;
    private int attachid;
	
	public int getAttachid() {
		return attachid;
	}
	public void setAttachid(int attachid) {
		this.attachid = attachid;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getHtmlcode() {
		return htmlcode;
	}
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSendname() {
		return sendname;
	}
	public void setSendname(String sendname) {
		this.sendname = sendname;
	}

	
	
	
}
