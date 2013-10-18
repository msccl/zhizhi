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
import com.qkzz.game.bean.GameAuction;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.service.GameAuctionService;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.bo.EntityBean;
import com.qkzz.game.service.bo.EntityListBean;

public class GameInfoServlet extends HttpServlet {

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
		if(act.equals("getui")) {
			//游戏中的拍卖行列表，主要用于后台
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			
			if(gameid<=0){
				out.print("{\"status\":-1}");//游戏ID为空
				out.flush();
				out.close();
				return;
			}

			GameInfo game = GameInfoService.getGame(gameid);
			if(game == null) {
				out.print("{\"status\":-2}");//游戏不存在
				out.flush();
				out.close();
				return;
			}
			
			if(game.getUiurl() == null || game.getUiurl().equals("")) {
				out.print("{\"status\":-3}");//游戏未设置UI地址
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"status\":0,\"ui\":\""+game.getUiurl()+"\"}");//游戏ID为空
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
