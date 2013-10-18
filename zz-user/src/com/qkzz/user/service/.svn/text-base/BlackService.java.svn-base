package com.qkzz.user.service;

import java.util.List;

import com.qkzz.common.LRUMap;
import com.qkzz.user.bean.BlackUser;
import com.qkzz.user.dao.BlackUserDao;
import com.qkzz.user.dao.impl.BlackUserDaoImpl;

public class BlackService {
	
	private static BlackUserDao dao = new BlackUserDaoImpl();
	private static LRUMap<String, BlackUser> inputForbidUserMap = null;
	private static long lastFreshTime = System.currentTimeMillis();
	
	private static LRUMap<String, BlackUser> checkLoad() {
		if (inputForbidUserMap == null || System.currentTimeMillis()-lastFreshTime > 30*1000) {
			loadBlackUser();
			lastFreshTime = System.currentTimeMillis();
		}
		return inputForbidUserMap;
	}

	synchronized static void loadBlackUser() {
		LRUMap<String, BlackUser> tmpMap = new LRUMap<String, BlackUser>(10000);
		List<BlackUser> list = dao.getList();
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				BlackUser inputForbidUser = list.get(i);
				if(tmpMap.containsKey(inputForbidUser.getIp())) {
					continue;
				}
				tmpMap.put(inputForbidUser.getIp(), inputForbidUser);
			}
		}
		inputForbidUserMap = tmpMap;

		//清理过期数据
		dao.clearOutOfDate();
	}

	
	/**
	 * 添加屏蔽禁言记录
	 * @param bean
	 * @return
	 */
	public static int addBlack(BlackUser bean) {
		int ret = dao.addBlack(bean);
		checkLoad().put(bean.getIp(), bean);
		return ret;
	}
	
	
	/**
	 * 删除加黑记录
	 * @param uid
	 * @param type
	 * @return
	 */
	public static int delBlack(int uid) {
		int ret = dao.delBlack(uid);
		if(ret != -1) {
			checkLoad().remove(uid);
			return ret;
		}
		return -1;
	}


	/**
	 * 检查用户是否已被加黑禁言
	 * @param uid
	 * @return
	 */
	public static boolean isBlackUser(String ip) {
		if (inputForbidUserMap != null && checkLoad().get(ip) != null) {
			return true;
		}
		return checkLoad().get(ip) != null;
	}
}
