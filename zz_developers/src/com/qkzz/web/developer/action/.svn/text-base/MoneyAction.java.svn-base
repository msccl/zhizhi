package com.qkzz.web.developer.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qkzz.util.CookieUtil;
import com.qkzz.util.HttpUtil;
import com.qkzz.util.Pager;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.bean.MoneyInfo;
import com.qkzz.web.developer.bean.MoneyRetBean;
import com.qkzz.web.developer.service.GameInfoService;
import com.qkzz.web.developer.service.MoneyInfoService;
import com.qkzz.web.developer.service.StoreService;

@Controller
public class MoneyAction {

	/**
	 * 为游戏申请新的货币
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/money/add", method = RequestMethod.GET)
	public String applyNewMoney(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "增加新货币");
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "money_jsp/addmoney";
		}
		
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "money_jsp/addmoney";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "money_jsp/addmoney";
		}
		
		request.setAttribute("gameid", gameid);
		
		return "money_jsp/addmoney";

	}

	
	/**
	 * 申请新货币提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/money/add", method = RequestMethod.POST)
	public void doApplyNewMoney(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "增加新货币");
		PrintWriter out = response.getWriter();

		//货币名称
		String name = HttpUtil.getString(request, "name","");

		//是否可以兑换
		int canexchange = HttpUtil.getInt(request, "canexchange",0);
		
		//兑换比率
		double exchangerate = HttpUtil.getDouble(request, "exchangerate",1);//默认等同
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			out.println("{\"result\":\"-2\"}");
	        out.flush();    
	        out.close();    
			return;
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			out.println("{\"result\":\"-3\"}");
	        out.flush();    
	        out.close();    
			return;
		}

		if(bean.getDeveloperid() != ub.getId()) {
			out.println("{\"result\":\"-4\"}");
	        out.flush();    
	        out.close();    
			return;
		}
		
		MoneyInfo mi = new MoneyInfo();
		mi.setName(name);
		mi.setGameid(gameid);
		mi.setCanexchange(canexchange);
		mi.setExchangerate(exchangerate);
		int res = MoneyInfoService.addMoneyInfo(mi);
		
		if(res != -1) {
			int moneyid = MoneyInfoService.getLastInsertID(gameid, name);
			out.println("{\"result\":\""+res+"\",\"storeid\":\""+moneyid+"\"}");
	        out.flush();    
	        out.close();    
			return;
		}
		out.println("{\"result\":\""+res+"\"}");
        out.flush();    
        out.close();    
	}

	
//	/**
//	 * 申请成功提示页，方式刷新
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/money/addtip", method = RequestMethod.GET)
//	public String doAddAuctionTip(HttpServletRequest request, HttpServletResponse response) {
//
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("user", ub);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("pageTitle", "增加新货币");
//		
//		return "money_jsp/addtip";
//	}

	
	/**
	 * 添加货币列表，按照游戏区分
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/money/list/{gameid}/{size}/{page}", method = RequestMethod.GET)
	public String moneyList(@PathVariable("gameid") int gameid,@PathVariable("size") int size,@PathVariable("page") int page,HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "货币列表");
		
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "money_jsp/list";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "money_jsp/list";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "money_jsp/list";
		}
		
		List<MoneyInfo> moneyList = MoneyInfoService.getGameMoneyInfo(gameid, (page-1)*size, size);
		request.setAttribute("moneyList", moneyList);
		
		int total = MoneyInfoService.getGameMoneyMaxCount(gameid);
		request.setAttribute("turnpage", Pager.pagination(total, size, page, "/money/list/"+gameid+"/"));
		
		request.setAttribute("bean", bean);
		
		return "money_jsp/list";

	}

	
	/**
	 * 编辑货币
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/money/edit", method = RequestMethod.GET)
	public void editMoney(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑货币");
		PrintWriter out = response.getWriter();

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			out.println("{\"result\":\"-1\"}");
	        out.flush();    
	        out.close();    
			return;
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			out.println("{\"result\":\"-2\"}");
	        out.flush();    
	        out.close();    
			return;
		}

		if(bean.getDeveloperid() != ub.getId()) {
			out.println("{\"result\":\"-3\"}");
	        out.flush();    
	        out.close();    
			return;
		}
		
		int moneyinfoid = HttpUtil.getInt(request, "id");
		MoneyInfo mi = MoneyInfoService.getMoneyInfo(moneyinfoid);
		if(mi == null) {
			//货币不存在
			out.println("{\"result\":\"-4\"}");
	        out.flush();    
	        out.close();    
			return;
		}

		MoneyRetBean retBean = new MoneyRetBean();
		retBean.setResult(1);
		retBean.setMoney(mi);
		
		request.setAttribute("moneyinfobean", mi);
		request.setAttribute("gameid", gameid);
		
		JSONArray json = JSONArray.fromObject(retBean);
		out.println(json.toString());
        out.flush();    
        out.close();    
		return;
	}

	
	/**
	 * 货币编辑提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/money/edit", method = RequestMethod.POST)
	public void doEditMoney(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑货币");
		PrintWriter out = response.getWriter();

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			out.println("{\"result\":\"-2\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			out.println("{\"result\":\"-3\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		if(bean.getDeveloperid() != ub.getId()) {
			out.println("{\"result\":\"-4\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		int moneyinfoid = HttpUtil.getInt(request, "id");
		MoneyInfo mi = MoneyInfoService.getMoneyInfo(moneyinfoid);
		if(mi == null) {
			//货币不存在
			out.println("{\"result\":\"-5\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		//货币名称
		String name = HttpUtil.getString(request, "name","");

		//是否可以兑换
		int canexchange = HttpUtil.getInt(request, "canexchange",0);
		
		//兑换比率
		double exchangerate = HttpUtil.getDouble(request, "exchangerate",1);//默认等同
		
		mi.setName(name);
		mi.setCanexchange(canexchange);
		mi.setExchangerate(exchangerate);
		int res = MoneyInfoService.editMoneyInfo(mi);
		if(res == -1){
			out.println("{\"result\":\""+res+"\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		mi = MoneyInfoService.getMoneyInfo(moneyinfoid);
		out.println("{\"result\":\""+res+"\",\"moneyid\":\""+moneyinfoid+"\",\"status\":\""+mi.getStatus()+"\"}");
        out.flush();    
        out.close();
	}
	
	
//	/**
//	 * 申请成功提示页，方式刷新
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/money/edittip", method = RequestMethod.GET)
//	public String doEditMoneyTip(HttpServletRequest request, HttpServletResponse response) {
//
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("user", ub);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("pageTitle", "货币编辑");
//		
//		return "money_jsp/edittip";
//	}
//
	
	
//	/**
//	 * 删除货币
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/money/del", method = RequestMethod.GET)
//	public String delMoney(HttpServletRequest request, HttpServletResponse response) {
//		
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("user", ub);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("pageTitle", "删除货币");
//
//		int gameid = HttpUtil.getInt(request, "gameid",0);
//		if(gameid == 0) {
//			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "money_jsp/delmoney";
//		}
//
//		GameInfo bean = GameInfoService.getGame(gameid);
//		if(bean == null) {
//			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "money_jsp/delmoney";
//		}
//
//		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "money_jsp/delmoney";
//		}
//		
//		int moneyinfoid = HttpUtil.getInt(request, "id");
//		MoneyInfo mi = MoneyInfoService.getMoneyInfo(moneyinfoid);
//		if(mi == null) {
//			//货币不存在
//			request.setAttribute("ERR_CODE", "MONEYINFO_NOT_EXIST");
//			return "money_jsp/delmoney";
//		}
//		
//		request.setAttribute("gameid", gameid);
//		request.setAttribute("id", moneyinfoid);
//
//		return "money_jsp/delmoney";
//
//	}
	

	/**
	 * 删除货币提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/money/del", method = RequestMethod.GET)
	public void doDelMoney(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "删除货币");
		PrintWriter out = response.getWriter();

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			out.println("{\"result\":\"-2\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			out.println("{\"result\":\"-3\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		if(bean.getDeveloperid() != ub.getId()) {
			out.println("{\"result\":\"-4\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		int moneyinfoid = HttpUtil.getInt(request, "id");
		MoneyInfo mi = MoneyInfoService.getMoneyInfo(moneyinfoid);
		if(mi == null) {
			//拍卖行不存在
			out.println("{\"result\":\"-5\"}");
	        out.flush();    
	        out.close();
	        return;
		}
		
		int res = MoneyInfoService.delMoneyInfo(moneyinfoid);
		out.println("{\"result\":\""+res+"\"}");
        out.flush();    
        out.close();
	}

}
