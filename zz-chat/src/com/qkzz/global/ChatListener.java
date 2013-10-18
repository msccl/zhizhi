package com.qkzz.global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qkzz.chat.service.ChannelCheckDeamon;
import com.qkzz.chat.service.TeamCheckDeamon;
import com.qkzz.chat.service.TeamFreshListCheckDeamon;

/**
 * 请主意，在web-app中，listener元素位于所有的servlet 元素之前以及所有filter-mapping元素之后。
 * 
 * @author james
 * 
 */
public class ChatListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		try {

			System.out.println("Chat Listener starting........");
			System.out.println("====================================");

			//启动频道聊天监控线程
			System.out.println("Channel check thread start.....");
			ChannelCheckDeamon ium = new ChannelCheckDeamon();
			ium.start();
			System.out.println("Channel check thread start over.....");
			System.out.println("====================================");
			
			//启动队伍聊天监控线程
			System.out.println("Team check thread start.....");
			TeamCheckDeamon thread = new TeamCheckDeamon();
			thread.start();
			System.out.println("Team check thread start over.....");
			System.out.println("====================================");

			//队伍刷新列表监控线程
			System.out.println("FreshList check thread start.....");
			TeamFreshListCheckDeamon freshThread = new TeamFreshListCheckDeamon();
			freshThread.start();
			System.out.println("FreshList check thread start over...");
			System.out.println("====================================");
			
			System.out.println("Chat Listener over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}
