package com.qkzz.game.service;

import java.util.List;

import com.qkzz.game.bean.GameStoreTools;
import com.qkzz.game.dao.GameStoreToolsDao;
import com.qkzz.game.dao.impl.GameStoreToolsDaoImpl;


public class GameStoreToolsService {
	
	private static GameStoreToolsDao dao = new GameStoreToolsDaoImpl();
	
	/**
	 * 某游戏某商店道具总数
	 * @param gameid
	 * @param storeid
	 * @return
	 */
	public static int countByList(int gameid, int storeid){
		return dao.countByList(gameid, storeid);
	}
	
	/**
	 * 某游戏某商店道具列表
	 * @param gameid
	 * @param storeid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<GameStoreTools> getByList(int gameid, int storeid, int first, int max){
		return dao.getByList(gameid, storeid, first, max);
	}
	
	/**
	 * 某游戏某商店中某道具信息
	 * @param gameid
	 * @param storeid
	 * @param id
	 * @return
	 */
	public static GameStoreTools getById(int gameid, int storeid, int id){
		return dao.getById(gameid, storeid, id);
	}

	/**
	 * 某游戏某商店某道具减少数量
	 * @param gameid
	 * @param storeid
	 * @param id
	 * @param num
	 * @return
	 */
	public static int decToolsNum(int gameid, int storeid, int id, int num){
		return dao.decToolsNum(gameid, storeid, id, num);
	}
	
	
}
