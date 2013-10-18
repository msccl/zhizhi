package com.qkzz.friend.service;

import java.util.List;

import com.qkzz.friend.bean.SocialFriendListQueue;
import com.qkzz.friend.bean.SocialFriendUser;
import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.dao.SocialFriendListQueueDao;
import com.qkzz.friend.dao.SocialFriendUserDao;
import com.qkzz.friend.dao.impl.SocialFriendListQueueDaoImpl;
import com.qkzz.friend.dao.impl.SocialFriendUserDaoImpl;

public class SocialFriendUserService {

	private static SocialFriendUserDao dao = new SocialFriendUserDaoImpl();
	private static SocialFriendListQueueDao listdao = new SocialFriendListQueueDaoImpl();
	
	/**
	 * 获取社交好友信息
	 * @param uid
	 * @param fuid
	 * @param domain
	 * @return
	 */
	public static SocialFriendUser getSocialFriendUser(long uid, String fuid,String domain) {
		return dao.getSocialFriendUser(uid, fuid, domain);
	}
	
	
	/**
	 * 获取好友列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<SocialFriendUserListInfo> getList(long uid,String domain) {
		return dao.getList(uid, domain);
	}


	/**
	 * 增加好友
	 * @param obj
	 * @return
	 */
	public static int add(SocialFriendUser obj) {
		return dao.add(obj);
	}
	
	/**
	 * 删除好友
	 * @param uid
	 * @param fuid
	 * @return
	 */
	public static int delete(long uid, String fuid,String domain) {
		return dao.delete(uid, fuid, domain);
	}
	
	
	/**
	 * 添加传递的好友列表信息，等待后台处理
	 * @param bean
	 * @return
	 */
	public static int addSocialFriendListQueue(SocialFriendListQueue bean) {
		return listdao.add(bean);
	}
	
	/**
	 * 处理完毕后修改记录状态
	 * @param id
	 * @param status
	 * @return
	 */
	public static int changeListQueueStatus(long id,int status) {
		return listdao.chgStatus(id, status);
	}

	/**
	 * 获取未处理的记录列表
	 * @param number
	 * @return
	 */
	public static List<SocialFriendListQueue> getQueueList(int number) {
		return listdao.getList(number);
	}


}
