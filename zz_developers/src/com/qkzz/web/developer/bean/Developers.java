package com.qkzz.web.developer.bean;

/**
 * 开发者
 * @author Administrator
 *
 */
public class Developers {
	private int id;
	private String email;//登陆email
	private String password;//登陆密码
	private String name;//开发商名称
	private String intro;//开发商简介
	private int provinceid;//所在省ID
	private int cityid;//所在市ID
	private String address;//地址
	private String members;//开发商成员简介
	private String phone;//电话
	private String logo;//开发商Logo
	private String tags;//开发商标签
	private String grade;//开发商评级
	private String identifier;//开发商识别码
	private String createtime;//创建时间
	private int isverified;//是否通过吱吱审核
	
	public int getIsverified() {
		return isverified;
	}
	public void setIsverified(int isverified) {
		this.isverified = isverified;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
