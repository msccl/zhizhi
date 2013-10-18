package com.qkzz.chat.dao;

import java.util.List;

import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.TeamContent;

public interface TeamContentDao {

	/**
	 * 添加聊天内容
	 * @param content
	 */
	public int addContent(TeamContent content);
	
	/**
	 * 某个时间段内的聊天总列表
	 * @param channel
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public List<Content> getAllList(long teamid,int freshInterval);
	
	/**
	 * 清理队伍过期数据
	 * @param tableID
	 * return
	 */
	public int clearOutOfDate(long teamid,int freshInterval);
}
