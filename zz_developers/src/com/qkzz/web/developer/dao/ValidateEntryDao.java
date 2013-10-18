package com.qkzz.web.developer.dao;

public interface ValidateEntryDao {
	
	String getValue(String key);

	int save(String key, String value);
	
	void deleteTimeOut();
	
}
