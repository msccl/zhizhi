package com.qkzz.user.service;

import java.util.List;

import com.qkzz.user.bean.UserMood;
import com.qkzz.user.dao.UserMoodDao;
import com.qkzz.user.dao.impl.UserMoodDaoImpl;


public class UserMoodService {

	public static UserMoodDao dao = new UserMoodDaoImpl();
	
	/**
	 * 添加用户心情
	 * @param uid
	 * @param mood
	 * @return
	 */
	public static int add(long uid, String mood){
		return dao.add(uid, mood);
	}
	
	/**
	 * 删除用户心情
	 * @param uid
	 * @param id
	 * @return
	 */
	public static int delete(long uid, long id){
		return dao.delete(uid, id);
	}
	
	/**
	 * 获取某用户心情总数
	 * @param uid
	 * @return
	 */
	public static int countByList(long uid){
		return dao.countByList(uid);
	}
	
	/**
	 * 获取某用户心情列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<UserMood> getByList(long uid, long first, long max){
		return dao.getByList(uid, first, max);
	}
	
	
	
}
