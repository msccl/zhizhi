package com.qkzz.friend.bean;

/**
 * 用于好友列表中显示信息
 * @author dell
 *
 */
public class FriendUserListInfo {

    private long uid;
    private String name;
	private int sex;
	private int isonline;//是否在线 0：否  1：是
	private long groupid;
	private String mood;//心情
	private int gameid;//正在玩的游戏id
	private String gamename;//正在玩的游戏名称
	private String gameurl;//正在玩的游戏地址
	private String faceurl;//用户头像对应的地址
	private String remark;//好友用户名备注
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFaceurl() {
		return faceurl;
	}
	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}
	public String getGameurl() {
		return gameurl;
	}
	public void setGameurl(String gameurl) {
		this.gameurl = gameurl;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getIsonline() {
		return isonline;
	}
	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}
	public long getGroupid() {
		return groupid;
	}
	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

    
}
