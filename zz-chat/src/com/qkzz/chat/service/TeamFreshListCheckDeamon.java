package com.qkzz.chat.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.qkzz.chat.bean.TeamFreshBean;

/**
 * 本地服务器刷新列表更新到内存表中，用于其它机器进行同步使用
 * @author Administrator
 *
 */
public class TeamFreshListCheckDeamon extends Thread {
	
	private static boolean isRunning = false;//是否正在运行

	public void run() {
		while(true) {
			try {
				if(isRunning) {
					continue;
				}
				checkFreshList();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
//				System.out.println("Channel check items at "+GetDate.getStringDate()+"......");
				Thread.sleep(2000);//2秒检查一次
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检查刷新列表缓存中是否需要更新到内存表
	 */
	public static void checkFreshList() {
		ConcurrentHashMap<String,TeamFreshBean> map = TeamChatService.getTeamFreshMap();
		if(map == null || map.isEmpty()) {
			return;
		}
		
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();
			String key = (String)entry.getKey();
			TeamFreshBean value = (TeamFreshBean)entry.getValue();
			if(value.getLasttime() < System.currentTimeMillis()-30*1000) {
				TeamChatService.removeFromFreshList(value.getGameid(), value.getTeamid());
				continue;
			}
			ChatTeamService.addFreshRecord(value.getGameid(), value.getTeamid());
			ChatTeamService.clearOutOfDateFreshList(30);
		}
		System.out.println("Fresh game team chat......");
	}
	
}
