package com.qkzz.friend.dao;

import java.util.List;

import com.qkzz.friend.bean.FriendUser;
import com.qkzz.friend.bean.FriendUserListInfo;
import com.qkzz.friend.bean.SocialFriendUserListInfo;

public interface FriendUserDao {
	
	FriendUser getByFriendUser(long uid, long fuid);
	
	int countByList(long uid);
	
	/**
	 * 获取好友列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	List<FriendUser> getByList(long uid, int first, int max);


	/**
	 * 获取好友列表
	 * 完整信息
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	List<FriendUserListInfo> getFriendList(long uid, int first, int max);


	/**
	 * 获取社交好友列表
	 * 完整信息，主要用于社交好友接口
	 * 需要传递游戏ID，用于判断用户的好友是否已经在该游戏中激活
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	List<SocialFriendUserListInfo> getSocialFriendList(long uid, int gameid);

	
	/**
	 * 判断是否为好友
	 * @param uid
	 * @param fuid
	 * @return
	 */
	boolean isFriendUser(long uid, long fuid);
	
	/**
	 * 增加好友
	 * @param obj
	 * @return
	 */
	int save(FriendUser obj);
	
	/**
	 * 删除好友
	 * @param uid
	 * @param fuid
	 * @return
	 */
	int delete(long uid, long fuid);
	
	/**
	 * 将好友移动到新的分组
	 * @param uid
	 * @param friendUid
	 * @param groupid
	 * @return
	 */
	int changeGroup(long uid,long friendUid,long groupid);
			
	/**
	 * 将某个分组下的所有用户都转移到默认分组
	 * @param uid
	 * @param groupid
	 * @return
	 */
	int changeGroupToDefault(long uid,long groupid,long defaultGroupID);
	

	/**
	 * 修改用户好友备注
	 * @param uid
	 * @param friendUid
	 * @param newRemark
	 * @return
	 */
	int changeRemark(long uid,long friendUid,String newRemark);
}
