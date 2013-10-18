package com.qkzz.im.service;

import java.util.List;

import com.qkzz.im.bean.Outbox;
import com.qkzz.im.dao.OutboxDao;
import com.qkzz.im.dao.impl.OutboxDaoImpl;


public class OutboxService {
	
	private static OutboxDao dao = new OutboxDaoImpl();

	/**
	 * 根据UID获取用户发件箱总数
	 * @param uid
	 * @return
	 */
	public static int countByList(long uid){
		return dao.countByList(uid);
	}
	
	/**
	 * 根据UID获取用户发件箱列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<Outbox> getByList(long uid, int first, int max){
		return dao.getByList(uid, first, max);
	}

	/**
	 * 保存到发件箱
	 * @param obj
	 * @return
	 */
	public static int save(Outbox obj){
		return dao.save(obj);
	}
	
	/**
	 * 根据UID和ID删除相关已发信息
	 * @param uid
	 * @param id
	 * @return
	 */
	public static int delete(long uid, int id){
		return dao.delete(uid, id);
	}
	
	/**
	 * 根据UID和ID获取OUTBOX对象
	 * @param uid
	 * @param id
	 * @return
	 */
	public static Outbox getById(long uid, int id){
		return dao.getById(uid, id);
	}
	
}
