package com.qkzz.user.dao;

import java.util.List;

import com.qkzz.user.bean.Icon;

public interface IconDao {
	
	Icon getById(int id);
	
	List<Icon> getByList(int first, int max);

}
