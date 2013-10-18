package com.qkzz.online.service.bo;

import java.util.List;

import com.qkzz.online.bean.Online;

/**
 * 主要用于缓存用户列表
 * 
 * @author not attributable
 * @version 1.0
 */
public class OnlineLastList {

	private List<Online> list;// 主题列表
	private long lastUpdateTime = System.currentTimeMillis();// 缓存开始时间，过期后重新读取

	public List<Online> getList() {
		return list;
	}

	public void setList(List<Online> list) {
		this.list = list;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}




}
