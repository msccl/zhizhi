package com.qkzz.user.bean;


public class Member {

    private long id;
    private String name;
	private String password;
	private String mobile;
    private int islock;
	private String domainuid;//社交网站用户名
	private String domain;//社交网站域名
    
    public String getDomainuid() {
		return domainuid;
	}
	public void setDomainuid(String domainuid) {
		this.domainuid = domainuid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getIslock() {
		return islock;
	}
	public void setIslock(int islock) {
		this.islock = islock;
	}
    
    
    
}
