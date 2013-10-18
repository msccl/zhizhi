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
import com.qkzz.game.bean.GameStoreTools;
import com.qkzz.game.bean.GameTools;
import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameStoreService;
import com.qkzz.game.service.GameStoreToolsService;
import com.qkzz.game.service.GameToolsService;
import com.qkzz.game.service.UserToolboxService;
import com.qkzz.game.service.bo.EntityBean;
import com.qkzz.game.service.bo.EntityListBean;
import com.qkzz.game.service.bo.StoreTools;
import com.qkzz.game.util.Pager;
import com.qkzz.money.service.MoneyService;
import com.qkzz.user.service.MemberService;

public class GameStoreToolsServlet extends HttpServlet {

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
			//商店中的道具列表
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int storeid = HttpUtil.getInt(request, "storeid",0);
			int page = HttpUtil.getInt(request, "page",1);
			int count = HttpUtil.getInt(request,"count",5);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"list\":[]}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			
			if(storeid<=0){
				//如果没有输入storeid，查询第一个store作为默认
				GameStore store = GameStoreService.getFirstStore(gameid);
				if(store == null) {
					out.print("[{\"status\":-3,\"list\":[]}]");//游戏没有设置商店
					out.flush();
					out.close();
					return;
				}
				storeid = store.getId();
			} else {
				GameStore store = GameStoreService.getById(gameid, storeid);
				if(store == null) {
					out.print("[{\"status\":-4,\"list\":[]}]");//传入的storeid不存在
					out.flush();
					out.close();
					return;
				}
			}
			
			int maxCount = GameStoreToolsService.countByList(gameid,storeid);
			page = Pager.calCurrentPage(maxCount, count, page);
			int startIndex = (page - 1) * count;
			List<GameStoreTools> list = GameStoreToolsService.getByList(gameid,storeid,startIndex,count);

			EntityListBean<GameStoreTools> bean = new EntityListBean<GameStoreTools>();
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
			//商店中的道具详情
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int storeid = HttpUtil.getInt(request, "storeid",0);
			int id = HttpUtil.getInt(request, "id",0);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(storeid<=0){
				out.print("[{\"status\":-3,\"object\":null}]");//商店ID为空
				out.flush();
				out.close();
				return;
			}
			if(id<=0){
				out.print("[{\"status\":-4,\"object\":null}]");//记录ID为空
				out.flush();
				out.close();
				return;
			}
			
			//记录ID,游戏ID,商店ID,数量,状态
			GameStoreTools object = GameStoreToolsService.getById(gameid, storeid, id);
			
			StoreTools obj = null;
			EntityBean<StoreTools> bean = new EntityBean<StoreTools>();
			if(object!=null){
				//道具(道具ID,名称,描述,图片,价格,使用货币)
				GameTools tools = GameToolsService.getById(gameid, object.getToolsid());
				//设置OBJ
				obj = new StoreTools();
				obj.setId(object.getId());
				obj.setGameid(gameid);
				obj.setStoreid(storeid);
				obj.setNum(object.getNum());
				obj.setStatus(object.getStatus());
				obj.setToolsid(tools.getId());
				obj.setName(tools.getName());
				obj.setIntro(tools.getIntro());
				obj.setImg(tools.getImg());
				obj.setPrice(tools.getPrice());
				obj.setMoneyid(tools.getMoneyid());
				
				bean.setStatus(1);
				bean.setObject(obj);
			}else{
				bean.setStatus(-1);
				bean.setObject(obj);
			}
			//return JSON
			if(format.equals("json")) {
				JSONArray json = JSONArray.fromObject(bean);
				out.print(json.toString());
			}
			
		}else if(act.equals("buy")) {
			//购买商店中的道具
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int storeid = HttpUtil.getInt(request, "storeid",0);
			int id = HttpUtil.getInt(request, "id",0);
			int num = HttpUtil.getInt(request, "num",0);
			int uid = HttpUtil.getInt(request, "uid",0);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为零
				out.flush();
				out.close();
				return;
			}
			if(storeid<=0){
				out.print("[{\"status\":-3,\"object\":null}]");//商店ID为零
				out.flush();
				out.close();
				return;
			}
			if(id<=0){
				out.print("[{\"status\":-4,\"object\":null}]");//记录ID为零
				out.flush();
				out.close();
				return;
			}
			if(num<=0){
				out.print("[{\"status\":-5,\"object\":null}]");//购买数量为零
				out.flush();
				out.close();
				return;
			}
			//判断用户ID,同时判断该用户是否存在
			if(uid<=0 || MemberService.getById(uid)==null){
				out.print("[{\"status\":-6,\"object\":null}]");//购买者用户不存在
				out.flush();
				out.close();
				return;
			}
			
			GameStoreTools object = GameStoreToolsService.getById(gameid, storeid, id);
			if(object==null){
				out.print("[{\"status\":-7,\"object\":null}]");//该商店道具不存在
				out.flush();
				out.close();
				return;
			}
			//判断商品当前状态
			if(object.getStatus()==1){
				out.print("[{\"status\":-8,\"object\":null}]");//当前商品暂停出售
				out.flush();
				out.close();
				return;
			}
			if(object.getStatus()==2){
				out.print("[{\"status\":-9,\"object\":null}]");//当前商品已经下架
				out.flush();
				out.close();
				return;
			}
			//判断购买数量是否超出商店存货
			if(object.getNum()<num){
				out.print("[{\"status\":-10,\"object\":null}]");//购买数量大于商店存货数量
				out.flush();
				out.close();
				return;
			}
			
			//查询需要的货币,用户是否有足够的货币去购买
			GameTools tools = GameToolsService.getById(gameid, object.getToolsid());
			int moneyid = tools.getMoneyid();

			//使用那种货币购买(moneyid=0时为吱币)
			//判断用户是否有足够的货币额去购买
			//减少买家货币额
			if(MoneyService.getMoney(uid, moneyid)<(num*tools.getPrice())){
				out.print("[{\"status\":-11,\"object\":null}]");//购买者钱币不足
				out.flush();
				out.close();
				return;
			}
			
			int dectools = 0;
			int inctools = 0;
			//uid:用户ID, moneyInfoId:货币ID, money:金额, tradeId:交易类型, remark:备注, cls:操作java的CLASS类
			int stat = MoneyService.decMoney(uid, moneyid, num*tools.getPrice(), 1, "在商店买道具", this.getClass());
			System.out.println("stat:"+stat);
			if(stat>-1){
				/**
				 * 如果购买成功.1减少商店数量,2增加用户道具数量
				 */
				//1:减少商店数量
				dectools = GameStoreToolsService.decToolsNum(gameid, storeid, id, num);
				//2:增加用户道具数量
				inctools = UserToolboxService.incToolsNum(uid, gameid, object.getToolsid(), num);	
			}
			System.out.println(this.getClass()+":dectools:"+dectools+",inctools:"+inctools);
			if(dectools>-1 && inctools>-1){
				out.print("[{\"status\":1,\"object\":null}]");//购买成功
				out.flush();
				out.close();
				return;
			}else{
				out.print("[{\"status\":-1,\"object\":null}]");//购失败
				out.flush();
				out.close();
				return;
			}
			
		}else if(act.equals("sell")) {
			//将道具折价卖给商店
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int storeid = HttpUtil.getInt(request, "storeid",0);
			int toolsid = HttpUtil.getInt(request, "toolsid",0);
			int num = HttpUtil.getInt(request, "num",0);
			int uid = HttpUtil.getInt(request, "uid",0);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为零
				out.flush();
				out.close();
				return;
			}
			if(storeid<=0){
				out.print("[{\"status\":-3,\"object\":null}]");//商店ID为零
				out.flush();
				out.close();
				return;
			}
			if(toolsid<=0){
				out.print("[{\"status\":-4,\"object\":null}]");//道具ID为零
				out.flush();
				out.close();
				return;
			}
			if(num<=0){
				out.print("[{\"status\":-5,\"object\":null}]");//出售数量为零
				out.flush();
				out.close();
				return;
			}
			//判断用户ID,同时判断该用户是否存在
			if(uid<=0 || MemberService.getById(uid)==null){
				out.print("[{\"status\":-6,\"object\":null}]");//出售用户不存在
				out.flush();
				out.close();
				return;
			}
			
			//判断道具是否可以交易/拍卖
			GameTools tools = GameToolsService.getById(gameid, toolsid);
			if(tools==null){
				out.print("[{\"status\":-7,\"object\":null}]");//该道具不存在
				out.flush();
				out.close();
				return;
			}
			if(tools.getCanexchange()==0){
				out.print("[{\"status\":-8,\"object\":null}]");//该道具不可以被交易
				out.flush();
				out.close();
				return;
			}
//			if(tools.getCanauction()==0){
//				out.print("[{\"status\":-9,\"object\":null}]");//该道具不可以被拍卖
//				out.flush();
//				out.close();
//				return;
//			}
			
			UserToolbox utools = UserToolboxService.getByInfo(uid, gameid, toolsid);
			if(utools == null){
				out.print("[{\"status\":-9,\"object\":null}]");//用户道具不存在
				out.flush();
				out.close();
				return;
			}
			if(utools.getNum()<num){
				out.print("[{\"status\":-10,\"object\":null}]");//道具数量超出自己所拥有的该道具数量
				out.flush();
				out.close();
				return;
			}
			
			//1:减少用户道具数量
			int dectools = UserToolboxService.decToolsNum(uid, gameid, toolsid, num);	
			//2:添加用户钱币
			MoneyService.incMoney(uid, tools.getMoneyid(), (num*tools.getPrice())/2, 5, "把道具贱卖给商店", this.getClass());
			//需要增加商店账户，用于保存商店的货币总额
			if(dectools>-1){
				out.print("[{\"status\":1,\"object\":null}]");//出售成功
				out.flush();
				out.close();
				return;
			}else{
				out.print("[{\"status\":-1,\"object\":null}]");//出售失败
				out.flush();
				out.close();
				return;
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
