package com.qkzz.game.service.bo;

import java.util.List;

public class EntityListBean<T> {
	
	private int status;
	private List<T> list;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
