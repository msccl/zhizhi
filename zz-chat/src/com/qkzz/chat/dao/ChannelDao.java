package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.Channel;

public interface ChannelDao {

	/**
	 * 获取频道信息
	 * @param id
	 * @return
	 */
	public Channel getChannel(int id);
	

	/**
	 * 获取所有在用频道的列表
	 * @param category
	 * @param startIndex
	 * @param number
	 * @return
	 */
	public List<Channel> getList(int startIndex,int number);


	/**
	 * 获取所有在用的频道列表
	 * @return
	 */
	public List<Channel> getAllList();

	/**
	 * 通过游戏ID获取当前游戏的聊天频道ID
	 * @param gameid
	 * @return
	 */
	public int getChannelIDByGameID(int gameid);
}
