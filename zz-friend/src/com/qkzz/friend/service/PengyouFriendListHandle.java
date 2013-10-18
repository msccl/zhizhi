package com.qkzz.friend.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.bean.bo.PengyouFriend;
import com.qkzz.friend.service.SocialFriendListHandle;

/**
 * 人人网好友列表解析
 */
public class PengyouFriendListHandle implements SocialFriendListHandle {

	@Override
	public List<SocialFriendUserListInfo> getList(
			String socialSiteFriendListString) {

		List<SocialFriendUserListInfo> ret = new ArrayList<SocialFriendUserListInfo>();

		JSONObject json = JSONObject.fromObject(socialSiteFriendListString);

		if(json.get("items") != null) {
			JSONArray jarr=JSONArray.fromObject(json.get("items"));
			List<PengyouFriend> items = (List<PengyouFriend>)jarr.toCollection(jarr,PengyouFriend.class);
			if(items != null && items.size() > 0) {
				for(int i=0;i<items.size();i++) {
					PengyouFriend item = items.get(i);
					System.out.println(item.getOpenid()+"==="+item.getNickname()+"==="+item.getFigureurl());
					SocialFriendUserListInfo info = new SocialFriendUserListInfo();
					info.setUid(item.getOpenid());
					info.setName(item.getNickname());
					info.setFace(item.getFigureurl());
					String sexStr = item.getGender();
					int sex = 0;
					if(sexStr != null && sexStr.equals("女")) {
						sex = 1;
					}
					info.setSex(sex);
					info.setFriendtype(1);
					ret.add(info);
				}
			}
		}

		if(ret.isEmpty()) {
			return null;
		}
		return ret;
	}

	public static void main(String[] args) {
		String abc = "{\"ret\":0,\"items\":[{\"openid\":\"08B9999CACFBE0D9F57CAB4E7D8BDBF0\",\"nickname\":\"Jane\",\"gender\":\"女\",\"figureurl\":\"http://imgcache.qq.com/qzone_v4/client/userinfo_icon/11111111.gif\",\"is_vip\":false},{\"openid\":\"08B9999CACFBE0D9F57CAB4E7D8BDBF0\"},{\"openid\":\"83A390F6A8E66BA800829ECD6032A6DE\",\"nickname\":\"Jim\",\"gender\":\"男\",\"figureurl\":\"http://imgcache.qq.com/qzone_v4/client/userinfo_icon/22222222.gif\",\"is_vip\":true,\"is_year_vip\":true,\"vip_level\":5}]}";

		PengyouFriendListHandle h = new PengyouFriendListHandle();
		h.getList(abc);
	}
}
