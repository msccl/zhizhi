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
import com.qkzz.game.bean.GameStore;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameStoreService;
import com.qkzz.game.service.bo.EntityBean;
import com.qkzz.game.service.bo.EntityListBean;

public class GameStoreServlet extends HttpServlet {

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
			//游戏中的商店列表，主要用于后台
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int pn = HttpUtil.getInt(request, "page",1);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"list\":[]}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			
			int count = GameStoreService.countByList(gameid);
			int startIndex = (pn-1)*count;
			List<GameStore> list = GameStoreService.getByList(gameid, startIndex, count);

			EntityListBean<GameStore> bean = new EntityListBean<GameStore>();
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
			//获取商店详情
			
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
				out.print("[{\"status\":-3,\"object\":null}]");//商店ID为空
				out.flush();
				out.close();
				return;
			}
			
			int status = GameStoreService.getByStatus(gameid, id);
			GameStore object = GameStoreService.getById(gameid, id);
			
			EntityBean<GameStore> bean = new EntityBean<GameStore>();
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
			
		} else {
			//其他，报错
			out.print("{}");
			out.flush();
			out.close();
			return;
		}
	}
}
