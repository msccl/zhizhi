package com.qkzz.web.developer.dao;

import com.qkzz.web.developer.bean.Developers;

public interface DevelopersDao {

	/**
	 * 开发者注册
	 * @param bean
	 * @return
	 */
	public int add(Developers bean);
	
	/**
	 * 登陆信息是否正确
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean isValidate(String email,String password);
	
	/**
	 * 编辑信息
	 * @param bean
	 * @return
	 */
	public int editInfo(Developers bean);
	
	/**
	 * 修改密码
	 * @param developerid
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public int editPassword(int developerid,String newPwd);
	
	/**
	 * 邮箱是否已经存在
	 * @param email
	 * @return
	 */
	public boolean isEmailExist(String email);
	
	/**
	 * 获取用户的登录密码，用于修改密码时新旧密码的对比
	 * @param developerid
	 * @return
	 */
	public String getPassword(int developerid);
	
	
	/**
	 * 验证登陆并返回用户信息
	 * @param email
	 * @param password
	 * @return
	 */
	public Developers getByLogin(String email,String password);
	
	/**
	 * 通过ID获取用户信息
	 * @param id
	 * @return
	 */
	public Developers getByID(int id);
}
