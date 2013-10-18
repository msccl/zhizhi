package com.qkzz.friend.bean;

/**
 * 社交好友
 * @author Administrator
 *
 */
public class SocialFriendUser {
	private long uid;//用户编号
	private String fuid;//好友在社交网站编号
	private String fname;//好友在社交网站名称
	private String face;//好友在社交网站的头像
	private int sex;//好友在社交网站的性别
	private String birth;//好友在社交网站的生日
	private String domain;//所在网站域名
	private String createtime;//创建时间    
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
