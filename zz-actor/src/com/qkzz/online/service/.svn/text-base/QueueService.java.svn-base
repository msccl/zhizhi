package com.qkzz.online.service;

import java.util.List;

import com.qkzz.common.ActiveBean;
import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.QueueDao;


public class QueueService {

	private static QueueDao dao = ActiveBean.getBean("queueDaoImpl", QueueDao.class);
	
	public static int delete(long id){
		return dao.delete(id);
	}
	
	public static long countByList(){
		return dao.countByList();
	}
	
	public static List<Queue> getByList(long first, long max){
		return dao.getByList(first, max);
	}
	
	public static void main(String [] args){
		//获取对应分页用户列表
		List<Queue> list = QueueService.getByList(0, 2);
		if(!list.isEmpty()){
			//遍历对应分页用户列表
			for(Queue obj: list){
				System.out.println("game:"+obj.getGameid()+",uid:"+obj.getUid()+",name:"+obj.getName());
				if(RoundService.addOrUpdate(obj)){
					QueueService.delete(obj.getId());
				}
			}
		}
	}
	
}
