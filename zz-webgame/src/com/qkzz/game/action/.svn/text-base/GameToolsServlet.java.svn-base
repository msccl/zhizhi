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
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameToolsService;
import com.qkzz.game.service.bo.EntityBean;
import com.qkzz.game.service.bo.EntityListBean;

public class GameToolsServlet extends HttpServlet {

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
		if(act.equals("list")) {
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int pn = HttpUtil.getInt(request, "page",1);
			
			if(gameid<=0){
				out.print("[{\"status\":0,\"list\":[]}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			
			int count = GameToolsService.countByList(gameid);
			int startIndex = (pn-1)*count;
			List<GameTools> list = GameToolsService.getByList(gameid, startIndex, count);
			
			EntityListBean<GameTools> bean = new EntityListBean<GameTools>();
			if(list!=null){
				bean.setStatus(1);
				bean.setList(list);
			}else{
				bean.setStatus(-1);
				bean.setList(list);
			}
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}
			
		}else if(act.equals("read")) {
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int id = HttpUtil.getInt(request, "id", 0);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(id<=0){
				out.print("[{\"status\":-3,\"object\":null}]");//拍卖行ID为空
				out.flush();
				out.close();
				return;
			}
			
			int status = GameToolsService.getByStatus(gameid, id);
			GameTools object = GameToolsService.getById(gameid, id);
			
			EntityBean<GameTools> bean = new EntityBean<GameTools>();
			if(object!=null){
				bean.setStatus(status);
				bean.setObject(object);
			}else{
				bean.setStatus(-4);
				bean.setObject(object);
			}
			//return JSON
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}
			
		}else if(act.equals("toolsexists")) {
			//判断游戏内部的道具在数据库中是否存在，主要用于未在DEV后台登陆注册的道具判断
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			String toolid = HttpUtil.getString(request, "toolid", "");//道具在游戏内部的ID
			
			if(gameid<=0){
				out.print("{\"status\":-1}");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(toolid.equals("")){
				out.print("{\"status\":-2}");//道具在游戏内部的唯一ID不能为空
				out.flush();
				out.close();
				return;
			}
			
			if(GameToolsService.isToolsInGameExist(gameid, toolid)) {
				GameTools gt = GameToolsService.getByUnregisteredToolId(gameid, toolid);
				if(gt != null) {
					out.print("{\"status\":1,\"toolid\":\""+gt.getId()+"\"}");//道具已经存在于数据库中
				} else {
					out.print("{\"status\":-3}");//道具已经存在于数据库中
				}
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"status\":0}");//道具在数据库中不存在
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
