package com.qkzz.user.dao;

import com.qkzz.user.bean.ReportList;

public interface ReportListDao {

	/**
	 * 添加举报记录
	 * @param bean
	 * @return
	 */
	int addReport(ReportList bean);
	
	/**
	 * 获取用户被举报的次数
	 * 如果是5的倍数，则加入黑名单列表，开始屏蔽，禁言
	 * @param uid
	 * @return
	 */
	int getReportNum(long uid);
	
	/**
	 * 是否可以举报
	 * 同一IP的用户在10分钟内不能重复举报同一个用户
	 * @param reportuid
	 * @param ip
	 * @return
	 */
	boolean canAddReport(long reportuid,long uid,String ip);
}
