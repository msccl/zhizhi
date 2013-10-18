package com.qkzz.im.service;

import java.util.List;

import com.qkzz.common.GetDate;
import com.qkzz.im.bean.Inbox;
import com.qkzz.im.dao.InboxDao;
import com.qkzz.im.dao.impl.InboxDaoImpl;


public class InboxService {
	
	private static InboxDao dao = new InboxDaoImpl();
	
	/**
	 * 根据UID获取用户新内线总数
	 * @param uid
	 * @return
	 */
	public static int countByStatus(long uid){
		return dao.countByStatus(uid);
	}
	
	/**
	 * 根据UID获取用户新内线列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<Inbox> getByStatus(long uid, int first, int max){
		return dao.getByStatus(uid, first, max);
	}
	
	/**
	 * 根据UID获取用户内线总数(区分:普通内线/系统内线)
	 * @param uid
	 * @param system
	 * @return
	 */
	public static int countByList(long uid, boolean system){
		return dao.countByList(uid, system);
	}
	
	/**
	 * 根据UID获取用户内线列表(区分:普通内线/系统内线)
	 * @param uid
	 * @param system
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<Inbox> getByList(long uid, boolean system, int first, int max){
		return dao.getByList(uid, system, first, max);
	}

	/**
	 * 保存到收件箱
	 * @param obj
	 * @return
	 */
	public static int save(Inbox obj){
		return dao.save(obj);
	}
	
	/**
	 * 根据UID和ID删除相关内线
	 * @param uid
	 * @param id
	 * @return
	 */
	public static int delete(long uid, int id){
		return dao.delete(uid, id);
	}
	
	/**
	 * 根据UID和ID更新内线状态
	 * @param uid
	 * @param id
	 * @return
	 */
	public static int updateStatus(long uid, int id){
		return dao.updateStatus(uid, id);
	}
	
	/**
	 * 根据UID和ID获取INBOX对象
	 * @param uid
	 * @param id
	 * @return
	 */
	public static Inbox getById(long uid, int id){
		return dao.getById(uid, id);
	}
	
	/**
	 * 系统内线
	 * @param title
	 * @param content
	 * @return
	 */
	public static int sendSystemMessage(long uid, String title, String content,int type){
		Inbox obj = new Inbox();
		obj.setSenduid(1000);
		obj.setSendname("系统信息");
		obj.setReceiveuid(uid);
		obj.setTitle(title);
		obj.setHtmlcode(content);
		obj.setType(type);
		obj.setCreatetime(GetDate.getStringDate());
		obj.setContent("");
		obj.setIsdel(0);
		obj.setStatus(0);
		return InboxService.save(obj);
	}
	
}
