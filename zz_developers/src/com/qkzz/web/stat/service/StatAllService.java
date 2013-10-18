package com.qkzz.web.stat.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.stat.bean.StatAll;
import com.qkzz.web.stat.dao.StatAllDao;

public class StatAllService {
	
	private static StatAllDao dao = ActiveBean.getBean("statAllDaoImpl", StatAllDao.class);
	
	/**
	 * 获取指定日期段的数据总行数
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	public static int countByList(String begindate, String enddate){
		return dao.countByList(begindate, enddate);
	}
	
	/**
	 * 获取指定日期段的数据列表
	 * @param begindate
	 * @param enddate
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<StatAll> getByList(String begindate, String enddate, int first, int max){
		return dao.getByList(begindate, enddate, first, max);
	}
	
	public static void main(String [] args){
		String begindate = "2011-09-07";
		String enddate = "2011-09-07";
		int count = StatAllService.countByList(begindate, enddate);
		System.out.println(count);
		List<StatAll> list = StatAllService.getByList(begindate, enddate, 0, 10);
		if(!list.isEmpty()){
			for(StatAll obj: list){
				System.out.println(obj.toString());
			}
		}
		
	}
	
}
