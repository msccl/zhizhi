package com.qkzz.web.developer.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.developer.bean.Tools;
import com.qkzz.web.developer.dao.ToolsDao;

public class ToolsService {

	private static ToolsDao dao = ActiveBean.getBean("toolsDaoImpl", ToolsDao.class);

	/**
	 * 添加道具
	 * @param bean
	 * @return
	 */
	public static int addTools(Tools bean) {
		return dao.addTools(bean);
	}
	
	
	/**
	 * 获取最新添加的道具ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public static int getLastToolsID(int gameid,String name) {
		return dao.getLastToolsID(gameid, name);
	}
	
	/**
	 * 编辑道具
	 * @param bean
	 * @return
	 */
	public static int editTools(Tools bean) {
		return dao.editTools(bean);
	}
	
	/**
	 * 删除道具
	 * @param id
	 * @return
	 */
	public static int delTools(int gameid,int id) {
		return dao.delTools(gameid, id);
	}
	
	/**
	 * 修改道具状态
	 * @param gameid
	 * @param id
	 * @param status
	 * @return
	 */
	public static int changeStatus(int gameid,int id,int status) {
		return dao.changeStatus(gameid, id, status);
	}
	
	/**
	 * 根据ID获取道具信息
	 * @param id
	 * @return
	 */
	public static Tools getTools(int gameid,int id) {
		return dao.getTools(gameid, id);
	}
	
	/**
	 * 获取某个游戏的道具列表
	 * @param gameid
	 * @return
	 */
	public static List<Tools> getGameTools(int gameid,int startIndex,int num) {
		return dao.getGameTools(gameid,startIndex,num);
	}

	
	/**
	 * 获取游戏道具总量
	 * @param gameid
	 * @return
	 */
	public static int getGameToolsMaxCount(int gameid) {
		return dao.getGameToolsMaxCount(gameid);
	}
}
