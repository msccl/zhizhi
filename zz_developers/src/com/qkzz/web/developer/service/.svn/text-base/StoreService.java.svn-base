package com.qkzz.web.developer.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.developer.bean.Store;
import com.qkzz.web.developer.dao.StoreDao;

public class StoreService {

	private static StoreDao dao = ActiveBean.getBean("storeDaoImpl", StoreDao.class);

	/**
	 * 添加商店
	 * @param bean
	 * @return
	 */
	public static int addStore(Store bean) {
		return dao.addStore(bean);
	}
	
	/**
	 * 获取最新插入的记录ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public static int getLastInsertID(int gameid,String name) {
		return dao.getLastInsertID(gameid, name);
	}

	
	/**
	 * 编辑商店状态
	 * @param bean
	 * @return
	 */
	public static int editStoreStatus(int storeID,int status) {
		return dao.editStoreStatus(storeID, status);
	}
	
	/**
	 * 删除商店
	 * @param id
	 * @return
	 */
	public static int delStore(int id) {
		return dao.delStore(id);
	}
	
	/**
	 * 根据ID获取商店信息
	 * @param id
	 * @return
	 */
	public static Store getStore(int id) {
		return dao.getStore(id);
	}
	
	/**
	 * 获取某个游戏的商店信息
	 * @param gameid
	 * @return
	 */
	public static List<Store> getGameStore(int gameid,int startIndex,int num) {
		return dao.getGameStore(gameid,startIndex,num);
	}

	
	/**
	 * 获取游戏商店总量
	 * @param gameid
	 * @return
	 */
	public static int getGameStoreMaxCount(int gameid) {
		return dao.getGameStoreMaxCount(gameid);
	}

	/**
	 * 编辑商店
	 * @param store
	 * @return
	 */
	public static int editStore(Store store) {
		return dao.editStore(store);
	}
}
