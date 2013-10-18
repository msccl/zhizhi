package com.qkzz.online.dao;

import java.util.List;

import com.qkzz.online.bean.GameOnline;

public interface GameOnlineDao {

	/**
	 * 获取某个游戏中的在线用户列表
	 * @param gameid
	 * @param startIndex
	 * @param count
	 * @return
	 */
	List<GameOnline> getGameUserList(int gameid,int startIndex,int count);

	/**
	 * 获取游戏在线用户总量
	 * @param gameid
	 * @return
	 */
	int getGameUserMaxCount(int gameid);
	
	/**
	 * 判断用户在某个游戏中是否在线
	 * @param uid
	 * @param gameid
	 * @return
	 */
	boolean isGameOnline(long uid,int gameid);

	/**
	 * 获取用户正在玩的游戏id
	 * @param uid
	 * @return
	 */
	int getPlayingGameID(long uid);
	
	/**
	 * 获取用户打开的游戏ID列表
	 * @param uid
	 * @return
	 */
	List<Integer> getUserOpenningGameIDList(long uid,int startIndex,int count);
	
	/**
	 * 获取用户打开的游戏ID总量
	 * @param uid
	 * @return
	 */
	int getUserOpenningGameIDMaxCount(long uid);

	/**
	 * 主动清除游戏在线记录
	 * @param uid
	 * @param gameid
	 * @return
	 */
	int clearGameOnline(long uid);
	
	/**
	 * 主动清除按照游戏ID区分的在线记录
	 * @param uid
	 * @param gameid
	 * @return
	 */
	int clearGameByGameIDOnline(long uid,int gameid);
}
