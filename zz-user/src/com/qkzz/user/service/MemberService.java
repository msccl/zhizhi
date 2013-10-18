package com.qkzz.user.service;

import com.qkzz.user.bean.Member;
import com.qkzz.user.dao.MemberDao;
import com.qkzz.user.dao.impl.MemberDaoImpl;


public class MemberService {

	public static MemberDao dao = new MemberDaoImpl();

	public static Member getById(long id){
		return dao.getById(id);
	}
	
	public static Member getByName(String name) {
		return dao.getByName(name);
	}
	
	public static boolean isValidateName(String name){
		return dao.isValidateName(name);
	}
	
	public static Member getByLogin(String name, String password) {
		return dao.getByLogin(name, password);
	}

	public static int update(Member obj) {
		return dao.update(obj);
	}
	
	public static int add(Member obj) {
		return dao.add(obj);
	}
	
	public static String getName(long id) {
		return dao.getName(id);
	}

	public static long getIDByName(String name) {
		return dao.getIDByName(name);
	}
	
	/**
	 * 判断外站用户是否已经是本站用户
	 * @param domainuid ：外站用户ID
	 * @param domain ： 外站域名，例如3366.com
	 * @return
	 */
	public static Member getDoaminUser(String domainuid,String domain) {
		return dao.getDoaminUser(domainuid, domain);
	}
	
	/**
	 * 判断用户是否已经设置密码
	 * @param uid ：zz用户ID
	 * @return
	 */
	public static boolean hasSetPassword(long uid) {
		return dao.hasSetPassword(uid);
	}
	
	/**
	 * 将外站用户添加到zz数据库
	 * @param name
	 * @param domainUID
	 * @param domain
	 * @return
	 */
	public static int addDomainUser(String name,String domainUID,String domain) {
		return dao.addDomainUser(name, domainUID, domain);
	}

}
