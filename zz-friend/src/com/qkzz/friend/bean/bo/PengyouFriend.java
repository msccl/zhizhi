package com.qkzz.friend.bean.bo;

public class PengyouFriend {
	private String openid;		//好友QQ号码转化得到的ID
	private String nickname;	//昵称
	private String gender;		//性别
	private String figureurl;	//头像URL
	private String is_vip;		//是否为黄钻用户（true或false）
	private String is_year_vip;	//是否年费黄钻（如果is_vip为false，那is_year_vip一定是false）
	private String vip_level;	//黄钻等级（如果是黄钻用户才返回此字段）
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFigureurl() {
		return figureurl;
	}
	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}
	public String getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}
	public String getIs_year_vip() {
		return is_year_vip;
	}
	public void setIs_year_vip(String is_year_vip) {
		this.is_year_vip = is_year_vip;
	}
	public String getVip_level() {
		return vip_level;
	}
	public void setVip_level(String vip_level) {
		this.vip_level = vip_level;
	}
}
