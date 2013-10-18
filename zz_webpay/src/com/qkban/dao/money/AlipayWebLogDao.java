package com.qkban.dao.money;

import java.util.List;

import com.qkban.bean.money.AlipayWebLogBean;

public interface AlipayWebLogDao {

	/**
	 * 添加充�?�记�?
	 * @param indentnumber
	 * @param userid
	 * @param money
	 * @param ip
	 * @return
	 */
	public int addLog(String indentnumber,String userid,double money,String ip);
	
	
	/**
	 * 用户log�?大数�?
	 * @param uid
	 * @return
	 */
	public int logMaxCount(String userid);
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<AlipayWebLogBean> getMoneyLogList(String userid,int start,int size);


	/**
	 * 充�?�记录�?�量
	 * @return
	 */
	public int logMaxCount();
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<AlipayWebLogBean> getMoneyLogList(int start,int size);

}
