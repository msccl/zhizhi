package com.qkzz.game.dao;

import java.util.List;

import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.service.bo.UserboxTools;

/**
 * 用户道具
 * @author Administrator
 *
 */
public interface UserToolboxDao {

	/**
	 * 某用户道具总数
	 * @param uid
	 * @return
	 */
	int countByList(long uid);

	/**
	 * 某用户道具列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	List<UserToolbox> getByList(long uid, int first, int max);

	/**
	 * 某用户某游戏道具总数
	 * @param uid
	 * @param gameid
	 * @return
	 */
	int countByList(long uid, int gameid);

	
	/**
	 * 获取道具箱中所有道具的详细信息，不需要分页
	 * @param uid
	 * @param gameid
	 * @return
	 */
	List<UserboxTools> getAllToolsList(long uid,int gameid);
	

	/**
	 * 获取道具箱中排除当前游戏的其他所有道具的详细信息，不需要分页
	 * @param uid
	 * @param gameid
	 * @return
	 */
	List<UserboxTools> getAllOtherGameToolsList(long uid,int gameid);

	
	/**
	 * 某用户某游戏道具列表
	 * @param uid
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	List<UserToolbox> getByList(long uid, int gameid, int first, int max);
	

	/**
	 * 获取某用户游戏中的某个道具信息
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @return
	 */
	UserToolbox getByInfo(long uid, int gameid, int toolsid);
	
	/**
	 * 判断用户是否已有某游戏中的某个道具
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @return
	 */
	boolean isValidateTool(long uid, int gameid, int toolsid);

	/**
	 * 某用户某游戏增加某道具数量
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @param num
	 * @return
	 */
	int incToolsNum(long uid, int gameid, int toolsid, int num);
	
	/**
	 * 某用户某游戏减少某道具数量
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @param num
	 * @return
	 */
	int decToolsNum(long uid, int gameid, int toolsid, int num);

	/**
	 * 移除用户道具数量为零的记录
	 * @param uid
	 * @return
	 */
	int removeToolsNumZero(long uid);
	
	/**
	 * 添加用户道具对象
	 * @param obj
	 * @return
	 */
	int add(UserToolbox obj);
	
	int createTable0(int i);
	int createTable1(int i);
	int createTable2(int i);
}
