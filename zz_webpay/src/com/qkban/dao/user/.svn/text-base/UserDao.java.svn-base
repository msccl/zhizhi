package com.qkban.dao.user;

import com.qkban.bean.user.User;



public interface UserDao {
	
	User getById(int id);

	int save(User obj);
	
	int update(int id, String ip, String lasttime);
	
	int updateMobile(int id, String mobile);
	
	int updatePassword(int id, String password);
	
	int updateAnswer(int id, String answer);
	
	int updateLock(int id);
		
	User getByLogin(String name, String password);
	
	boolean isValidateMobile(String mobile);
	
	boolean isValidateName(String name);
	
	User getByName(String name);
	
}
