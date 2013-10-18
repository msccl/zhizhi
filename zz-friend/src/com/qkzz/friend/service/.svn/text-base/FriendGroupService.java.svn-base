package com.qkzz.friend.service;

import java.util.List;

import com.qkzz.friend.bean.FriendGroup;
import com.qkzz.friend.dao.FriendGroupDao;
import com.qkzz.friend.dao.impl.FriendGroupDaoImpl;

public class FriendGroupService {

	private static FriendGroupDao dao = new FriendGroupDaoImpl();
	
	/**
	 * 添加分组
	 * @param uid
	 * @param groupName
	 * @return
	 */
	public static int addGroup(long uid,String groupName) {
		return dao.addGroup(uid, groupName);
	}
	
	/**
	 * 增加默认分组
	 * 可以在用户注册的时候添加，也可以在读取列表的时候添加
	 * @param uid
	 * @return
	 */
	public static int addDefaultGroup(long uid,String groupName) {
		return dao.addDefaultGroup(uid, groupName);
	}
	
	/**
	 * 删除好友分组
	 * @param uid
	 * @param groupID
	 * @return
	 */
	public static int delGroup(long uid,long groupID) {
		return dao.delGroup(uid, groupID);
	}
	
	/**
	 * 获取默认分组ID
	 * @param uid
	 * @return
	 */
	public static long getDefaultGroupID(long uid) {
		return dao.getDefaultGroupID(uid);
	}
	
	/**
	 * 编辑分组
	 * @param uid
	 * @param groupid
	 * @param newGroupName
	 * @return
	 */
	public static int editGroup(long uid,long groupid,String newGroupName) {
		return dao.editGroup(uid, groupid, newGroupName);
	}
	
	/**
	 * 获取用户好友分组列表
	 * @param uid
	 * @return
	 */
	public static List<FriendGroup> getGroupList(long uid) {
		List<FriendGroup> list = dao.getGroupList(uid);
		if(list == null || list.size() == 0) {
			//增加默认分组
			dao.addDefaultGroup(uid, "好友");
			list = dao.getGroupList(uid);
		} 
		return list;
	}
	
	/**
	 * 获取分组信息
	 * @param groupid
	 * @return
	 */
	public static FriendGroup getGroup(long uid,long groupid) {
		return dao.getGroup(uid, groupid);
	}
	
	/**
	 * 获取用户最新添加的分组ID
	 * @param uid
	 * @return
	 */
	public static long getLastInsertGroupID(long uid) {
		return dao.getLastInsertGroupID(uid);
	}
	
	/**
	 * 分组名称是否存在
	 * @param uid
	 * @param groupName
	 * @return
	 */
	public static boolean isGroupNameExist(long uid,String groupName) {
		return dao.isGroupNameExist(uid, groupName);
	}

	
	/**
	 * 是否为我的好友分组
	 * @param uid
	 * @param groupid
	 * @return
	 */
	public static boolean isMyFriendGroup(long uid,long groupid) {
		return dao.isMyFriendGroup(uid, groupid);
	}
	
	/**
	 * 获取用户已经添加的好友分组数量
	 * @param uid
	 * @return
	 */
	public static int getTotalGroupNum(long uid) {
		return dao.getTotalGroupNum(uid);
	}
}
