package com.qkzz.chat.service;

import java.util.List;

import com.qkzz.chat.bean.TeamFreshBean;
import com.qkzz.common.GetDate;

public class TeamCheckDeamon extends Thread {
	
	private static boolean isRunning = false;//是否正在运行
	private static int keepInterval = 30;//聊天内容保存的最长时间300秒,每次刷新时只需要获取最近N秒的内容即可

	public void run() {
		while(true) {
			try {
				if(isRunning) {
					continue;
				}
				checkTeam();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("Team check items at "+GetDate.getStringDate()+"......");
				Thread.sleep(1000);//1秒检查一次
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检查频道聊天内容是否需要刷新
	 */
	private static void checkTeam() {
		List<TeamFreshBean> teamFreshList = ChatTeamService.getTeamFreshList();
		if(teamFreshList == null || teamFreshList.isEmpty()) {
			return;
		}
		
		isRunning = true;
		
		for(int i=0;i<teamFreshList.size();i++) {
			TeamFreshBean bean = teamFreshList.get(i);
			int gameid = bean.getGameid();
			long teamid = bean.getTeamid();
			System.out.println("checkTeamDeamon=====gameid:"+gameid+"===teamid:"+teamid);
			TeamChatService.freshTeamContentCache(gameid, teamid, 30);
		}
		isRunning = false;
		System.out.println("Fresh game team chat......");
	}
	
}
