package com.qkzz.game.dao;

import java.util.List;

import com.qkzz.game.bean.GameStore;

/**
 * 商店
 * @author Administrator
 *
 */
public interface GameStoreDao {
	
	/**
	 * 获取某游戏商店数量
	 * @param gameid
	 * @return
	 */
	int countByList(int gameid);
	
	/**
	 * 获取某游戏商店列表
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	List<GameStore> getByList(int gameid, int first, int max);

	/**
	 * 获取某游戏某商店详细信息
	 * @param gameid
	 * @param id
	 * @return
	 */
	GameStore getById(int gameid, int id);


	/**
	 * 获取某游戏第一个创建的商店详细信息，如果用户没有罗列的时候没有输入商店ID，就选择第一个创建作为默认商店
	 * @param gameid
	 * @return
	 */
	GameStore getFirstStore(int gameid);

	
	/**
	 * 获取某游戏某商店状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	int getByStatus(int gameid, int id);
	
}
