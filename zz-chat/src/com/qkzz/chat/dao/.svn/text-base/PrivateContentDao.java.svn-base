package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.PrivateContent;

public interface PrivateContentDao {

	/**
	 * 添加发送者聊天内容
	 * @param content
	 */
	public int addFromContent(PrivateContent content);
	

	/**
	 * 添加目标用户聊天内容
	 * @param content
	 * @return
	 */
	public int addDestContent(PrivateContent content);

	/**
	 * 获取私聊频道的聊天内容列表，可分页
	 * @param fromuid
	 * @param destuid
	 * @param freshInterval ：时间间隔，单位：秒
	 * @param startIndex
	 * @param number
	 * @return
	 */
	public List<Content> getList(long uid,long lasttime,int number);
	
	/**
	 * 某个时间段内的聊天内容总量
	 * @param fromuid
	 * @param destuid
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public int getMaxCount(long uid,long lasttime);
	
	/**
	 * 某个时间段内的聊天总列表
	 * @param fromuid
	 * @param destuid
	 * @param freshInterval
	 * @return
	 */
	public List<Content> getAllList(long uid,long lasttime);
	
	/**
	 * 清理私聊频道过期数据
	 * @param tableID
	 * @param freshInterval
	 * @return
	 */
	public int clearOutOfDate(int tableID,int freshInterval);


}
