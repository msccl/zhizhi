package com.qkzz.game.dao;

import java.util.List;

import com.qkzz.game.bean.GameAuctionTools;

/**
 * 拍卖行道具
 * @author Administrator
 *
 */
public interface GameAuctionToolsDao {
	
	/**
	 * 某游戏某拍卖行道具总数
	 * @param gameid
	 * @param auctionid
	 * @return
	 */
	int countByList(int gameid, int auctionid);
	
	/**
	 * 某游戏某拍卖行道具总数
	 * @param gameid
	 * @param auctionid
	 * @param first
	 * @param max
	 * @return
	 */
	List<GameAuctionTools> getByList(int gameid, int auctionid, int first, int max);
	
	/**
	 * 某游戏某拍卖行中某道具信息
	 * @param gameid
	 * @param auctionid
	 * @param id
	 * @return
	 */
	GameAuctionTools getById(int gameid, int auctionid, int id);

	/**
	 * 某游戏某拍卖行中某道具减少数量
	 * @param gameid
	 * @param auctionid
	 * @param toolsid
	 * @param uid
	 * @param num
	 * @return
	 */
	int decToolsNum(int gameid, int auctionid, int id, int num);

	/**
	 * 移除某游戏道具数量为零的记录
	 * @param gameid
	 * @return
	 */
	int removeToolsNumZero(int gameid);
	
	/**
	 * 用户添加拍卖行道具对象
	 * @param obj
	 * @return
	 */
	int add(GameAuctionTools obj);
	
	
	
}
