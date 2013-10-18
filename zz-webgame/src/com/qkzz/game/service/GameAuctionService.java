package com.qkzz.game.service;

import java.util.List;

import com.qkzz.game.bean.GameAuction;
import com.qkzz.game.dao.GameAuctionDao;
import com.qkzz.game.dao.impl.GameAuctionDaoImpl;


public class GameAuctionService {
	
	private static GameAuctionDao dao = new GameAuctionDaoImpl();
	
	/**
	 * 获取某游戏拍卖行数量
	 * @param gameid
	 * @return
	 */
	public static int countByList(int gameid){
		return dao.countByList(gameid);
	}
	
	/**
	 * 获取某游戏拍卖行列表
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<GameAuction> getByList(int gameid, int first, int max){
		return dao.getByList(gameid, first, max);
	}

	/**
	 * 获取某游戏某拍卖行详细信息
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static GameAuction getById(int gameid, int id){
		return dao.getById(gameid, id);
	}
	
	/**
	 * 获取某游戏某拍卖行状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static int getByStatus(int gameid, int id){
		return dao.getByStatus(gameid, id);
	}
	
}
