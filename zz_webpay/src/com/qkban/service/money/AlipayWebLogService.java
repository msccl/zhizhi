package com.qkban.service.money;

import java.util.List;

import com.qkban.bean.money.AlipayWebLogBean;
import com.qkban.dao.money.AlipayWebLogDao;
import com.qkban.util.ActiveBean;

public class AlipayWebLogService {

	public static AlipayWebLogDao alipayLogInst = (AlipayWebLogDao)ActiveBean.getBean("alipayWebLogDaoImpl");

	/**
	 * 添加充�?�记�?
	 * @param indentnumber
	 * @param userid
	 * @param money
	 * @param ip
	 * @return
	 */
	public static int addLog(String indentnumber,String userid,double money,String ip) {
		return alipayLogInst.addLog(indentnumber, userid, money, ip);
	}
	
	
	/**
	 * 用户log�?大数�?
	 * @param uid
	 * @return
	 */
	public static int logMaxCount(String userid) {
		return alipayLogInst.logMaxCount(userid);
	}
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public static List<AlipayWebLogBean> getMoneyLogList(String userid,int start,int size) {
		return alipayLogInst.getMoneyLogList(userid, start, size);
	}


	/**
	 * 充�?�记录�?�量
	 * @return
	 */
	public static int logMaxCount() {
		return alipayLogInst.logMaxCount();
	}
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public static List<AlipayWebLogBean> getMoneyLogList(int start,int size) {
		return alipayLogInst.getMoneyLogList(start, size);
	}

}
