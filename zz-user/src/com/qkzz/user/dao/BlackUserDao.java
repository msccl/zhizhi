package com.qkzz.user.dao;

import java.util.List;

import com.qkzz.user.bean.BlackUser;

public interface BlackUserDao {

	/**
	 * 添加屏蔽禁言记录
	 * @param bean
	 * @return
	 */
	int addBlack(BlackUser bean);
	
	/**
	 * 删除加黑记录
	 * @param uid
	 * @param type
	 * @return
	 */
	int delBlack(int uid);


	/**
	 * 获取全部加黑名单
	 * @return
	 */
	List<BlackUser> getList();
	
	/**
	 * 删除过期数据
	 * @return
	 */
	int clearOutOfDate();

}
