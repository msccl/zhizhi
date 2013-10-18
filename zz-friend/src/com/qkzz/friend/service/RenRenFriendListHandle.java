package com.qkzz.friend.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.bean.bo.RenRenFriend;
import com.qkzz.friend.service.SocialFriendListHandle;

/**
 * 人人网好友列表解析
 */
public class RenRenFriendListHandle implements SocialFriendListHandle {

	@Override
	public List<SocialFriendUserListInfo> getList(
			String socialSiteFriendListString) {

		List<SocialFriendUserListInfo> ret = new ArrayList<SocialFriendUserListInfo>();
		JSONArray jarr=JSONArray.fromObject(socialSiteFriendListString);
		
		List<RenRenFriend> items = (List<RenRenFriend>)jarr.toCollection(jarr,RenRenFriend.class);
		if(items != null && items.size() > 0) {
			for(int i=0;i<items.size();i++) {
				RenRenFriend item = items.get(i);
				System.out.println(item.getId()+"==="+item.getName()+"==="+item.getTinyurl());
				SocialFriendUserListInfo info = new SocialFriendUserListInfo();
				info.setUid(item.getId());
				info.setName(item.getName());
				info.setFace(item.getHeadurl());
				info.setFriendtype(1);
				ret.add(info);
			}
		}
		
		if(ret.isEmpty()) {
			return null;
		}
		return ret;
	}

	public static void main(String[] args) {
		String abc = "[{\"id\":27740660,\"name\":\"Ramos\",\"headurl\":\"...\" ,\"tinyurl\":\"...\",\" headurl_with_logo\":\"http://.....\" ,\"tinyurl_with_logo\":\"http://.....\"},{\"id\":27740663,\"name\":\"Ramosaaa\",\"headurl\":\"...\",\"tinyurl\":\"...\",\" headurl_with_logo\":\"http://.....\" ,\"tinyurl_with_logo\":\"http://.....\"}]";

		RenRenFriendListHandle h = new RenRenFriendListHandle();
		h.getList(abc);
	}
}
