package com.qkzz.game.service;

import java.util.List;

import com.qkzz.game.bean.GameTools;
import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.dao.UserToolboxDao;
import com.qkzz.game.dao.impl.UserToolboxDaoImpl;
import com.qkzz.game.service.bo.UserboxTools;


public class UserToolboxService {
	
	private static UserToolboxDao dao = new UserToolboxDaoImpl();

	/**
	 * 某用户道具总数
	 * @param uid
	 * @return
	 */
	public static int countByList(long uid){
		return dao.countByList(uid);
	}

	/**
	 * 某用户道具列表
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<UserToolbox> getByList(long uid, int first, int max){
		return dao.getByList(uid, first, max);
	}

	/**
	 * 某用户某游戏道具总数
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public static int countByList(long uid, int gameid){
		return dao.countByList(uid, gameid);
	}

	/**
	 * 某用户某游戏道具列表
	 * @param uid
	 * @param gameid
	 * @param first
	 * @param max
	 * @return
	 */
	public static List<UserToolbox> getByList(long uid, int gameid, int first, int max){
		return dao.getByList(uid, gameid, first, max);
	}
	
	/**
	 * 获取道具箱中所有道具的详细信息，不需要分页
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public static List<UserboxTools> getAllToolsList(long uid,int gameid) {
		return dao.getAllToolsList(uid, gameid);
	}
	
	/**
	 * 获取道具箱中排除当前游戏的其他所有道具的详细信息，不需要分页
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public static List<UserboxTools> getAllOtherGameToolsList(long uid,int gameid) {
		return dao.getAllOtherGameToolsList(uid, gameid);
	}

	
	/**
	 * 获取某用户游戏中的某个道具信息
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @return
	 */
	public static UserToolbox getByInfo(long uid, int gameid, int toolsid){
		return dao.getByInfo(uid, gameid, toolsid);
	}
	
	
	/**
	 * 获取某用户游戏中的某个道具信息
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @return
	 */
	public static UserboxTools getToolsDetail(long uid, int gameid, int toolsid){
		UserboxTools ret = new UserboxTools();
		
		UserToolbox utb = dao.getByInfo(uid, gameid, toolsid);
		if(utb == null) {
			return null;
		}
		
		GameTools gt = GameToolsService.getById(gameid, toolsid);
		if(gt == null) {
			return null;
		}
		
		ret.setId(toolsid);
		ret.setUid(uid);
		ret.setGameid(gameid);
		ret.setName(gt.getName());
		ret.setIntro(gt.getIntro());
		ret.setImg(gt.getImg());
		ret.setCanexchange(gt.getCanexchange());
		ret.setCanauction(gt.getCanauction());
		ret.setFunctiondefine(gt.getFunctiondefine());
		ret.setMoneyid(gt.getMoneyid());
		ret.setPrice(gt.getPrice());
		ret.setNum(utb.getNum());
		ret.setCreatetime(utb.getCreatetime());

		return ret;
	}

	
	
	/**
	 * 某用户某游戏增加某道具数量
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @param num
	 * @return
	 */
	public static int incToolsNum(long uid, int gameid, int toolsid, int num){
		//判断用户是否已有某游戏中的某个道具
		if(dao.isValidateTool(uid, gameid, toolsid)){
			System.out.println("11111111111111111111111");
			return dao.incToolsNum(uid, gameid, toolsid, num);
		}else{
			UserToolbox obj = new UserToolbox();
			obj.setUid(uid);
			obj.setGameid(gameid);
			obj.setToolsid(toolsid);
			obj.setNum(num);
			obj.setGamename("游戏名称...");
			System.out.println("22222222222222222");
			return dao.add(obj);
		}
	}
	
	/**
	 * 某用户某游戏减少某道具数量
	 * @param uid
	 * @param gameid
	 * @param toolsid
	 * @param num
	 * @return
	 */
	public static int decToolsNum(long uid, int gameid, int toolsid, int num){
		int status = dao.decToolsNum(uid, gameid, toolsid, num);
		if(status>0){
			//移除用户道具数量为零的记录
			dao.removeToolsNumZero(uid);
		}
		return status;
	}

	public static int createTable(){
		for(int i=0; i<16; i++){
			dao.createTable0(i);
		}
		for(int i=0; i<16; i++){
			dao.createTable1(i);
		}
		for(int i=0; i<256; i++){
			dao.createTable2(i);
		}
		return 1;
	}
	
	
	public static void main(String [] args){

	}
	
	
}
