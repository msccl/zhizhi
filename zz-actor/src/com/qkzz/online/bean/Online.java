package com.qkzz.online.bean;

public class Online {
	
	private long uid;				/* 用户ID */
	private String name;			/* 昵称 */
	private long lasttime;			/* 最后时间 */
	private String lasturl;			/* 游戏地址 */
	private int lastgame;			/* 游戏ID */
	private String sign;			/* 用户签名 */
	
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	

}
