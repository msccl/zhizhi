package com.qkzz.friend.service;

import java.util.List;

import com.qkzz.friend.bean.FriendUser;
import com.qkzz.friend.bean.FriendUserListInfo;
import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.dao.FriendUserDao;
import com.qkzz.friend.dao.impl.FriendUserDaoImpl;

public class FriendUserService {
	
	private static FriendUserDao dao = new FriendUserDaoImpl();

	public static FriendUser getByFriendUser(long uid, long fuid){
		return dao.getByFriendUser(uid, fuid);
	}
	
	public static int countByList(long uid){
		return dao.countByList(uid);
	}
	
	public static List<FriendUser> getByList(long uid, int first, int max){
		return dao.getByList(uid, first, max);
	}
	
	public static boolean isFriendUser(long uid, long fuid){
		return dao.isFriendUser(uid, fuid);
	}
	
	public static int save(FriendUser obj){
		return dao.save(obj);
	}
	
	public static int delete(long uid, long fuid){
		return dao.delete(uid, fuid);
	}
			
	
	/**
	 * 将好友移动到新的分组
	 * @param uid
	 * @param friendUid
	 * @param groupid
	 * @return
	 */
	public static int changeGroup(long uid,long friendUid,long groupid) {
		return dao.changeGroup(uid, friendUid, groupid);
	}
			
	/**
	 * 将某个分组下的所有用户都转移到默认分组
	 * @param uid
	 * @param groupid
	 * @return
	 */
	public static int changeGroupToDefault(long uid,long groupid) {
		long defaultGroupID = FriendGroupService.getDefaultGroupID(uid);
		return dao.changeGroupToDefault(uid, groupid,defaultGroupID);
	}

	
	/**
	 * 获取好友列表
	 * 完整信息
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<FriendUserListInfo> getFriendList(long uid, int first, int max) {
		return dao.getFriendList(uid, first, max);
	}
	
	
	/**
	 * 获取社交好友列表
	 * 完整信息，主要用于社交好友接口
	 * 需要传递游戏ID，用于判断用户的好友是否已经在该游戏中激活
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<SocialFriendUserListInfo> getSocialFriendList(long uid, int gameid) {
		return dao.getSocialFriendList(uid, gameid);
	}

	
	
	/**
	 * 修改用户好友备注
	 * @param uid
	 * @param friendUid
	 * @param newRemark
	 * @return
	 */
	public static int changeRemark(long uid,long friendUid,String newRemark) {
		return dao.changeRemark(uid, friendUid, newRemark);
	}

}
