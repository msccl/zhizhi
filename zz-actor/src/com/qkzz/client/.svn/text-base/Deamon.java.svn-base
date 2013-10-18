package com.qkzz.client;

import java.util.List;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.service.QueueService;
import com.qkzz.online.service.RoundService;

public class Deamon implements Runnable {
	
	private static boolean isRuning = false;

	@Override
	public void run() {
		for(;;){
			//System.out.println(new java.util.Date());
			try {
				if(!isRuning){
					process();
				}
				Thread.sleep(500);
			} catch (Exception ex) {
				isRuning = false;
				ex.printStackTrace();
			}
        }
	}
	
	/**
	 * 操作部分
	 */
	void process() {
		isRuning = true;
		//long count = QueueService.countByList();
		//获取对应分页用户列表
		List<Queue> list = QueueService.getByList(0, 10);
		if(!list.isEmpty()){
			//遍历对应分页用户列表
			for(Queue obj: list){
				//System.out.println(obj.getId()+",game:"+obj.getGameid()+",uid:"+obj.getUid()+",name:"+obj.getName());
				if(RoundService.addOrUpdate(obj)){
					QueueService.delete(obj.getId());
				}
			}
		}
		isRuning = false;
	}


}
