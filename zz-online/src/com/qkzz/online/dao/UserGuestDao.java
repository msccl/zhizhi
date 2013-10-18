package com.qkzz.online.dao;

import com.qkzz.online.bean.UserGuest;



public interface UserGuestDao {
	
	long getById();
	
	int add(UserGuest obj);
	
	int delete(long id);
	
}
