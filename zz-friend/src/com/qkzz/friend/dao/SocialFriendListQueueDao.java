package com.qkzz.friend.dao;

import java.util.List;

import com.qkzz.friend.bean.SocialFriendListQueue;

public interface SocialFriendListQueueDao {

	/**
	 * 添加传递的好友列表信息，等待后台处理
	 * @param bean
	 * @return
	 */
	int add(SocialFriendListQueue bean);
	
	/**
	 * 处理完毕后修改记录状态
	 * @param id
	 * @param status
	 * @return
	 */
	int chgStatus(long id,int status);

	/**
	 * 获取未处理的记录列表
	 * @param number
	 * @return
	 */
	List<SocialFriendListQueue> getList(int number);
	
}
