package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.Content;

public interface WorldChatContentDao {

	/**
	 * 向聊天总表中添加内容
	 * @param content
	 * @return
	 */
	int addContent(Content content);
	
	
	/**
	 * 某个时间段内的聊天总列表
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public List<Content> getAllList(int freshInterval);


	/**
	 * 清理频道过期数据
	 * @param freshInterval
	 * @return
	 */
	public int clearOutOfDate(int freshInterval);

}
