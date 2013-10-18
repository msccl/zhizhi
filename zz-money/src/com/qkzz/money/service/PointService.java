package com.qkzz.money.service;

import java.util.List;

import com.qkzz.common.Tools;
import com.qkzz.money.bean.PointLogBean;
import com.qkzz.money.dao.PointDao;
import com.qkzz.money.dao.PointLogDao;
import com.qkzz.money.dao.impl.PointDaoImpl;
import com.qkzz.money.dao.impl.PointLogDaoImpl;


public class PointService {

	public static PointDao pointInst = new PointDaoImpl();
	public static PointLogDao pointLogInst = new PointLogDaoImpl();

	/**
	 * 增加积分
	 * @param uid ：用户ID
	 * @param point ：积分
	 * @param productid ：产品ID
	 * @param remark ：注释
	 * @param cls ：类名，通常是this
	 * @return
	 */
	public static int incPoint(int uid, int point, int productid, String remark,Object cls) {

		//游客状态下不能操作
//		if (UserService.isGuest(uid)) {
//			return -1;
//		}

		if (point < 0) {
			return -2;
		}
		
		int userPoint = PointService.getPoint(uid);
		if (userPoint < 0) {
			return -3;
		}
		
		if(pointInst.incPoint(uid, point) != -1) {
			// add point inc log
			remark = remark + "@" + Tools.getLocalHostName()
					+ (cls == null ? "-NoClass:" : cls.getClass().getName())
					+ ":" + userPoint;
			PointService.addLog(uid, point, productid,1,remark);
			return 0;
		}
		return -4;
	}
	
	
	/**
	 * 减少积分数量
	 * @param uid ：用户ID
	 * @param point ：积分
	 * @param productid ：对应产品ID
	 * @param remark ： 注释
	 * @param cls ：类名 通常是this
	 * @return
	 */
	public static int decPoint(int uid, int point, int productid, String remark,Object cls) {
		//游客状态下不能操作
//		if (UserService.isGuest(uid)) {
//			return -1;
//		}
		if (point < 0) {
			return -2;
		}

		int userPoint = PointService.getPoint(uid);
		if (userPoint < point || userPoint < 0) {
			return -3;
		}
		if (pointInst.decPoint(uid, point) != -1) {
			remark = remark + "@" + Tools.getLocalHostName()
					+ (cls == null ? "-NoClass:" : cls.getClass().getName())
					+ ":" + userPoint;
			PointService.addLog(uid, point, productid,2,remark);
			return 0;
		}
		return -4;
	}
	
	/**
	 * 获取用户积分
	 * @param uid
	 * @return
	 */
	public static int getPoint(int uid) {
		return pointInst.getPoint(uid);
	}

	/**
	 * 添加积分账户
	 * @param uid
	 * @return
	 */
	public static int addPointAccount(int uid) {
		return pointInst.addPointAccount(uid);
	}

	/**
	 * 更改积分值
	 * @param uid
	 * @param point
	 * @return
	 */
	public static int modifyPoint(int uid,int point) {
		return pointInst.modifyPoint(uid, point);
	}
	
	/**
	 * 增加积分log
	 * @param uid ：用户ID
	 * @param point ：积分
	 * @param productid ：对应产品ID
	 * @param transtype ：积分转换类型 1：增加 2：减少
	 * @param remark : 注释
	 * @return
	 */
	public static int addLog(int uid, int point, int productid,int transtype,String remark) {
		PointLogBean log = new PointLogBean();
		log.setUid(uid);
		log.setAmount(point);
		log.setRemark(remark);
		log.setTranstype(transtype);
		return pointLogInst.add(log);
	}

	/**
	 * 获取积分log记录
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public static List<PointLogBean> getPointLogList(int uid, int start, int size) {
		return pointLogInst.getPointLogList(uid, start, size);
	}
	

	/**
	 * 积分log总量
	 * @param uid
	 * @return
	 */
	public static int logMaxCount(int uid) {
		return pointLogInst.logMaxCount(uid);
	}

}
