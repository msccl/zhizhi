package com.qkzz.global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qkzz.friend.service.SocialFriendListQueueCheckDeamon;


/**
 * 请主意，在web-app中，listener元素位于所有的servlet 元素之前以及所有filter-mapping元素之后。
 * 
 * @author james
 * 
 */
public class FriendListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		try {

			System.out.println("Friend Listener starting........");
			System.out.println("====================================");

			//启动频道聊天监控线程
			System.out.println("Queue check thread start.....");
			SocialFriendListQueueCheckDeamon queue = new SocialFriendListQueueCheckDeamon();
			queue.start();
			System.out.println("Queue check thread start over.....");
			System.out.println("====================================");
			
			System.out.println("Friend Listener over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}
