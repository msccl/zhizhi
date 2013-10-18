package com.qkzz.web.stat.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.stat.bean.StatGame;
import com.qkzz.web.stat.dao.StatGameDao;

public class StatGameService {
	
	private static StatGameDao dao = ActiveBean.getBean("statGameDaoImpl", StatGameDao.class);
	
	/**
	 * 获取指定日期段的数据总行数
	 * @param gamecode
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	public static int countByList(String gamecode, String begindate, String enddate){
		return dao.countByList(gamecode, begindate, enddate);
	}
	
	/**
	 * 获取指定日期段的数据列表
	 * @param gamecode
	 * @param begindate
	 * @param enddate
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<StatGame> getByList(String gamecode, String begindate, String enddate, int first, int max){
		return dao.getByList(gamecode, begindate, enddate, first, max);
	}
	
	/**
	 * 获取指定日期的game_code列表
	 * @param date
	 * @return
	 */
	public static List<String> getByGamecodeList(String date){
		return dao.getByGamecodeList(date);
	}
		
	/**
	 * 获取指定game_code和日期的统计数据
	 * @param gamecode
	 * @param date
	 * @return
	 */
	public static StatGame getByGamecode(String gamecode, String date){
		return dao.getByGamecode(gamecode, date);
	}
	
	public static void main(String [] args){
		String date = "2011-09-07";
		List<String> objs = StatGameService.getByGamecodeList(date);
		if(!objs.isEmpty()){
			for(String gamecode: objs){
				int count = StatGameService.countByList(gamecode, date, date);
				System.out.println(count);
				List<StatGame> list = StatGameService.getByList(gamecode, date, date, 0, 10);
				if(!list.isEmpty()){
					for(StatGame obj: list){
						System.out.println(obj.toString());
					}
				}
			}
		}
	}
	
}
