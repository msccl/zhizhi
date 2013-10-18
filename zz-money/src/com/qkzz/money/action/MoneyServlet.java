package com.qkzz.money.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.common.HttpUtil;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.money.bean.MoneyInfo;
import com.qkzz.money.bean.UserMoneyLog;
import com.qkzz.money.service.MoneyLogService;
import com.qkzz.money.service.MoneyService;

public class MoneyServlet extends HttpServlet {

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
		
		PrintWriter out = response.getWriter();

//		int freshTime = 5;//seconds,need to config
		int moneyid = HttpUtil.getInt(request, "moneyid",0);
		long uid = HttpUtil.getLong(request, "uid",0);
		
		String act = HttpUtil.getString(request, "act","");
		if(act.equals("getmoney")) {
			
			double money = MoneyService.getMoney(uid, moneyid);
			
			out.print("{\"money\":\""+money+"\"}");
			out.flush();
			out.close();

		} else if(act.equals("incmoney")) {
			double money = HttpUtil.getDouble(request, "money",0);
			int tradeid = HttpUtil.getInt(request, "tradeid",0);
			String remark = HttpUtil.getString(request, "remark","");
			
			int res = MoneyService.incMoney(uid, moneyid, money, tradeid, remark, this);
			
			out.print("{\"result\":\""+res+"\"}");
			out.flush();
			out.close();

		} else if(act.equals("decmoney")) {
			double money = HttpUtil.getDouble(request, "money",0);
			int tradeid = HttpUtil.getInt(request, "tradeid",0);
			String remark = HttpUtil.getString(request, "remark","");
			
			int res = MoneyService.decMoney(uid, moneyid, money, tradeid, remark, this);
			
			out.print("{\"result\":\""+res+"\"}");
			out.flush();
			out.close();

		} else if(act.equals("moneyloglist")) {
			int pn = HttpUtil.getInt(request, "page",1);//页码
			int count = HttpUtil.getInt(request, "count",0);//每页条数
			
			String format = HttpUtil.getString(request, "format","json");
			
			int startIndex = (pn-1)*count;
			List<UserMoneyLog> list = MoneyLogService.getMoneyLogList(uid, moneyid, startIndex, count);
			
			if(list != null && list.size() > 0) {
				if(format.equals("json")) {
					JSONArray json = JSONArray.fromObject(list);
					out.print(json.toString());
				}
			} else {
				out.print("{}");
			}
			out.flush();
			out.close();

		}else if(act.equals("moneyexists")) {
			//判断游戏内部的货币在数据库中是否存在，主要用于未在DEV后台登陆注册的货币判断
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			String moneyidInGame = HttpUtil.getString(request, "id", "");//货币在游戏内部的唯一ID
			
			if(gameid<=0){
				out.print("{\"status\":-1}");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(moneyidInGame.equals("")){
				out.print("{\"status\":-2}");//货币在游戏内部的唯一ID不能为空
				out.flush();
				out.close();
				return;
			}
			
			if(MoneyService.isMoneyInGameExist(gameid, moneyidInGame)) {
				MoneyInfo mi = MoneyService.getByUnregisteredMoneyId(gameid, moneyidInGame);
				if(mi != null) {
					out.print("{\"status\":1,\"moneyinfoid\":\""+mi.getId()+"\"}");//货币已经存在于数据库中
				} else {
					out.print("{\"status\":-3}");//货币已经存在于数据库中，但是获取失败
				}
				out.flush();
				out.close();
				return;
			}
			
			out.print("{\"status\":0}");//货币在数据库中不存在
			out.flush();
			out.close();

		}else if(act.equals("addunregisteredmoney")) {
			//在游戏中邮寄货币的时候，如果该货币信息在吱吱数据库中不存在，则需要添加
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			String moneyidInGame = HttpUtil.getString(request, "id", "");//货币在游戏内部的唯一ID
			String name = HttpUtil.getString(request, "name","");//货币名称，例如金币，用于邮寄时的显示
			
			
			if(gameid<=0){
				out.print("{\"status\":-1}");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(moneyidInGame.equals("")){
				out.print("{\"status\":-2}");//货币在游戏内部的唯一ID不能为空
				out.flush();
				out.close();
				return;
			}

			if(MoneyService.isMoneyInGameExist(gameid, moneyidInGame)) {
				out.print("{\"status\":-3}");//该货币已经存在于数据库中，不能重复添加
				out.flush();
				out.close();
				return;
			}

			//添加道具
			MoneyInfo mi = new MoneyInfo();
			mi.setGameid(gameid);
			mi.setName(name);
			mi.setIdingame(moneyidInGame);
			
			int res = MoneyService.addMoneyInfo(mi);
			if(res != -1) {
				//获取刚刚添加的货币ID
				MoneyInfo gmoney = MoneyService.getByUnregisteredMoneyId(gameid, moneyidInGame);
				if(gmoney != null) {
					out.print("{\"status\":1,\"moneyinfoid\":"+gmoney.getId()+"}");//添加成功，返回刚刚添加的ID
					out.flush();
					out.close();
					return;
				}
			}

			out.print("{\"status\":-4}");//添加失败
			out.flush();
			out.close();
			
		} else {
			out.print("{}");
			out.flush();
			out.close();
		}
		
		
	}
}
