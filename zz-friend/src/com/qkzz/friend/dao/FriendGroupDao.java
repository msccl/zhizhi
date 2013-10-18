package com.qkzz.friend.dao;

import java.util.List;

import com.qkzz.friend.bean.FriendGroup;

public interface FriendGroupDao {

	/**
	 * 添加分组
	 * @param uid
	 * @param groupName
	 * @return
	 */
	int addGroup(long uid,String groupName);
	
	/**
	 * 增加默认分组
	 * 可以在用户注册的时候添加，也可以在读取列表的时候添加
	 * @param uid
	 * @return
	 */
	int addDefaultGroup(long uid,String groupName);

	/**
	 * 获取默认分组ID
	 * @param uid
	 * @return
	 */
	long getDefaultGroupID(long uid);
	
	/**
	 * 删除好友分组
	 * @param uid
	 * @param groupID
	 * @return
	 */
	int delGroup(long uid,long groupID);
	
	/**
	 * 编辑分组
	 * @param uid
	 * @param groupid
	 * @param newGroupName
	 * @return
	 */
	int editGroup(long uid,long groupid,String newGroupName);
	
	/**
	 * 获取用户好友分组列表
	 * @param uid
	 * @return
	 */
	List<FriendGroup> getGroupList(long uid);
	
	/**
	 * 获取分组信息
	 * @param groupid
	 * @return
	 */
	FriendGroup getGroup(long uid,long groupid);
	
	/**
	 * 获取用户最新添加的分组ID
	 * @param uid
	 * @return
	 */
	long getLastInsertGroupID(long uid);
	
	/**
	 * 分组名称是否存在
	 * @param uid
	 * @param groupName
	 * @return
	 */
	boolean isGroupNameExist(long uid,String groupName);
	
	/**
	 * 是否为我的好友分组
	 * @param uid
	 * @param groupid
	 * @return
	 */
	boolean isMyFriendGroup(long uid,long groupid);
	
	/**
	 * 获取用户已经添加的好友分组数量
	 * @param uid
	 * @return
	 */
	int getTotalGroupNum(long uid);
}
