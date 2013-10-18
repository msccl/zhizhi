package com.qkzz.user.dao;

import com.qkzz.user.bean.Member;


public interface MemberDao {
	
	Member getById(long id);
	
	Member getByName(String name);
	
	Member getByLogin(String name, String password);

	int update(Member obj);
	
	int add(Member obj);
	
	boolean isValidateName(String name);
	
	String getName(long id);
	
	long getIDByName(String name);
	
	/**
	 * 获取外站用户注册信息
	 * @param domainuid ：外站用户ID
	 * @param domain ： 外站域名，例如3366.com
	 * @return
	 */
	Member getDoaminUser(String domainuid,String domain);
	
	/**
	 * 判断用户是否已经设置密码
	 * @param uid ：zz用户ID
	 * @return
	 */
	boolean hasSetPassword(long uid);
	
	/**
	 * 将外站用户添加到zz数据库
	 * @param name
	 * @param domainUID
	 * @param domain
	 * @return
	 */
	int addDomainUser(String name,String domainUID,String domain);
}
