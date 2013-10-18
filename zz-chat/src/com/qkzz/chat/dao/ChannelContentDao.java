package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.Content;

public interface ChannelContentDao {

	/**
	 * 添加聊天内容
	 * @param content
	 */
	public int addContent(Content content);
	
//	/**
//	 * 获取某个频道的聊天内容列表，可分页
//	 * @param channelid
//	 * @param freshInterval ：时间间隔，单位：秒
//	 * @param startIndex
//	 * @param number
//	 * @return
//	 */
//	public List<Content> getList(int channelid,int freshInterval,int startIndex,int number);
	
//	/**
//	 * 某个时间段内的聊天内容总量
//	 * @param channelid
//	 * @param freshInterval ：时间间隔，单位：秒
//	 * @return
//	 */
//	public int getMaxCount(int channelid,int freshInterval);
	
	/**
	 * 某个时间段内的聊天总列表
	 * @param channel
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public List<Content> getAllList(int channelid,int gameid,int freshInterval);
	
	/**
	 * 清理频道过期数据
	 * @param tableID
	 * return
	 */
	public int clearOutOfDate(int tableID,int freshInterval);
}
