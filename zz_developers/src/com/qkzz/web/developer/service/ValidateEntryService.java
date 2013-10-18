package com.qkzz.web.developer.service;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.developer.dao.ValidateEntryDao;

public class ValidateEntryService {

	private static ValidateEntryDao dao = ActiveBean.getBean("validateEntryDaoImpl", ValidateEntryDao.class);
	
	public static String getValue(String key){
		return dao.getValue(key);
	}

	public static int save(String key, String value){
		return dao.save(key, value);
	}
	
	public static void deleteTimeOut(){
		dao.deleteTimeOut();
	}
	
	
}
