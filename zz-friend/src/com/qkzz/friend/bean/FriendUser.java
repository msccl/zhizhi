package com.qkzz.friend.bean;


public class FriendUser {

	private long uid;
	private long fuid;
	private String fname;
	private String remark;//好友姓名备注，改成自己熟悉的名字
	private long groupid;//好友分组ID，如果是默认组，值为0
//	private String groupname;//好友分组名称
	private String createtime;
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getFuid() {
		return fuid;
	}
	public void setFuid(long fuid) {
		this.fuid = fuid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public long getGroupid() {
		return groupid;
	}
	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}
//	public String getGroupname() {
//		return groupname;
//	}
//	public void setGroupname(String groupname) {
//		this.groupname = groupname;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
		
}
