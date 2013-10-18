package com.qkzz.online.dao;

import com.qkzz.online.bean.Queue;


public interface ByGameDao {
	
	boolean add(Queue obj);	
	boolean isExist(Queue obj);	
	boolean update(Queue obj);
		
}
