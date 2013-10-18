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
import com.qkzz.game.bean.GameAuctionTools;
import com.qkzz.game.bean.GameTools;
import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.service.GameAuctionToolsService;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameToolsService;
import com.qkzz.game.service.UserToolboxService;
import com.qkzz.game.service.bo.AuctionTools;
import com.qkzz.game.service.bo.EntityBean;
import com.qkzz.game.service.bo.EntityListBean;
import com.qkzz.game.util.Pager;
import com.qkzz.money.bean.MoneyInfo;
import com.qkzz.money.service.MoneyService;
import com.qkzz.user.service.MemberService;

public class GameAuctionToolsServlet extends HttpServlet {
	
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
			//拍卖行中的道具列表
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int auctionid = HttpUtil.getInt(request, "auctionid",0);
			int page = HttpUtil.getInt(request, "page",1);
			int count = HttpUtil.getInt(request,"count",0);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"list\":[]}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(auctionid<=0){
				out.print("[{\"status\":-3,\"list\":[]}]");//拍卖行ID为空
				out.flush();
				out.close();
				return;
			}
			
			int maxCount = GameAuctionToolsService.countByList(gameid,auctionid);
			page = Pager.calCurrentPage(maxCount, count, page);
			int startIndex = (page - 1) * count;
			List<GameAuctionTools> list = GameAuctionToolsService.getByList(gameid,auctionid,startIndex,count);

			EntityListBean<GameAuctionTools> bean = new EntityListBean<GameAuctionTools>();
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
			//拍卖行中的道具详情
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int auctionid = HttpUtil.getInt(request, "auctionid",0);
			int id = HttpUtil.getInt(request, "id",0);
			
			if(gameid<=0){
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为空
				out.flush();
				out.close();
				return;
			}
			if(auctionid<=0){
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
			
			//记录ID,游戏ID,拍卖行ID,出售的用户ID,价格,使用货币,数量,状态
			GameAuctionTools object = GameAuctionToolsService.getById(gameid, auctionid, id);
			
			AuctionTools obj = null;
			EntityBean<AuctionTools> bean = new EntityBean<AuctionTools>();
			if(object!=null){
				//道具(道具ID,名称,描述,图片)
				GameTools tools = GameToolsService.getById(gameid, object.getToolsid());
				
				//设置OBJ
				obj = new AuctionTools();
				obj.setId(object.getId());
				obj.setGameid(gameid);
				obj.setAuctionid(auctionid);
				obj.setUid(object.getUid());
				obj.setPrice(object.getPrice());
				obj.setMoneyid(object.getMoneyid());
				obj.setNum(object.getNum());
				obj.setStatus(object.getStatus());
				obj.setToolsid(tools.getId());
				obj.setName(tools.getName());
				obj.setIntro(tools.getIntro());
				obj.setImg(tools.getImg());
				
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
			//购买拍卖行中的道具
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int auctionid = HttpUtil.getInt(request, "auctionid",0);
			int id = HttpUtil.getInt(request, "id",0);
			int num = HttpUtil.getInt(request, "num",0);
			int uid = HttpUtil.getInt(request, "uid",0);
			
			if(gameid<=0) {
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为零
				out.flush();
				out.close();
				return;
			}
			if(auctionid<=0) {
				out.print("[{\"status\":-3,\"object\":null}]");//拍卖行ID为零
				out.flush();
				out.close();
				return;
			}
			if(id<=0) {
				out.print("[{\"status\":-4,\"object\":null}]");//记录ID为零
				out.flush();
				out.close();
				return;
			}
			if(num<=0) {
				out.print("[{\"status\":-5,\"object\":null}]");//购买数量为零
				out.flush();
				out.close();
				return;
			}
			//判断用户ID,同时判断该用户是否存在
			if(uid<=0 || MemberService.getById(uid)==null) {
				out.print("[{\"status\":-6,\"object\":null}]");//购买用户不存在
				out.flush();
				out.close();
				return;
			}
			GameAuctionTools object = GameAuctionToolsService.getById(gameid, auctionid, id);
			if(object==null){
				out.print("[{\"status\":-7,\"object\":null}]");//该拍卖道具不存在
				out.flush();
				out.close();
				return;
			}
			//判断商品当前状态
			if(object.getStatus()==1) {
				out.print("[{\"status\":-8,\"object\":null}]");//当前商品暂停拍卖
				out.flush();
				out.close();
				return;
			}
			if(object.getStatus()==2) {
				out.print("[{\"status\":-9,\"object\":null}]");//当前商品已经下架
				out.flush();
				out.close();
				return;
			}
			//判断购买数量是否超出商店存货
			if(object.getNum()<num) {
				out.print("[{\"status\":-10,\"object\":null}]");//购买数量大于该拍卖记录存货数量
				out.flush();
				out.close();
				return;
			}
			int moneyid = object.getMoneyid();

			//使用那种货币购买(moneyid=0时为吱币)
			//判断用户是否有足够的货币额去购买
			//减少买家货币额
			if(MoneyService.getMoney(uid, moneyid)<(num*object.getPrice())){
				out.print("[{\"status\":-11,\"object\":null}]");//购买者钱币不足
				out.flush();
				out.close();
				return;
			}
			
			int dectools= 0;
			int inctools = 0;
			int incmoney = 0;
			//uid:用户ID, moneyInfoId:货币ID, money:金额, tradeId:交易类型, remark:备注, cls:操作java的CLASS类
			int stat = MoneyService.decMoney(uid, moneyid, num*object.getPrice(), 2, "在拍卖行买道具", this.getClass());
			System.out.println("stat:"+stat);
			if(stat>-1){
				/**
				 * 如果购买成功.1减少该拍卖记录数量,2增加用户道具数量,3增加道具原所有者货币数量
				 */
				//1:减少拍卖行道具数量
				dectools = GameAuctionToolsService.decToolsNum(gameid, auctionid, id, num);
				//2:增加用户道具数量
				inctools = UserToolboxService.incToolsNum(uid, gameid, object.getToolsid(), num);	
				//3:增加道具原所有者货币数量
				incmoney = MoneyService.incMoney(object.getUid(), moneyid, num*object.getPrice(), 3, "在拍卖行卖道具", this.getClass());
			}
			System.out.println(this.getClass()+":dectools:"+dectools+",inctools:"+inctools);
			if(dectools>-1 && inctools>-1 && incmoney>-1){
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
			//将道具放到拍卖行中拍卖
			
			String gamecode = HttpUtil.getString(request, "gameid");
			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			int auctionid = HttpUtil.getInt(request, "auctionid",0);
			int toolsid = HttpUtil.getInt(request, "toolsid",0);
			int num = HttpUtil.getInt(request, "num",0);
			int uid = HttpUtil.getInt(request, "uid",0);
			int moneyid = HttpUtil.getInt(request, "moneyid",0);
			int price = HttpUtil.getInt(request, "price",0);
			
			
			if(gameid<=0) {
				out.print("[{\"status\":-2,\"object\":null}]");//游戏ID为零
				out.flush();
				out.close();
				return;
			}
			if(auctionid<=0) {
				out.print("[{\"status\":-3,\"object\":null}]");//拍卖行ID为零
				out.flush();
				out.close();
				return;
			}
			if(toolsid<=0) {
				out.print("[{\"status\":-4,\"object\":null}]");//道具ID为零
				out.flush();
				out.close();
				return;
			}
			if(num<=0) {
				out.print("[{\"status\":-5,\"object\":null}]");//拍卖数量为零
				out.flush();
				out.close();
				return;
			}
			
			if(moneyid == 0) {
				out.print("[{\"status\":-6,\"object\":null}]");//拍卖用户不存在
				out.flush();
				out.close();
				return;
			}
			
			if(price < 0) {
				out.print("[{\"status\":-7,\"object\":null}]");//价格不能为负值
				out.flush();
				out.close();
				return;
			}
			
			//判断用户ID,同时判断该用户是否存在
			if(uid<=0 || MemberService.getById(uid)==null) {
				out.print("[{\"status\":-8,\"object\":null}]");//拍卖用户不存在
				out.flush();
				out.close();
				return;
			}
			
			//判断道具是否可以交易/拍卖
			GameTools tools = GameToolsService.getById(gameid, toolsid);
			if(tools==null){
				out.print("[{\"status\":-9,\"object\":null}]");//该道具不存在
				out.flush();
				out.close();
				return;
			}
			if(tools.getCanexchange()==0){
				out.print("[{\"status\":-10,\"object\":null}]");//该道具不可以被交易
				out.flush();
				out.close();
				return;
			}
			if(tools.getCanauction()==0){
				out.print("[{\"status\":-11,\"object\":null}]");//该道具不可以被拍卖
				out.flush();
				out.close();
				return;
			}
			UserToolbox utools = UserToolboxService.getByInfo(uid, gameid, toolsid);
			if(utools == null){
				out.print("[{\"status\":-12,\"object\":null}]");//用户道具不存在
				out.flush();
				out.close();
				return;
			}
			if(utools.getNum()<num){
				out.print("[{\"status\":-13,\"object\":null}]");//道具数量超出自己所拥有的该道具数量
				out.flush();
				out.close();
				return;
			}

			MoneyInfo moneyInfo = MoneyService.getMoneyInfo(moneyid);
			if(moneyInfo == null) {
				out.print("[{\"status\":-14,\"object\":null}]");//货币ID不存在
				out.flush();
				out.close();
				return;
			}
			
			if(moneyInfo.getGameid() != gameid) {
				out.print("[{\"status\":-15,\"object\":null}]");//货币不属于该游戏
				out.flush();
				out.close();
				return;
			}

			
			
			//1:添加道具到拍卖行
			GameAuctionTools obj = new GameAuctionTools();
			obj.setGameid(gameid);
			obj.setAuctionid(auctionid);
			obj.setUid(uid);
			obj.setToolsid(toolsid);
			obj.setNum(num);
			obj.setMoneyid(moneyid);
			obj.setPrice(price);
			obj.setStatus(0);
			GameAuctionToolsService.add(obj);
			//2:减少用户道具数量
			int dectools = UserToolboxService.decToolsNum(uid, gameid, toolsid, num);	

			
			if(dectools>-1){
				out.print("[{\"status\":1,\"object\":null}]");//道具投放拍卖成功
				out.flush();
				out.close();
				return;
			}else{
				out.print("[{\"status\":-1,\"object\":null}]");//道具投放拍卖失败
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
