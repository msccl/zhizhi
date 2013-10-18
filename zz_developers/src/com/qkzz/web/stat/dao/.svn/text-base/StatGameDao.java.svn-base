package com.qkzz.web.stat.dao;

import java.util.List;

import com.qkzz.web.stat.bean.StatGame;


/**
 * 
 * @author
 */
public interface StatGameDao {
	
	/**
	 * 获取指定日期段的数据总行数
	 * @param gamecode
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	int countByList(String gamecode, String begindate, String enddate);
	
	/**
	 * 获取指定日期段的数据列表
	 * @param gamecode
	 * @param begindate
	 * @param enddate
	 * @param first
	 * @param max
	 * @return
	 */
	List<StatGame> getByList(String gamecode, String begindate, String enddate, int first, int max);
	
	/**
	 * 获取指定日期的game_code列表
	 * @param date
	 * @return
	 */
	List<String> getByGamecodeList(String date);
	
	/**
	 * 获取指定game_code和日期的统计数据
	 * @param gamecode
	 * @param date
	 * @return
	 */
	StatGame getByGamecode(String gamecode, String date);
	
}
