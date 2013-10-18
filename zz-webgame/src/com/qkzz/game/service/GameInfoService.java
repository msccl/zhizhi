package com.qkzz.game.service;

import com.qkzz.common.LRUMap;
import com.qkzz.game.bean.GameCacheBean;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.dao.GameInfoDao;
import com.qkzz.game.dao.impl.GameInfoDaoImpl;

public class GameInfoService {

	private static GameInfoDao dao = new GameInfoDaoImpl();

	public static LRUMap<Integer, String> gameNameMap = new LRUMap<Integer, String>(1000);

	public static LRUMap<String, Integer> gameCodeMap = new LRUMap<String,Integer>(1000);
	
	public static LRUMap<Integer, GameCacheBean> gameMap = new LRUMap<Integer,GameCacheBean>(2000);

	
	/**
	 * 根据ID获取游戏信息
	 * @param id
	 * @return
	 */
	public static GameInfo getGame(int id) {
		GameInfo game = null;
		if(gameMap.containsKey(id)) {
			GameCacheBean bean = gameMap.get(id);
			game = bean.getGame();
			if(System.currentTimeMillis() - bean.getLastFreshTime() > 180*1000) {
				game = dao.getGame(id);
				if(game != null) {
					bean.setGame(game);
					bean.setLastFreshTime(System.currentTimeMillis());
					gameMap.put(id, bean);
				}
			}
			return game;
		}
		
		game = dao.getGame(id);
		if(game != null) {
			GameCacheBean bean = new GameCacheBean();
			bean.setGame(game);
			bean.setLastFreshTime(System.currentTimeMillis());
			gameMap.put(id, bean);
		}
		
		return game;
	}
	
	public static String getName(int id){
		if(gameNameMap.containsKey(id)) {
			return gameNameMap.get(id); 
		}

		String name = "";
		GameInfo gi = getGame(id);
		if(gi != null) {
			name = gi.getName();
			gameNameMap.put(id, name);
		}
		return name;
	}

	
	/**
	 * 通过游戏编号获取对应的数字ID
	 * @param gamecode
	 * @return
	 */
	public static int getGameIDByGameCode(String gamecode) {
		if(gameCodeMap.containsKey(gamecode)) {
			return gameCodeMap.get(gamecode);
		}
		
		int gameid = dao.getIDByGameCode(gamecode);
		gameCodeMap.put(gamecode, gameid);
		return gameid;
	}
}
