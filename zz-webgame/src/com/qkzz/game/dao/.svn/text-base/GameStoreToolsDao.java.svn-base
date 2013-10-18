package com.qkzz.game.dao;

import java.util.List;

import com.qkzz.game.bean.GameStoreTools;

/**
 * 商店道具
 * @author Administrator
 *
 */
public interface GameStoreToolsDao {
	
	/**
	 * 某游戏某商店道具总数
	 * @param gameid
	 * @param storeid
	 * @return
	 */
	int countByList(int gameid, int storeid);
	
	/**
	 * 某游戏某商店道具列表
	 * @param gameid
	 * @param storeid
	 * @param first
	 * @param max
	 * @return
	 */
	List<GameStoreTools> getByList(int gameid, int storeid, int first, int max);
	
	/**
	 * 某游戏某商店中某道具信息
	 * @param gameid
	 * @param storeid
	 * @param id
	 * @return
	 */
	GameStoreTools getById(int gameid, int storeid, int id);

	/**
	 * 某游戏某商店某道具减少数量
	 * @param gameid
	 * @param storeid
	 * @param id
	 * @param num
	 * @return
	 */
	int decToolsNum(int gameid, int storeid, int id, int num);
	
}
