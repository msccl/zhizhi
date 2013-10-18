package com.qkzz.online.service;

import com.qkzz.online.bean.OnlineQueue;
import com.qkzz.online.dao.OnlineQueueDao;
import com.qkzz.online.dao.impl.OnlineQueueDaoImpl;


public class OnlineQueueService {
	
	private static OnlineQueueDao queuedao = new OnlineQueueDaoImpl();
	
	/**
	 * 添加到在线队列中
	 * @param bean
	 * @return
	 */
	public static int addToQueue(OnlineQueue bean) {
		return queuedao.addToQueue(bean);
	}
	
	

}
