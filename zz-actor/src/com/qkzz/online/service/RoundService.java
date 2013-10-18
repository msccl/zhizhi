package com.qkzz.online.service;

import com.qkzz.common.ActiveBean;
import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.ByGameDao;
import com.qkzz.online.dao.GameDao;
import com.qkzz.online.dao.OnlineDao;


public class RoundService {

	private static OnlineDao onlinedao = ActiveBean.getBean("onlineDaoImpl", OnlineDao.class);
	private static GameDao gamedao = ActiveBean.getBean("gameDaoImpl", GameDao.class);
	private static ByGameDao bygamedao = ActiveBean.getBean("byGameDaoImpl", ByGameDao.class);
	
	/**
	 * 更新到Online
	 * @param obj
	 * @return
	 */
	private static boolean addOnline(Queue obj){
		if(onlinedao.isExistAll(obj.getUid())){
			onlinedao.updateAll(obj);
		}else{
			onlinedao.addAll(obj);
		}
		
		if(onlinedao.isExist(obj.getUid())){
			return onlinedao.update(obj);
		}else{
			return onlinedao.add(obj);
		}
	}
	
	/**
	 * 更新到Game
	 * @param obj
	 * @return
	 */
	private static boolean addGame(Queue obj){
		if(gamedao.isExistAll(obj.getUid())){
			gamedao.updateAll(obj);
		}else{
			gamedao.addAll(obj);
		}
		
		if(gamedao.isExist(obj.getUid())){
			return gamedao.update(obj);
		}else{
			return gamedao.add(obj);
		}
	}

	/**
	 * 更新到 ByGame
	 * @param obj
	 * @return
	 */
	private static boolean addByGame(Queue obj){
		if(bygamedao.isExist(obj)){
			return bygamedao.update(obj);
		}else{
			return bygamedao.add(obj);
		}
	}
	
	public static boolean addOrUpdate(Queue obj){
		if(addOnline(obj) && addGame(obj) && addByGame(obj)){
			return true;
		}else{
			return false;
		}
	}
		
}
