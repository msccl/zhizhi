package com.qkzz.online.service.bo;

import java.util.List;

public class OnlineOpenGame {
	
	private List<Integer> list;//游戏列表
	private int page;//页码
	private int total;//总量
	private int count;//每页数量
	private int freshinterval;//刷新时间
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFreshinterval() {
		return freshinterval;
	}
	public void setFreshinterval(int freshinterval) {
		this.freshinterval = freshinterval;
	}
}
