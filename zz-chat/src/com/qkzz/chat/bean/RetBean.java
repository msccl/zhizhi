package com.qkzz.chat.bean;

import java.util.List;

public class RetBean {

	private List<Content> list;
	private int freshinterval;
	private Long lasttime;
	public List<Content> getList() {
		return list;
	}
	public void setList(List<Content> list) {
		this.list = list;
	}
	public long getLasttime() {
		return lasttime;
	}
	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}
	public int getFreshinterval() {
		return freshinterval;
	}
	public void setFreshinterval(int freshinterval) {
		this.freshinterval = freshinterval;
	}
}
