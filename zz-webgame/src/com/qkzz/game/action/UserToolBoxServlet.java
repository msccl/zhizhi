package com.qkzz.game.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.common.HttpUtil;
import com.qkzz.game.bean.GameTools;
import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameToolsService;
import com.qkzz.game.service.UserToolboxService;
import com.qkzz.game.service.bo.EntityBean;
import com.qkzz.game.service.bo.ListRetBean;
import com.qkzz.game.service.bo.UserboxTools;

public class UserToolBoxServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757860734741145731L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String format = HttpUtil.getString(request, "format","json");
		
		PrintWriter out = response.getWriter();
		String act = HttpUtil.getString(request, "act","");
		//create,read.delete,list
		if(act.equals("alltools")) {
			//个人用户道具箱中的所有道具列表，不分游戏，主要用于后台
			
			long uid = HttpUtil.getLong(request, "uid", 0);
			int page = HttpUtil.getInt(request, "page",1);
			int size = HttpUtil.getInt(request, "count",10);
			
			if(uid<=0){
				out.print("[{\"status\":-2,\"list\":[]}]");//用户ID为空
				out.flush();
				out.close();
				return;
			}
			
			int maxCount = UserToolboxService.countByList(uid);
			int startIndex = (page - 1) * size;
			List<UserToolbox> list = UserToolboxService.getByList(uid,startIndex,size);

			ListRetBean<UserToolbox> bean = new ListRetBean<UserToolbox>();
			if(list!=null){
				bean.setStatus(1);
				bean.setMaxcount(maxCount);
				bean.setList(list);
			}else{
				bean.setStatus(-1);
				bean.setMaxcount(0);
				bean.setList(list);
			}
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}
			
		}else if(act.equals("gametoolslist")) {
			//个人在某款游戏中道具箱中的道具列表
			
			long uid = HttpUtil.getLong(request, "uid", 0);
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int filter = HttpUtil.getInt(request, "filter",0);//道具箱列表过滤标记，0：当前游戏道具  1：除去当前游戏之外的所有道具
//			int page = HttpUtil.getInt(request, "page",1);
//			int size = HttpUtil.getInt(request, "count",10);

			if(uid<=0 || gameid <= 0){
				out.print("[{\"status\":-2,\"list\":[]}]");//用户ID为空
				out.flush();
				out.close();
				return;
			}
			
			List<UserboxTools> list = null;
			if(filter == 0) {
				list = UserToolboxService.getAllToolsList(uid, gameid);
			} else {
				list = UserToolboxService.getAllOtherGameToolsList(uid, gameid);
			}
			
			ListRetBean<UserboxTools> bean = new ListRetBean<UserboxTools>();
			if(list!=null){
				bean.setStatus(1);
				bean.setMaxcount(list.size());
				bean.setList(list);
			}else{
				bean.setStatus(-1);
				bean.setMaxcount(0);
				bean.setList(list);
			}

			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}			
			
		}else if(act.equals("detail")) {
			//个人道具箱中道具详情
			
			long uid = HttpUtil.getLong(request, "uid");
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int toolsid = HttpUtil.getInt(request, "id", 0);
			
			if(uid <= 0 || gameid <= 0 || toolsid <=0 ){
				out.print("[{\"status\":-1,\"object\":null}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			
			UserboxTools obj = UserToolboxService.getToolsDetail(uid, gameid, toolsid);

			EntityBean<UserboxTools> bean = new EntityBean<UserboxTools>();
			if(obj!=null){
				bean.setStatus(0);
				bean.setObject(obj);
			}else{
				bean.setStatus(-2);
				bean.setObject(obj);
			}
			//return JSON
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}
			
		}else if(act.equals("use")) {
			//道具使用，减少道具数量
			long uid = HttpUtil.getLong(request, "uid", 0);
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int toolsid = HttpUtil.getInt(request, "id", 0);//道具ID
			int num = HttpUtil.getInt(request, "num",1);//道具使用数量，默认为1
			
			if(gameid<=0 || uid <=0 || toolsid<=0){
				out.print("{\"status\":\"-1\"}");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			
			UserToolbox utb = UserToolboxService.getByInfo(uid, gameid, toolsid);
			if(utb == null) {
				out.print("{\"status\":\"-2\"}");
				out.flush();
				out.close();
				return;
			}
			
			//判断道具数量是否充足
			if(utb.getNum() < num) {
				out.print("{\"status\":\"-3\"}");
				out.flush();
				out.close();
				return;
			}

			//减少道具数量
			int res = UserToolboxService.decToolsNum(uid, gameid, toolsid, num);
			if(res != -1) {
				out.print("{\"status\":\"0\"}");
				out.flush();
				out.close();
				return;
			}

			out.print("{\"status\":\"-4\"}");
			out.flush();
			out.close();
		}else if(act.equals("pickuptools")) {
			//拾起游戏中掉落的道具增加到道具包中
			long uid = HttpUtil.getLong(request, "uid", 0);
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int toolsid = HttpUtil.getInt(request, "id", 0);//道具ID
			int num = HttpUtil.getInt(request, "num",1);//道具使用数量，默认为1
			
			if(gameid<=0 || uid <=0 || toolsid<=0){
				out.print("{\"status\":\"-2\"}");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			
			GameTools tools = GameToolsService.getById(gameid, toolsid);
			if(tools == null) {
				out.print("{\"status\":\"-3\"}");//道具不存在
				out.flush();
				out.close();
				return;
			}
			
			if(tools.getCandrop() == 0) {
				out.print("{\"status\":\"-4\"}");//该道具不可以掉落，不能继续操作
				out.flush();
				out.close();
				return;
			}

			//增加道具到用户道具包中
			int res = UserToolboxService.incToolsNum(uid, gameid, toolsid, num);
			if(res != -1) {
				out.print("{\"status\":\"1\",\"id\":\""+toolsid+"\",\"num\":\""+num+"\"}");
				out.flush();
				out.close();
				return;
			}

			//拾起失败
			out.print("{\"status\":\"-1\"}");
			out.flush();
			out.close();
			
		} else {
			//其他，报错
			out.print("{}");
			out.flush();
			out.close();
			return;
		}
	}
}
