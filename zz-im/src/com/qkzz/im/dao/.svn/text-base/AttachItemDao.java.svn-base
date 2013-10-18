package com.qkzz.im.dao;

import java.util.List;

import com.qkzz.im.bean.AttachItem;

public interface AttachItemDao {

	/**
	 * 添加附件
	 * @param bean
	 * @return
	 */
	int addItem(AttachItem bean);
	
	/**
	 * 获取附件内容列表
	 * @param attachid
	 * @return
	 */
	List<AttachItem> getItemList(int attachid);
	
	/**
	 * 更改附件领取状态
	 * @param attachid
	 * @param id
	 * @param status
	 * @return
	 */
	int changeStatus(int attachid,int id,int status);
	
	/**
	 * 获取附件内容信息
	 * @param attach
	 * @param type
	 * @param itemid
	 * @return
	 */
	AttachItem getItem(int attachid,int type,int itemid);
}
