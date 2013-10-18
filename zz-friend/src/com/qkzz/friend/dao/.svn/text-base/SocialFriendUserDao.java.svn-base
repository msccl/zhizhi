package com.qkzz.friend.dao;

import java.util.List;

import com.qkzz.friend.bean.SocialFriendUser;
import com.qkzz.friend.bean.SocialFriendUserListInfo;

public interface SocialFriendUserDao {

	SocialFriendUser getSocialFriendUser(long uid, String fuid,String domain);
	
	/**
	 * 获取好友列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	List<SocialFriendUserListInfo> getList(long uid,String domain);


	/**
	 * 增加好友
	 * @param obj
	 * @return
	 */
	int add(SocialFriendUser obj);
	
	/**
	 * 删除好友
	 * @param uid
	 * @param fuid
	 * @return
	 */
	int delete(long uid, String fuid,String domain);
	
}
