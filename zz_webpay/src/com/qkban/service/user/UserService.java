package com.qkban.service.user;

import com.qkban.bean.user.User;
import com.qkban.dao.user.UserDao;
import com.qkban.util.ActiveBean;


public class UserService {

	private static UserDao dao = ActiveBean.getBean("userDaoImpl", UserDao.class);

	/**
	 * get User
	 */
	public static User getById(int id){
		return dao.getById(id);
	}
	
	/**
	 * save User Object
	 * @param obj
	 * @return
	 */
	public static int save(User obj){
		return dao.save(obj);
	}
	
	/**
	 * update User Last Login IP and time
	 * @param id
	 * @param ip
	 * @param lasttime
	 * @return
	 */
	public static int update(int id, String ip, String lasttime){
		return dao.update(id, ip, lasttime);
	}
	
	/**
	 * update User MOBILE
	 * @param id
	 * @param mobile
	 * @return
	 */
	public static int updateMobile(int id, String mobile){
		return dao.updateMobile(id, mobile);
	}
	
	/**
	 * update User PASSWORD
	 * @param id
	 * @param password
	 * @return
	 */
	public static int updatePassword(int id, String password){
		return dao.updatePassword(id, password);
	}
	
	/**
	 * update User ANSWER
	 * @param id
	 * @param answer
	 * @return
	 */
	public static int updateAnswer(int id, String answer){
		return dao.updateAnswer(id, answer);
	}
	
	/**
	 * Locked User
	 * @param id
	 * @return
	 */
	public static int updateLock(int id){
		return dao.updateLock(id);
	}
	
	/**
	 * User Login
	 * @param username
	 * @param password
	 * @return
	 */
	public static User getByLogin(String name, String password){
		return dao.getByLogin(name, password);
	}
	
	/**
	 * validate MOBILE
	 * @param mobile
	 * @return
	 */
	public static boolean isValidateMobile(String mobile){
		return dao.isValidateMobile(mobile);
	}

	/**
	 * validate name
	 * @param name
	 * @return
	 */
	public static boolean isValidateName(String name){
		return dao.isValidateName(name);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static User getByName(String name){
		return dao.getByName(name);
	}
	
}
