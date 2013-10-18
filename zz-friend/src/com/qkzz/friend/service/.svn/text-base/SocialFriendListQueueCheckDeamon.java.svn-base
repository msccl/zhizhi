package com.qkzz.friend.service;

import java.util.List;

import com.qkzz.friend.bean.FriendUser;
import com.qkzz.friend.bean.SocialFriendListQueue;
import com.qkzz.friend.bean.SocialFriendUser;
import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.user.bean.Member;
import com.qkzz.user.service.MemberService;

public class SocialFriendListQueueCheckDeamon extends Thread {
	
	private static boolean isRunning = false;//是否正在运行

	public void run() {
		while(true) {
			try {
				if(isRunning) {
					continue;
				}
				checkQueue();
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
	 * 检查社交好友列表队列中是否有需要处理的内容
	 */
	private static void checkQueue() {
		List<SocialFriendListQueue> queueList = SocialFriendUserService.getQueueList(50);
		if(queueList == null || queueList.isEmpty()) {
			return;
		}
		
		isRunning = true;
		
		for(int i=0;i<queueList.size();i++) {
			SocialFriendListQueue queue = queueList.get(i);
			
			String domain = queue.getDomain();//社交网站域名
			long uid = queue.getUid();//用户UID
			
			System.out.println("friendlist===="+queue.getFrdlist());
			//由于每个网站的用户好友列表格式不同，需要按照domain分别处理
			SocialFriendListHandle handle = null;
			if(queue.getDomain().equals("renren.com")) {
				//人人网
				handle = new RenRenFriendListHandle();
			} else if(queue.getDomain().equals("pengyou.com")) {
				//朋友网
				handle = new PengyouFriendListHandle();
			}

			List<SocialFriendUserListInfo> list = handle.getList(queue.getFrdlist());
			if(list != null && list.size() > 0) {
				for(int k=0;k<list.size();k++) {
					//判断是否激活，如果激活，判断是否已经加入吱吱好友列表
					SocialFriendUserListInfo social = list.get(k);
					String suid = social.getUid();
					String sname= social.getName();
					
					Member member = MemberService.getDoaminUser(suid, domain);
					if(member != null) {
						//该用户已经激活并加入了吱吱用户，继续判断是否已经在该用户的吱吱好友列表中
						if(!FriendUserService.isFriendUser(uid, member.getId())) {
							//还不是吱吱还有，加入
							FriendUser obj = new FriendUser();
							obj.setUid(uid);
							obj.setFuid(member.getId());
							obj.setFname(member.getName());
							//获取用户好友默认分组ID
							long groupid = FriendGroupService.getDefaultGroupID(uid);
							obj.setGroupid(groupid);
							int status = FriendUserService.save(obj);

							if(status != -1) {
								//成功加入后判断是否在社交好友列表中，如果存在则删除
								if(SocialFriendUserService.getSocialFriendUser(uid, suid, domain) != null) {
									SocialFriendUserService.delete(uid, suid, domain);
								}
							}
						}
						
					} else {
						//用户尚未激活，直接加入该用户的社交好友列表中
						SocialFriendUser obj = new SocialFriendUser();
						obj.setUid(uid);
						obj.setFuid(suid);
						obj.setFname(sname);
						obj.setSex(social.getSex());
						obj.setFace(social.getFace());
						obj.setBirth(social.getBirth()==null?"":social.getBirth());
						obj.setDomain(domain);
						SocialFriendUserService.add(obj);
					}
				}
			}
			
			//处理完成后更改记录状态为已处理
			SocialFriendUserService.changeListQueueStatus(queue.getId(), 1);
		}
		isRunning = false;
		System.out.println("Haldle social Friend user list......");
	}

	
}
