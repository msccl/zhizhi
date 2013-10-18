package com.qkzz.game.dao;

import java.util.List;

import com.qkzz.game.bean.GameAuction;

/**
 * 拍卖行
 * @author Administrator
 *
 */
public interface GameAuctionDao {

	/**
	 * 获取某游戏拍卖行数量
	 * @param gameid
	 * @return
	 */
	int countByList(int gameid);
	
	/**
	 * 获取某游戏拍卖行列表
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	List<GameAuction> getByList(int gameid, int first, int max);

	/**
	 * 获取某游戏某拍卖行详细信息
	 * @param gameid
	 * @param id
	 * @return
	 */
	GameAuction getById(int gameid, int id);
	
	/**
	 * 获取某游戏某拍卖行状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	int getByStatus(int gameid, int id);
	
}
