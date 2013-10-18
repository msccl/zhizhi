package com.qkzz.web.developer.dao;

import java.util.List;

import com.qkzz.web.developer.bean.GameInfo;

public interface GameInfoDao {

	/**
	 * 添加新游戏
	 * @param bean
	 * @return
	 */
	public int addGame(GameInfo bean);
	
	/**
	 * 编辑游戏
	 * @param bean
	 * @return
	 */
	public int editGame(GameInfo bean);
	
	/**
	 * 更改游戏状态
	 * @param bean
	 * @return
	 */
	public int editGameStatus(int gameID,int status);
	
	/**
	 * 删除游戏
	 * @param id
	 * @return
	 */
	public int delGame(int id);
	
	/**
	 * 根据ID获取游戏信息
	 * @param id
	 * @return
	 */
	public GameInfo getGame(int id);

	/**
	 * 获取游戏列表
	 * @param developerid
	 * @return
	 */
	public List<GameInfo> getGameList(int developerid,int startIndex,int num);

	/**
	 * 游戏总量
	 * @param developerid
	 * @return
	 */
	public int getGameMaxCount(int developerid);
	
	
	/**
	 * 通过游戏编号获取对应的数字ID
	 * @param gamecode
	 * @return
	 */
	public int getIDByGameCode(String gamecode);

}
