package com.qkzz.user.dao;

import java.util.List;

import com.qkzz.user.bean.UserMood;

public interface UserMoodDao {
	
	int add(long uid, String mood);
	
	int delete(long uid, long id);
	
	int countByList(long uid);
	
	List<UserMood> getByList(long uid, long first, long max);
	
}
