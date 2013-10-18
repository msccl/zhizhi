package com.qkzz.chat.service;

import java.util.List;

import com.qkzz.common.GetDate;

public class PrivateCheckDeamon extends Thread {
	
	private static boolean isRunning = false;//是否正在运行
//	private static int keepInterval = 300;//聊天内容保存的最长时间，300秒

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
					System.out.println("666666666666666");
					continue;
				}
				checkPrivate();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("Private check items at "+GetDate.getStringDate()+"......");
				Thread.sleep(2*1000);//1秒检查一次
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void checkPrivate() {
		List<String> privateIDList = ChatService.getPrivateIDList();
		if(privateIDList == null || privateIDList.isEmpty()) {
			System.out.println("cccccccccccccccc");
			return;
		}
		
		System.out.println("ddddddddddddddd");
		for(int i=0;i<privateIDList.size();i++) {
			String p = privateIDList.get(i);
			String[] tmp = p.split("-");
			long fromuid = 0;
			long destuid = 0;
			if(tmp != null) {
				fromuid = Long.parseLong(tmp[0]);
				destuid = Long.parseLong(tmp[1]);
			}
//			ChatService.freshPrivateContentCache(fromuid, destuid, keepInterval);
		}
		System.out.println("eeeeeeeeeeeeeeeeeeeeee");
	}
}
