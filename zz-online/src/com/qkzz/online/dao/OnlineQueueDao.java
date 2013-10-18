package com.qkzz.online.dao;

import com.qkzz.online.bean.OnlineQueue;

public interface OnlineQueueDao {
	
	/**
	 * 添加到队列中
	 * @param bean
	 * @return
	 */
	int addToQueue(OnlineQueue bean);
}
