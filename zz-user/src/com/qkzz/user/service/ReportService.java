package com.qkzz.user.service;

import com.qkzz.chat.bean.ChatContentLog;
import com.qkzz.chat.service.ChatContentLogService;
import com.qkzz.common.DateTrans;
import com.qkzz.user.bean.BlackUser;
import com.qkzz.user.bean.ReportList;
import com.qkzz.user.dao.ReportListDao;
import com.qkzz.user.dao.impl.ReportListDaoImpl;

/**
 * 举报service
 * @author dell
 *
 */
public class ReportService {
	private static ReportListDao dao = new ReportListDaoImpl();
	
	/**
	 * 添加举报记录
	 * @param bean
	 * @return
	 */
	public static int addReport(ReportList bean) {
		int ret = dao.addReport(bean);
		if(ret != -1) {

			if(bean.getReportuid() == 1004 || bean.getReportuid() == 1005) {
				//如果是gm或者gamemaster账户，举报之后直接禁言
				int minutes = 120;

				BlackUser blackUser = new BlackUser();
				blackUser.setUid(bean.getUid());
				blackUser.setStarttime(DateTrans.getLong2Str(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
				blackUser.setEndtime(DateTrans.getLong2Str(System.currentTimeMillis()+minutes*60*1000, "yyyy-MM-dd HH:mm:ss"));
				blackUser.setIp(bean.getIp());
				
				BlackService.addBlack(blackUser);
			} else {
				int reportNum = dao.getReportNum(bean.getUid());
				if(reportNum > 0 && reportNum%5 == 0) {
					//举报达到5的倍数时，开始执行禁言操作

					//被禁言的时间长度，单位：分钟
//					int minutes = 30+10*(reportNum/5-1);
					int minutes = 120;

					BlackUser blackUser = new BlackUser();
					blackUser.setUid(bean.getUid());
					blackUser.setStarttime(DateTrans.getLong2Str(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
					blackUser.setEndtime(DateTrans.getLong2Str(System.currentTimeMillis()+minutes*60*1000, "yyyy-MM-dd HH:mm:ss"));
					blackUser.setIp(bean.getIp());
					
					BlackService.addBlack(blackUser);
				}
			}
		}
		return ret;
	}
	
	/**
	 * 获取用户被举报的次数
	 * 如果是5的倍数，则加入黑名单列表，开始屏蔽，禁言
	 * @param uid
	 * @return
	 */
	public static int getReportNum(long uid) {
		return dao.getReportNum(uid);
	}
	
	/**
	 * 是否可以举报
	 * 同一IP的用户在10分钟内不能重复举报同一个用户
	 * @param reportuid
	 * @param ip
	 * @return
	 */
	public static boolean canAddReport(long reportuid,long uid,String ip) {
		return dao.canAddReport(reportuid, uid, ip);
	}

}
