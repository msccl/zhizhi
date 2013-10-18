package com.qkzz.game.service;

import java.util.List;

import com.qkzz.game.bean.GameStore;
import com.qkzz.game.dao.GameStoreDao;
import com.qkzz.game.dao.impl.GameStoreDaoImpl;


public class GameStoreService {
	
	private static GameStoreDao dao = new GameStoreDaoImpl();

	/**
	 * 获取某游戏商店数量
	 * @param gameid
	 * @return
	 */
	public static int countByList(int gameid){
		return dao.countByList(gameid);
	}
	
	/**
	 * 获取某游戏商店列表
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<GameStore> getByList(int gameid, int first, int max){
		return dao.getByList(gameid, first, max);
	}

	/**
	 * 获取某游戏第一个创建的商店详细信息，如果用户没有罗列的时候没有输入商店ID，就选择第一个创建作为默认商店
	 * @param gameid
	 * @return
	 */
	public static GameStore getFirstStore(int gameid) {
		return dao.getFirstStore(gameid);
	}
	
	
	/**
	 * 获取某游戏某商店详细信息
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static GameStore getById(int gameid, int id){
		return dao.getById(gameid, id);
	}

	/**
	 * 获取某游戏某商店状态
	 * @param gameid
	 * @param id
	 * @return
	 */
	public static int getByStatus(int gameid, int id){
		return dao.getByStatus(gameid, id);
	}
	
	
}
