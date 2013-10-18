package com.qkzz.im.dao;

import java.util.List;

import com.qkzz.im.bean.Inbox;

public interface InboxDao {
	
	int countByStatus(long uid);
	
	List<Inbox> getByStatus(long uid, int first, int max);
	
	int countByList(long uid, boolean system);
	
	List<Inbox> getByList(long uid, boolean system, int first, int max);

	int save(Inbox obj);
	
	int delete(long uid, int id);
	
	int updateStatus(long uid, int id);
	
	Inbox getById(long uid, int id);
	
}
