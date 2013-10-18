package com.qkzz.chat.service;

import java.util.List;

public class ChannelCheckDeamon extends Thread {
	
	private static boolean isRunning = false;//是否正在运行
	private static int keepInterval = 30;//聊天内容保存的最长时间300秒,每次刷新时只需要获取最近N秒的内容即可

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		CheckDeamon ium = new CheckDeamon();
//		ium.start();
//	}

	public void run() {
		while(true) {
			try {
				if(isRunning) {
					continue;
				}
				checkChannel();
				checkWorldChannel();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
//				System.out.println("Channel check items at "+GetDate.getStringDate()+"......");
				Thread.sleep(1000);//1秒检查一次
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检查频道聊天内容是否需要刷新
	 */
	private static void checkChannel() {
		List<String> channelFreshList = ChatService.getChannelFreshList();
		if(channelFreshList == null || channelFreshList.isEmpty()) {
			return;
		}
		
		isRunning = true;
		
		for(int i=0;i<channelFreshList.size();i++) {
			String key = channelFreshList.get(i);
			String[] tmp = key.split("-");
			int channelid = Integer.parseInt(tmp[0]);
			int gameid = Integer.parseInt(tmp[1]);
			System.out.println("checkChannelDeamon=====channelid:"+channelid+"===gameid:"+gameid);
			ChatService.freshCurrentGameContentCache(channelid, gameid, 2);
		}
		isRunning = false;
		System.out.println("Fresh game channel......");
	}

	
	/**
	 * 检查世界聊天是否需要刷新
	 */
	private static void checkWorldChannel() {
		int flag = ChatService.getWorldChatFreshFlag();
		if(flag == 0) {
			return;
		}
		
		isRunning = true;

		ChatService.freshWorldChannelContentCache(keepInterval);
		System.out.println("Fresh world channel.....");

		isRunning = false;
	}
}
