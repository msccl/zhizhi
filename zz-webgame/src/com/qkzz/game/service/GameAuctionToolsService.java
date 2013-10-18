package com.qkzz.game.service;

import java.util.List;

import com.qkzz.game.bean.GameAuctionTools;
import com.qkzz.game.dao.GameAuctionToolsDao;
import com.qkzz.game.dao.impl.GameAuctionToolsDaoImpl;


public class GameAuctionToolsService {
	
	private static GameAuctionToolsDao dao = new GameAuctionToolsDaoImpl();
		
	/**
	 * 某游戏某拍卖行道具总数
	 * @param gameid
	 * @param auctionid
	 * @return
	 */
	public static int countByList(int gameid, int auctionid){
		return dao.countByList(gameid, auctionid);
	}
	
	/**
	 * 某游戏某拍卖行道具总数
	 * @param gameid
	 * @param auctionid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<GameAuctionTools> getByList(int gameid, int auctionid, int first, int max){
		return dao.getByList(gameid, auctionid, first, max);
	}
	
	/**
	 * 某游戏某拍卖行中某用户的某道具信息
	 * @param gameid
	 * @param auctionid
	 * @param id
	 * @return
	 */
	public static GameAuctionTools getById(int gameid, int auctionid, int id){
		return dao.getById(gameid, auctionid, id);
	}

	/**
	 * 某游戏某拍卖行中某用户某道具减少数量
	 * @param gameid
	 * @param auctionid
	 * @param id
	 * @param num
	 * @return
	 */
	public static int decToolsNum(int gameid, int auctionid, int id, int num){
		return dao.decToolsNum(gameid, auctionid, id, num);
	}

	/**
	 * 移除某游戏道具数量为零的记录
	 * @param gameid
	 * @return
	 */
	public static int removeToolsNumZero(int gameid){
		return dao.removeToolsNumZero(gameid);
	}
	
	/**
	 * 用户添加拍卖行道具对象
	 * @param obj
	 * @return
	 */
	public static int add(GameAuctionTools obj){
		return dao.add(obj);
	}
	
	
	
}
