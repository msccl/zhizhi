package com.qkzz.online.dao;

import java.util.List;

import com.qkzz.online.bean.Queue;


public interface QueueDao {
	
	int delete(long id);
	
	long countByList();
	
	List<Queue> getByList(long first, long max);
	
}
