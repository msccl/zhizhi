package com.qkzz.online.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.common.Constant;
import com.qkzz.common.HttpUtil;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.online.bean.OnlineQueue;
import com.qkzz.online.service.OnlineService;
import com.qkzz.online.service.UserGuestService;
import com.qkzz.online.service.bo.OnlineOpenGame;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.UserService;

public class OnlineServlet extends HttpServlet {

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
		String act = HttpUtil.getString(request, "act","");
//		String ip = HttpUtil.getString(request, "ip");
		long uid = HttpUtil.getLong(request, "uid",0);

		if(act.equals("isonline")) {
			//判断用户是否全局在线
			
			String sign = HttpUtil.getString(request, "sign","");
			boolean isonline = OnlineService.isGlobalOnlne(uid,sign);
			
			out.print("{\"status\":\""+(isonline?"0":"1")+"\"}");
			out.flush();
			out.close();
			
		} else if(act.equals("playlist")) {
			//获取正在玩游戏，只有一个
			int gameid = OnlineService.getPlayingGameID(uid);
			
			//如果没有正在玩的游戏，直接返回id为0
			out.print("{\"id\":\""+gameid+"\",\"title\":\"\"}");
			out.flush();
			out.close();
			
		} else if(act.equals("openlist")) {
			//获取正打开游戏列表
			int page = HttpUtil.getInt(request, "page",1);
			int count = HttpUtil.getInt(request, "count",0);
			
			int startIndex = (page - 1)*count;
			
			int total = OnlineService.getUserOpenningGameIDMaxCount(uid);
			List<Integer> list = OnlineService.getUserOpenningGameIDList(uid, startIndex, count);

			OnlineOpenGame bean = new OnlineOpenGame();
			bean.setList(list);
			bean.setPage(page);
			bean.setCount(count);
			bean.setTotal(total);
			bean.setFreshinterval(Constant.FRESH_INTERVAL_DEFAULT);
			
			JSONArray json = JSONArray.fromObject(bean);
			out.print(json.toString());
			out.flush();
			out.close();
			
		} else if(act.equals("submitonline")) {
			//报告用户当前状态
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int status = HttpUtil.getInt(request, "status",0);//游戏在线状态值 0:下线 1：全局上线 2：正在玩 3：正在打开
			String lasturl = HttpUtil.getString(request, "url","");
			String sign = HttpUtil.getString(request, "sign");

			String name = "游客";
			User user = UserService.getByUid(uid);
			if(user == null) {
				out.print("{\"result\":\"-1\"}");
				out.flush();
				out.close();
				return;
			}
			name = user.getName();
			OnlineQueue queue = new OnlineQueue();
			queue.setUid(uid);
			queue.setName(name);
			queue.setGameid(gameid);
			queue.setLasttime(System.currentTimeMillis());
			queue.setLasturl(lasturl);
			queue.setStatus(status);
			queue.setSign(sign);
			OnlineService.addToQueue(queue);
			
			out.print("{\"result\":\"0\"}");
			out.flush();
			out.close();

		}else if(act.equals("guestid")) {
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);//如果通过网页登陆，有可能不会传递gameid，默认为0
			String url = HttpUtil.getString(request, "url");//用户当前所在url
			//获取游客ID			
			long guestuid = UserGuestService.getById();
			if(guestuid>0){
				UserGuestService.delete(guestuid);
			}

			//加入在线
			OnlineQueue queue = new OnlineQueue();
			queue.setGameid(gameid);
			queue.setUid(guestuid);
			queue.setName("游客"+guestuid);
			queue.setLasttime(System.currentTimeMillis());
			queue.setLasturl(url);
			queue.setStatus(1);//全局在线
			OnlineService.addToQueue(queue);
			
			out.print("{\"status\":\""+(guestuid>0?guestuid:"0")+"\"}");
			out.flush();
			out.close();
			
		} else {
			//其他，报错
			out.print("{}");
			out.flush();
			out.close();
		}
	}
}
