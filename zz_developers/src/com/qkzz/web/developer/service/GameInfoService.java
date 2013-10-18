package com.qkzz.web.developer.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.util.MD5Encrypt;
import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.dao.GameInfoDao;

public class GameInfoService {

	private static GameInfoDao dao = ActiveBean.getBean("gameInfoDaoImpl", GameInfoDao.class);

	/**
	 * 添加新游戏
	 * @param bean
	 * @return
	 */
	public static int addGame(GameInfo bean) {
		return dao.addGame(bean);
	}
	
	/**
	 * 编辑游戏
	 * @param bean
	 * @return
	 */
	public static int editGame(GameInfo bean) {
		return dao.editGame(bean);
	}
	
	/**
	 * 更改游戏状态
	 * @param bean
	 * @return
	 */
	public static int editGameStatus(int gameID,int status) {
		return dao.editGameStatus(gameID, status);
	}
	
	/**
	 * 删除游戏
	 * @param id
	 * @return
	 */
	public static int delGame(int id) {
		return dao.delGame(id);
	}
	
	/**
	 * 根据ID获取游戏信息
	 * @param id
	 * @return
	 */
	public static GameInfo getGame(int id) {
		return dao.getGame(id);
	}

	/**
	 * 获取游戏列表
	 * @param developerid
	 * @return
	 */
	public static List<GameInfo> getGameList(int developerid,int startIndex,int num) {
		return dao.getGameList(developerid,startIndex,num);
	}

	
	/**
	 * 获取游戏总量
	 * @param developerid
	 * @return
	 */
	public static int getGameMaxCount(int developerid) {
		return dao.getGameMaxCount(developerid);
	}

	/**
	 * 生成唯一编码
	 * @param devuid
	 * @return
	 */
	public static String generateGameCode(int devuid) {
		String key = new StringBuffer(devuid).append("-").append(System.currentTimeMillis()).toString();
		return MD5Encrypt.encoderForString(key);
	}
	
	
	/**
	 * 通过游戏编号获取对应的数字ID
	 * @param gamecode
	 * @return
	 */
	public static int getIDByGameCode(String gamecode) {
		return dao.getIDByGameCode(gamecode);
	}
}
