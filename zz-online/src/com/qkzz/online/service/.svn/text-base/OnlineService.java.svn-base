package com.qkzz.online.service;

import java.util.List;

import com.qkzz.common.LRUMap;
import com.qkzz.common.Tools;
import com.qkzz.online.bean.GameOnline;
import com.qkzz.online.bean.Online;
import com.qkzz.online.bean.OnlineQueue;
import com.qkzz.online.dao.GameOnlineDao;
import com.qkzz.online.dao.OnlineDao;
import com.qkzz.online.dao.OnlineQueueDao;
import com.qkzz.online.dao.impl.GameOnlineDaoImpl;
import com.qkzz.online.dao.impl.OnlineDaoImpl;
import com.qkzz.online.dao.impl.OnlineQueueDaoImpl;
import com.qkzz.user.bean.User;


public class OnlineService {
	
	private static OnlineDao dao = new OnlineDaoImpl();
	private static GameOnlineDao gamedao = new GameOnlineDaoImpl();
	private static OnlineQueueDao queuedao = new OnlineQueueDaoImpl();
	
	/**
	 * 获取随机玩家列表
	 * @param gameid
	 * @param count
	 * @return
	 */
	public static List<Online> getRandUserList(int count) {
		return dao.getRandUserList(count);
	}
	
	/**
	 * 判断用户是否全局在线
	 * 不包含游客
	 * @param uid
	 * @return
	 */
	public static boolean isGlobalOnlne(long uid) {
		return dao.isGlobalOnlne(uid);
	}

	/**
	 * 判断用户是否全局在线，需要判断用户签名
	 * @param uid
	 * @param sign
	 * @return
	 */
	public static boolean isGlobalOnlne(long uid,String sign){
		return dao.isGlobalOnlne(uid, sign);
	}

	/**
	 * 获取某个游戏中的在线用户列表
	 * @param gameid
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public static List<GameOnline> getGameUserList(int gameid,int startIndex,int count) {
		return gamedao.getGameUserList(gameid, startIndex, count);
	}

	/**
	 * 获取游戏在线用户总量
	 * @param gameid
	 * @return
	 */
	public static int getGameUserMaxCount(int gameid) {
		return gamedao.getGameUserMaxCount(gameid);
	}
	
	/**
	 * 判断用户在某个游戏中是否在线
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public static boolean isGameOnline(long uid,int gameid) {
		return gamedao.isGameOnline(uid, gameid);
	}

	/**
	 * 获取用户正在玩的游戏id
	 * @param uid
	 * @return
	 */
	public static int getPlayingGameID(long uid) {
		return gamedao.getPlayingGameID(uid);
	}
	
	/**
	 * 获取用户打开的游戏ID列表
	 * @param uid
	 * @return
	 */
	public static List<Integer> getUserOpenningGameIDList(long uid,int startIndex,int count) {
		return gamedao.getUserOpenningGameIDList(uid, startIndex, count);
	}
	
	/**
	 * 获取用户打开的游戏ID总量
	 * @param uid
	 * @return
	 */
	public static int getUserOpenningGameIDMaxCount(long uid) {
		return gamedao.getUserOpenningGameIDMaxCount(uid);
	}

	
	/**
	 * 添加到在线队列中
	 * @param bean
	 * @return
	 */
	public static int addToQueue(OnlineQueue bean) {
		return queuedao.addToQueue(bean);
	}
	
	/**
	 * 缓存在线用户对象
	 */
	private static LRUMap<Long, User> lastUpdateUserMap = new LRUMap<Long, User>(100, 10000);
	public static User getByUser(long uid) {
		User obj = null;
		if (!lastUpdateUserMap.containsKey(uid)) {
			obj = dao.getByUser(uid);
			obj.setLastUpdateTime(System.currentTimeMillis());
			lastUpdateUserMap.put(uid, obj);
		} else {
			obj = lastUpdateUserMap.get(uid);
			/**
			 * 如果对象缓存超过了10*60秒，重新从数据库中获取对象并加入缓存
			 */
			if (Tools.isExpired(obj.getLastUpdateTime(), 60*10)) {
				obj = dao.getByUser(uid);
				obj.setLastUpdateTime(System.currentTimeMillis());
				lastUpdateUserMap.put(uid, obj);
			} 
		}
		return obj;
	}
	
	
	/**
	 * 清除用户在线状态
	 * @param uid
	 */
	public static void clearOnline(long uid) {
		List<Integer> list = gamedao.getUserOpenningGameIDList(uid, 0, 100);
		if(list != null && list.size() > 0) {
			for(int i=0;i<list.size();i++) {
				int gameid = list.get(i);
				gamedao.clearGameByGameIDOnline(uid, gameid);
			}
		}
		gamedao.clearGameOnline(uid);
		dao.clearOnline(uid);
	}
	
	
//	/**
//	 * 缓存用户列表
//	 */
//	public static LRUMap<String, OnlineLastList> lastMap = new LRUMap<String, OnlineLastList>(100, 1000);
//	public static List<Online> getByLastList(int appid,int first, int max) {
//		String Key = appid + "."+first+"."+ max;
//		List<Online> lastlist = null;
//		if (!lastMap.containsKey(Key)) {
//			if(appid==0){
//				lastlist = dao.getByList(first, max);
//			}else{
//				lastlist = dao.getByList(appid, first, max);
//			}
//			OnlineLastList list = new OnlineLastList();
//			list.setList(lastlist);
//			list.setLastUpdateTime(System.currentTimeMillis());
//			lastMap.put(Key, list);
//		} else {
//			OnlineLastList list = (OnlineLastList)lastMap.get(Key);
//			/**
//			 * 如果对象缓存超过了10*60秒，重新从数据库中获取对象并加入缓存
//			 */
//			if (Tools.isExpired(list.getLastUpdateTime(), 60*10)) {
//				if(appid==0){
//					lastlist = dao.getByList(first, max);
//				}else{
//					lastlist = dao.getByList(appid, first, max);
//				}
//				list.setList(lastlist);
//				list.setLastUpdateTime(System.currentTimeMillis());
//				lastMap.put(Key, list);
//			} else {
//				lastlist = list.getList();
//			}
//		}
//		return lastlist;
//	}
//	
//	/**
//	 * 缓存用户人数
//	 */
//	public static Map<Integer, long []> countMap = new HashMap<Integer, long []>();
//	public static int countByList(int appid) {
//		//清理在线用户
//		OnlineService.cleanByList();
//		
//		long count = 0;
//		if (!countMap.containsKey(appid)) {
//			if(appid==0){
//				count = dao.count();
//			}else{
//				count = dao.count(appid);
//			}
//			long[] value = { count, System.currentTimeMillis() };
//			countMap.put(appid, value);
//		} else {
//			long[] value = (long[]) countMap.get(appid);
//			/**
//			 * 如果对象缓存超过了5*60秒，重新从数据库中获取对象并加入缓存
//			 */
//			if (Tools.isExpired(value[1], 5*10)) {
//				if(appid==0){
//					count = dao.count();
//				}else{
//					count = dao.count(appid);
//				}
//				value[0] = count;
//				value[1] = System.currentTimeMillis();
//				countMap.put(appid, value);
//			}else{
//				count = value[0];
//			}
//		}		
//		return Integer.parseInt(Long.toString(count));
//	}
	

}
