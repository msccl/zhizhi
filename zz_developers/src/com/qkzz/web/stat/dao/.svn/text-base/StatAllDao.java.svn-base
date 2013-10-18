package com.qkzz.web.stat.dao;

import java.util.List;

import com.qkzz.web.stat.bean.StatAll;


/**
 * 
 * @author
 */
public interface StatAllDao {
	
	/**
	 * 获取指定日期段的数据总行数
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	int countByList(String begindate, String enddate);
	
	/**
	 * 获取指定日期段的数据列表
	 * @param begindate
	 * @param enddate
	 * @param first
	 * @param max
	 * @return
	 */
	List<StatAll> getByList(String begindate, String enddate, int first, int max);
	
}
