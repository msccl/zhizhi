package com.qkzz.user.service;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.qkzz.common.HttpUtil;
import com.qkzz.common.MD5;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.online.bean.OnlineQueue;
import com.qkzz.online.service.OnlineQueueService;
import com.qkzz.online.service.OnlineService;
import com.qkzz.user.bean.User;

public class GuestUserService {
	
	/**
	 * 根据临时ID，从在线表中获取游客用户对象
	 * @param id
	 * @return
	 */
//	public static User getByID(long id, HttpServletRequest request){
//		//判断是否在线列表中
//		if(OnlineService.isGlobalOnlne(id)){
//			//如果有，更新用户状态，获取用户对象，并返回
//			User obj = OnlineService.getByUser(id);
//			//调用写在线队列方法
//			createOnlineQueue(obj, request);
//			return obj;
//		}
//		return null;
//	}
	

	public static User getByID(long id){
		//判断是否在线列表中
		if(OnlineService.isGlobalOnlne(id)){
			//如果有，更新用户状态，获取用户对象，并返回
			User obj = OnlineService.getByUser(id);
			return obj;
		}
		return null;
	}

	
	/**
	 *  创建游客用户对象
	 * @param name
	 * @return
	 */
	public static User createUser(String name, HttpServletRequest request,String sign){
		User obj = new User();
		obj.setUid(Rand(90000000l,999999999));
		obj.setName(name);
		//调用写在线队列方法
		createOnlineQueue(obj, request,sign);
		return obj;
	}

	/**
	 * 添加到对列表
	 * @param user
	 * @param request
	 */
	public static void createOnlineQueue(User user, HttpServletRequest request,String sign){
		
		String gamecode = HttpUtil.getString(request, "gameid");
		int gameid = GameInfoService.getGameIDByGameCode(gamecode);//如果通过网页登陆，有可能不会传递gameid，默认为0
		String url = HttpUtil.getString(request, "url");//用户当前所在url
		
		
		//加入在线
		OnlineQueue queue = new OnlineQueue();
		queue.setGameid(gameid);
		queue.setUid(user.getUid());
		queue.setName(user.getName());
		queue.setLasttime(System.currentTimeMillis());
		queue.setLasturl(url);
		queue.setStatus(1);//全局在线
		queue.setSign(sign);
		OnlineQueueService.addToQueue(queue);
	}
	
	
	/**
	 * 产生随机数(等介于min,max)
	 * @param min
	 * @param max
	 * @return
	 */
    public static long Rand(long min, long max) {
        Random random = new Random();
        return Math.abs(random.nextLong()) % ((max - min) + 1) + min;
    }

	
}
