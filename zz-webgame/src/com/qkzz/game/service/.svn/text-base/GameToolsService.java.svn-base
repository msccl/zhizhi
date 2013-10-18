package com.qkzz.game.service;

import java.util.List;

import com.qkzz.game.bean.GameTools;
import com.qkzz.game.dao.GameToolsDao;
import com.qkzz.game.dao.impl.GameToolsDaoImpl;


public class GameToolsService {
	
	private static GameToolsDao dao = new GameToolsDaoImpl();

	/**
	 * 获取某游戏道具数量
	 * @param gameid
	 * @return
	 */
	public static int countByList(int gameid){
		return dao.countByList(gameid);
	}
	
	/**
	 * 获取某游戏道具列表
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<GameTools> getByList(int gameid, int first, int max){
		return dao.getByList(gameid, first, max);
	}

	/**
	 * 获取某游戏某道具详细信息
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static GameTools getById(int gameid, int id){
		return dao.getById(gameid, id);
	}
	
	
	/**
	 * 根据道具在游戏内部的唯一ID获取道具详细信息
	 * 前提是该道具已经加入到数据库中，该方法主要用来查询未在DEV后台注册的道具
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static GameTools getByUnregisteredToolId(int gameid, String id) {
		return dao.getByUnregisteredToolId(gameid, id);
	}

	
	/**
	 * 获取某游戏某道具状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static int getByStatus(int gameid, int id){
		return dao.getByStatus(gameid, id);
	}
	
	/**
	 * 获取某游戏某道具能否交易状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static int getByExchange(int gameid, int id){
		return dao.getByExchange(gameid, id);
	}
	
	/**
	 * 获取某游戏某道具能否拍卖状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static int getByAuction(int gameid, int id){
		return dao.getByAuction(gameid, id);
	}
	
	/**
	 * 判断游戏中自定义的道具是否存在
	 * 该道具是未在dev后台添加的道具，在游戏中可以直接添加
	 * @param gameid 游戏ID，后台分配
	 * @param toolsidInGame 道具在游戏中的唯一ID，可以是字符串
	 * @return
	 */
	public static boolean isToolsInGameExist(int gameid,String toolidInGame) {
		return dao.isToolsInGameExist(gameid, toolidInGame);
	}

	
	/**
	 * 在游戏中直接添加未注册的道具
	 * @param bean
	 * @return
	 */
	public static int addTools(GameTools bean) {
		return dao.addTools(bean);
	}

}
