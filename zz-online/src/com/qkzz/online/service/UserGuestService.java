package com.qkzz.online.service;

import com.qkzz.online.bean.UserGuest;
import com.qkzz.online.dao.UserGuestDao;
import com.qkzz.online.dao.impl.UserGuestDaoImpl;



public class UserGuestService {

	public static UserGuestDao dao = new UserGuestDaoImpl();

	public static long getById(){
		return dao.getById();
	}
	
	public static int add(UserGuest obj){
		return dao.add(obj);
	}
	
	public static int delete(long id){
		return dao.delete(id);
	}

	
}
