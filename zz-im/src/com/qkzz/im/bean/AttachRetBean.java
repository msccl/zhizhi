package com.qkzz.im.bean;

import java.util.List;

public class AttachRetBean {
	
	private int result;//返回值
	private String gameid;//游戏gamecode
	private List<ItemListBean> items;//道具列表
	private List<ItemBean> coins;//货币列表
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<ItemListBean> getItems() {
		return items;
	}
	public void setItems(List<ItemListBean> items) {
		this.items = items;
	}
	public List<ItemBean> getCoins() {
		return coins;
	}
	public void setCoins(List<ItemBean> coins) {
		this.coins = coins;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	
	
}
