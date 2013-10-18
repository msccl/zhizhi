package com.qkzz.web.developer.dao;

import java.util.List;

import com.qkzz.web.developer.bean.Store;

public interface StoreDao {

	/**
	 * 添加商店
	 * @param bean
	 * @return
	 */
	public int addStore(Store bean);
	
	/**
	 * 获取最新插入的记录ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public int getLastInsertID(int gameid,String name);
	
	/**
	 * 编辑商店状态
	 * @param bean
	 * @return
	 */
	public int editStoreStatus(int storeID,int status);
	
	
	/**
	 * 编辑商店
	 * @param store
	 * @return
	 */
	public int editStore(Store store);

	
	/**
	 * 删除商店
	 * @param id
	 * @return
	 */
	public int delStore(int id);
	
	/**
	 * 根据ID获取商店信息
	 * @param id
	 * @return
	 */
	public Store getStore(int id);
	
	/**
	 * 获取某个游戏的商店信息
	 * @param gameid
	 * @return
	 */
	public List<Store> getGameStore(int gameid,int startIndex,int num);
	
	
	/**
	 * 游戏商店总量
	 * @param gameid
	 * @return
	 */
	public int getGameStoreMaxCount(int gameid);
}
