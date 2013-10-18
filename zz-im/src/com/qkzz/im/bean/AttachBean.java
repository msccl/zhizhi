package com.qkzz.im.bean;

import java.util.List;

public class AttachBean {

	private int gameid;//游戏ID
	private List<ItemBean> items;//道具列表
	private List<ItemBean> coins;//货币列表

	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public List<ItemBean> getItems() {
		return items;
	}
	public void setItem(List<ItemBean> items) {
		this.items = items;
	}
	public List<ItemBean> getCoins() {
		return coins;
	}
	public void setCoin(List<ItemBean> coins) {
		this.coins = coins;
	}
	
}
