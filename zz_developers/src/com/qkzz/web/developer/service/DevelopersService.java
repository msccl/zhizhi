package com.qkzz.web.developer.service;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.dao.DevelopersDao;

public class DevelopersService {

	private static DevelopersDao dao = ActiveBean.getBean("developersDaoImpl", DevelopersDao.class);

	/**
	 * 开发者注册
	 * @param bean
	 * @return
	 */
	public static int add(Developers bean) {
		return dao.add(bean);
	}
	
	/**
	 * 登陆信息是否正确
	 * @param email
	 * @param password
	 * @return
	 */
	public static boolean isValidate(String email,String password) {
		return dao.isValidate(email, password);
	}
	
	/**
	 * 编辑信息
	 * @param bean
	 * @return
	 */
	public static int editInfo(Developers bean) {
		return dao.editInfo(bean);
	}
	
	/**
	 * 修改密码
	 * @param developerid
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public static int editPassword(int developerid,String newPwd) {
		return dao.editPassword(developerid, newPwd);
	}
	
	/**
	 * 邮箱是否已经存在
	 * @param email
	 * @return
	 */
	public static boolean isEmailExist(String email) {
		return dao.isEmailExist(email);
	}
	
	/**
	 * 获取用户的登录密码，用于修改密码时新旧密码的对比
	 * @param developerid
	 * @return
	 */
	public static String getPassword(int developerid) {
		return dao.getPassword(developerid);
	}


	/**
	 * 验证登陆并返回用户信息
	 * @param email
	 * @param password
	 * @return
	 */
	public static Developers getByLogin(String email,String password) {
		return dao.getByLogin(email, password);
	}

	
	/**
	 * 通过ID获取用户信息
	 * @param id
	 * @return
	 */
	public static Developers getByID(int id) {
		return dao.getByID(id);
	}

}
