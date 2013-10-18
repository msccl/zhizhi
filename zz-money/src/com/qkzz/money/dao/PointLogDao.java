package com.qkzz.money.dao;

import java.util.List;

import com.qkzz.money.bean.PointLogBean;


public interface PointLogDao {

	/**
	 * 添加log
	 * @param log
	 * @return
	 */
	public int add(PointLogBean log);
	
	/**
	 * 用户log最大数量
	 * @param uid
	 * @return
	 */
	public int logMaxCount(long uid);
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<PointLogBean> getPointLogList(long uid,int start,int size);

}
