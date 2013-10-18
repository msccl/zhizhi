package com.qkzz.web.developer.dao;

import java.util.List;

import com.qkzz.web.developer.bean.Tools;

public interface ToolsDao {
	/**
	 * 添加道具
	 * @param bean
	 * @return
	 */
	public int addTools(Tools bean);
	
	/**
	 * 获取最新添加的道具ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public int getLastToolsID(int gameid,String name);
	
	/**
	 * 编辑道具
	 * @param bean
	 * @return
	 */
	public int editTools(Tools bean);
	
	/**
	 * 删除道具
	 * @param id
	 * @return
	 */
	public int delTools(int gameid,int id);
	
	/**
	 * 修改道具状态
	 * @param gameid
	 * @param id
	 * @param status
	 * @return
	 */
	public int changeStatus(int gameid,int id,int status);
	
	/**
	 * 根据ID获取道具信息
	 * @param id
	 * @return
	 */
	public Tools getTools(int gameid,int id);
	
	/**
	 * 获取某个游戏的道具列表
	 * @param gameid
	 * @return
	 */
	public List<Tools> getGameTools(int gameid,int startIndex,int num);
	
	/**
	 * 游戏道具总量
	 * @param gameid
	 * @return
	 */
	public int getGameToolsMaxCount(int gameid);

}
