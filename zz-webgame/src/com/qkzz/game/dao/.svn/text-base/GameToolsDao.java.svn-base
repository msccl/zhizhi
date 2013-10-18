package com.qkzz.game.dao;

import java.util.List;

import com.qkzz.game.bean.GameTools;

/**
 * 道具定义
 * @author Administrator
 *
 */
public interface GameToolsDao {

	/**
	 * 获取某游戏道具数量
	 * @param gameid
	 * @return
	 */
	int countByList(int gameid);
	
	/**
	 * 获取某游戏道具列表
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	List<GameTools> getByList(int gameid, int first, int max);

	/**
	 * 获取某游戏某道具详细信息
	 * @param gameid
	 * @param id
	 * @return
	 */
	GameTools getById(int gameid, int id);
	

	/**
	 * 根据道具在游戏内部的唯一ID获取道具详细信息
	 * 前提是该道具已经加入到数据库中，该方法主要用来查询未在DEV后台注册的道具
	 * @param gameid
	 * @param id
	 * @return
	 */
	GameTools getByUnregisteredToolId(int gameid, String id);

	/**
	 * 获取某游戏某道具状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	int getByStatus(int gameid, int id);
	
	/**
	 * 获取某游戏某道具能否交易状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	int getByExchange(int gameid, int id);
	
	/**
	 * 获取某游戏某道具能否拍卖状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	int getByAuction(int gameid, int id);

	/**
	 * 判断游戏中自定义的道具是否存在
	 * 该道具是未在dev后台添加的道具，在游戏中可以直接添加
	 * @param gameid 游戏ID，后台分配
	 * @param toolidInGame 道具在游戏中的唯一ID，可以是字符串
	 * @return
	 */
	boolean isToolsInGameExist(int gameid,String toolidInGame);
	
	/**
	 * 添加道具
	 * @param bean
	 * @return
	 */
	public int addTools(GameTools bean);

}
