package com.qkzz.im.dao;

import java.util.List;

import com.qkzz.im.bean.Outbox;

public interface OutboxDao {

	int countByList(long uid);
	
	List<Outbox> getByList(long uid, int first, int max);

	int save(Outbox obj);
	
	int delete(long uid, int id);
	
	Outbox getById(long uid, int id);
	
}
