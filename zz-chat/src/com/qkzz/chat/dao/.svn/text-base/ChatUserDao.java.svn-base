package com.qkzz.chat.dao;

import com.qkzz.chat.bean.ChatUser;

public interface ChatUserDao {

	/**
	 * 获取聊天频道中用户的信息
	 * @param uid
	 * @return
	 */
	public ChatUser getChatUserInfo(long uid);

	/**
	 * 新用户第一次使用聊天频道时，加入到该表中
	 * @param uid
	 * @param nickname
	 * @return
	 */
	public int addChatUser(long uid,String nickname);

	/**
	 * 判断用户是否已经存在于聊天用户表中
	 * @param uid
	 * @return
	 */
	public boolean isChatUserExist(long uid);

	/**
	 * 更新用户信息，主要是用户昵称的保存更新
	 * @param uid
	 * @param nickname
	 * @return
	 */
	public int updateChatUserInfo(long uid,String nickname);
	
	/**
	 * 更新用户最后聊天时间
	 * @param uid
	 * @return
	 */
	public int updateLastTime(long uid);
}
