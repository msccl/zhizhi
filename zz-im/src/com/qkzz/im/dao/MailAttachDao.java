package com.qkzz.im.dao;

import com.qkzz.im.bean.MailAttach;

public interface MailAttachDao {

	/**
	 * 添加附件描述记录
	 * @param bean
	 * @return
	 */
	int add(MailAttach bean);
	
	/**
	 * 获取最后添加的附件描述记录ID
	 * @param receiveuid
	 * @return
	 */
	int getLastInsertID(long receiveuid);
	
	/**
	 * 获取附件描述详情
	 * @param receiveuid
	 * @param attachid
	 * @return
	 */
	MailAttach getAttach(long receiveuid,int attachid);
}
