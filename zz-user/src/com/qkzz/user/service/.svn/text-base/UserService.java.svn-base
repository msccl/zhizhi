package com.qkzz.user.service;

import com.qkzz.common.LRUMap;
import com.qkzz.common.Tools;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.online.service.OnlineService;
import com.qkzz.user.bean.Member;
import com.qkzz.user.bean.User;
import com.qkzz.user.dao.MemberDao;
import com.qkzz.user.dao.UserDao;
import com.qkzz.user.dao.impl.MemberDaoImpl;
import com.qkzz.user.dao.impl.UserDaoImpl;


public class UserService {

	public static MemberDao mdao = new MemberDaoImpl();
	public static UserDao dao = new UserDaoImpl();
	
	public static LRUMap<Long, User> userMap = new LRUMap<Long, User>(10000);

	/**
	 * 从缓存中读取用户信息，如果用户信息更新，需要更新缓存
	 * @param uid
	 * @return
	 */
	public static User getByUid(long uid){
		if(userMap.containsKey(uid)) {
			User user = userMap.get(uid);
			if(Tools.isExpired(user.getLastUpdateTime(), 180)) {

				user = dao.getByUid(uid);
				if(user != null) {
					Member member = MemberService.getById(uid);
					if(member != null) {
						user.setName(member.getName());
						user.setMobile(member.getMobile());
						user.setDomainuid(member.getDomainuid());
						user.setDomain(member.getDomain());
					}
					user.setLastUpdateTime(System.currentTimeMillis());
					user.setIsonline(OnlineService.isGlobalOnlne(uid)?1:0);
					int playingGameID = OnlineService.getPlayingGameID(uid);
					user.setGameid(playingGameID);
					user.setGamename(GameInfoService.getName(playingGameID));
					userMap.put(uid, user);
				}
//				user.setIsonline(OnlineService.isGlobalOnlne(uid)?1:0);
//				int playingGameID = OnlineService.getPlayingGameID(uid);
//				user.setGameid(playingGameID);
//				user.setGamename(GameInfoService.getName(playingGameID));
//				userMap.put(uid, user);
			}
			return user; 
		}
		User user = dao.getByUid(uid);
		if(user != null) {
			Member member = MemberService.getById(uid);
			if(member != null) {
				user.setName(member.getName());
				user.setMobile(member.getMobile());
				user.setDomainuid(member.getDomainuid());
				user.setDomain(member.getDomain());
			}
			user.setLastUpdateTime(System.currentTimeMillis());
			user.setIsonline(OnlineService.isGlobalOnlne(uid)?1:0);
			int playingGameID = OnlineService.getPlayingGameID(uid);
			user.setGameid(playingGameID);
			user.setGamename(GameInfoService.getName(playingGameID));
			userMap.put(uid, user);
			return user;
		}
		return null;
	}

	/**
	 * 更新用户信息
	 * @param obj
	 * @return
	 */
	public static int updateUserInfo(User obj){
		int ret = dao.update(obj);
		//更新缓存
		if(userMap.containsKey(obj.getUid())) {
			User user = dao.getByUid(obj.getUid());
			if(user != null) {
				Member member = MemberService.getById(obj.getUid());
				if(member != null) {
					user.setName(member.getName());
					user.setMobile(member.getMobile());
				}
				userMap.put(obj.getUid(), user);
			}
		}
		return ret;
	}
	
	/**
	 * 添加用户
	 * @param obj
	 * @return
	 */
	public static int addUser(User obj){
		return dao.add(obj);
	}
	
	/**
	 * 更新用户心情
	 * @param uid
	 * @param mood
	 * @return
	 */
	public static int updateUserMood(long uid, String mood){
		 int status = dao.updateMood(uid, mood);
		 if(status!=-1){
			 UserMoodService.add(uid, mood);
		 }
		 return status;
	}

	/**
	 * 更新用户头像
	 * @param uid
	 * @param face
	 * @return
	 */
	public static int updateFace(long uid, String face){
		return dao.updateFace(uid, face);
	}

	
	/**
	 * 添加登陆信息
	 * @param obj
	 * @return
	 */
	public static int addMember(Member obj){
		return mdao.add(obj);
	}

	/**
	 * update Member Object
	 * @param obj
	 * @return
	 */
	public static int updateMember(Member obj){
		return mdao.update(obj);
	}

	/**
	 * get User name
	 * @param uid
	 * @return
	 */
	public static String getName(long uid){
		return mdao.getName(uid);
	}
	
	public static User getByLogin(String name, String password){
		Member obj = mdao.getByLogin(name, password);
		if(obj==null){
			return null;
		}
		return defaultUser(obj);
	}
	
	private static User defaultUser(Member obj){
		User user = UserService.getByUid(obj.getId());
		if(user != null) {
			user.setName(obj.getName());
		}
		return user;
	}
	
	
}
