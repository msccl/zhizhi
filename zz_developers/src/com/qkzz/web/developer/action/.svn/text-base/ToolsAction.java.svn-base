package com.qkzz.web.developer.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qkzz.util.CookieUtil;
import com.qkzz.util.HttpUtil;
import com.qkzz.util.LoadResour;
import com.qkzz.util.OperationFile;
import com.qkzz.util.Pager;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.bean.Tools;
import com.qkzz.web.developer.bean.ToolsRetBean;
import com.qkzz.web.developer.service.GameInfoService;
import com.qkzz.web.developer.service.ToolsService;

@Controller
public class ToolsAction {
	
	private String swfpath = "/swf/";
	private String imgpath = "/img/";

	/**
	 * 为游戏增加新的道具
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/add", method = RequestMethod.GET)
	public String addTools(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("tools add get page");
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "增加新道具");
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "tools_jsp/addtools";
		}
		System.out.println("tools add get gameid:"+gameid);
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "tools_jsp/addtools";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "tools_jsp/addtools";
		}
		
		//获取该游戏的货币列表
//		List<MoneyInfo> moneyList = MoneyInfoService.getGameMoneyInfo(gameid, 0, 100);
//		request.setAttribute("moneyList", moneyList);
		request.setAttribute("gameid", gameid);
		
		return "tools_jsp/addtools";

	}
	
	
	/**
	 * 添加新道具提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/add", method = RequestMethod.POST)
	public String doAddTools(@RequestParam("image") MultipartFile image,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("tools add post page");
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "增加新道具");
		
		String name = HttpUtil.getString(request, "name","");
		
		String intro = HttpUtil.getString(request, "intro","");

		int canexchange = HttpUtil.getInt(request, "canexchange",0);
		
		int canauction = HttpUtil.getInt(request, "canauction",0);
		
		String functiondefine = HttpUtil.getString(request, "functiondefine","");
		
		int moneyid = HttpUtil.getInt(request, "moneyid",0);
		
		double price = HttpUtil.getDouble(request, "price",0);
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "tools_jsp/addtools";
			request.setAttribute("result", "{\"result\":\"-2\"}");
			return "tools_jsp/addtools_post_ajax";
		}
		System.out.println("tools add post gameid:"+gameid);
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "tools_jsp/addtools";
			request.setAttribute("result", "{\"result\":\"-3\"}");
			return "tools_jsp/addtools_post_ajax";
		}

		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "tools_jsp/addtools";
			request.setAttribute("result", "{\"result\":\"-4\"}");
			return "tools_jsp/addtools_post_ajax";
		}
		
		String _img = null;
		//需要重新上传处理
		if(!"".equals(image.getOriginalFilename())){

			// create file name
			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(image.getOriginalFilename());
			
			// create file path
			String filepath = imgpath + gameid + LoadResour.getFilePath();
			//System.out.println(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			LoadResour.mkdir(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			
			// upload file
			//FileCopyUtils.copy(swf.getBytes(), new File(LoadResour.getMMSRootPath("HTMLRoot")	+ filepath + filename));
			image.transferTo(new File(new File(LoadResour.getMMSRootPath("HTMLRoot")), filepath + filename));
			
			System.out.println("-----------------------------------");
			System.out.println(image.getOriginalFilename());
			System.out.println("-----------------------------------");
			
			_img = "http://s.7kzz.com" + filepath + filename;
		}
		
		Tools tools = new Tools();
		tools.setName(name);
		tools.setGameid(gameid);
		tools.setIntro(intro);
		tools.setImg(_img);
		tools.setCanexchange(canexchange);
		tools.setCanauction(canauction);
		tools.setFunctiondefine(functiondefine);
		tools.setMoneyid(moneyid);
		tools.setPrice(price);
		int res = ToolsService.addTools(tools);
		int toolsid = ToolsService.getLastToolsID(gameid, name);
		
		tools = ToolsService.getTools(gameid, toolsid);

		request.setAttribute("result", "{\"result\":\""+res+"\",\"toolsid\":\""+toolsid+"\",\"img\":\""+tools.getImg()+"\",\"status\":\""+tools.getStatus()+"\"}");
		return "tools_jsp/addtools_post_ajax";

		
//		return "redirect:/tools/addtip";

	}

	
	/**
	 * 申请成功提示页，防止刷新
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/addtip", method = RequestMethod.GET)
	public String doAddAuctionTip(HttpServletRequest request, HttpServletResponse response) {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "增加新道具");
		
		return "tools_jsp/addtip";
	}

	
	/**
	 * 道具列表，可翻页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/list/{gameid}/{size}/{page}", method = RequestMethod.GET)
	public String listTools(@PathVariable("gameid") int gameid,
			@PathVariable("size") int size,
			@PathVariable("page") int page,
			HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "道具列表");
		
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "tools_jsp/list";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "tools_jsp/list";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "tools_jsp/list";
		}
		
		List<Tools> toolsList = ToolsService.getGameTools(gameid, (page-1)*size, size);
		
		request.setAttribute("toolsList", toolsList);
		request.setAttribute("gameid", gameid);
				
		int total = ToolsService.getGameToolsMaxCount(gameid);
		request.setAttribute("turnpage", Pager.pagination(total, size, page, "/tools/list/"+gameid+"/"));
		
		request.setAttribute("bean", bean);

		
		return "tools_jsp/list";

	}

	
	
	/**
	 * 游戏详情
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/detail", method = RequestMethod.GET)
	public String itemDetail(HttpServletRequest request, HttpServletResponse response) {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "道具详情");
		
		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "tools_jsp/detail";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "tools_jsp/detail";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "tools_jsp/detail";
		}

		int toolsid = HttpUtil.getInt(request, "id");
		Tools tools = ToolsService.getTools(gameid, toolsid);
		if(tools == null) {
			//道具不存在
			request.setAttribute("ERR_CODE", "TOOLS_NOT_EXIST");
			return "tools_jsp/detail";
		}

		//查询货币信息
//		MoneyInfo mi = MoneyInfoService.getMoneyInfo(tools.getMoneyid());
//		if(mi == null) {
//			request.setAttribute("ERR_CODE", "MONEYINFO_NOT_EXIST");
//			return "tools_jsp/detail";
//		}
		
		request.setAttribute("tools", tools);
//		request.setAttribute("moneyinfo", mi);
		
		return "tools_jsp/detail";
	}

	

	/**
	 * 编辑道具
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/edit", method = RequestMethod.GET)
	public String editTools(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("tools edit get page");
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑道具");

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-1\"}");
			return "tools_jsp/edittools_ajax";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-2\"}");
			return "tools_jsp/edittools_ajax";
		}

		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-3\"}");
			return "tools_jsp/edittools_ajax";
		}

		int toolsid = HttpUtil.getInt(request, "id");
		Tools tools = ToolsService.getTools(gameid, toolsid);
		if(tools == null) {
			//道具不存在
//			request.setAttribute("ERR_CODE", "TOOLS_NOT_EXIST");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-4\"}");
			return "tools_jsp/edittools_ajax";
		}
		
		
		//获取该游戏的货币列表
//		List<MoneyInfo> moneyList = MoneyInfoService.getGameMoneyInfo(gameid, 0, 10000);
//		request.setAttribute("moneyList", moneyList);
		
		ToolsRetBean retBean = new ToolsRetBean();
		retBean.setResult(1);
		retBean.setTools(tools);

		JSONArray json = JSONArray.fromObject(retBean);
		request.setAttribute("result", json.toString());
		request.setAttribute("toolsbean", tools);
		request.setAttribute("gameid", gameid);
		System.out.println("tools edit get page result:"+json.toString());
		
		return "tools_jsp/edittools_ajax";

	}
	

	/**
	 * 编辑道具提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/edit", method = RequestMethod.POST)
	public String doEditTools(@RequestParam("image") MultipartFile image,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("tools edit post page");
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑道具");

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "tools_jsp/edittools";
			System.out.println("111111111111111");
			request.setAttribute("result", "{\"result\":\"-2\"}");
			return "tools_jsp/edittools_post_ajax";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			System.out.println("22222222222222");
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-3\"}");
			return "tools_jsp/edittools_post_ajax";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			System.out.println("33333333333333");
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-4\"}");
			return "tools_jsp/edittools_post_ajax";
		}

		int toolsid = HttpUtil.getInt(request, "id");
		Tools tools = ToolsService.getTools(gameid, toolsid);
		if(tools == null) {
			System.out.println("4444444444444444");
			//道具不存在
//			request.setAttribute("ERR_CODE", "AUCTION_NOT_EXIST");
//			return "tools_jsp/edittools";
			request.setAttribute("result", "{\"result\":\"-5\"}");
			return "tools_jsp/edittools_post_ajax";
		}

		String name = HttpUtil.getString(request, "name","");
		
		String intro = HttpUtil.getString(request, "intro","");

		int canexchange = HttpUtil.getInt(request, "canexchange",0);
		
		int canauction = HttpUtil.getInt(request, "canauction",0);
		
		String functiondefine = HttpUtil.getString(request, "functiondefine","");
		
		int moneyid = HttpUtil.getInt(request, "moneyid",0);
		
		double price = HttpUtil.getDouble(request, "price",0);

		//需要重新上传处理
		if(!"".equals(image.getOriginalFilename())){

			System.out.println("55555555555555555555");
			// create file name
			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(image.getOriginalFilename());
			
			// create file path
			String filepath = imgpath + gameid + LoadResour.getFilePath();
			//System.out.println(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			LoadResour.mkdir(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			
			// upload file
			//FileCopyUtils.copy(swf.getBytes(), new File(LoadResour.getMMSRootPath("HTMLRoot")	+ filepath + filename));
			image.transferTo(new File(new File(LoadResour.getMMSRootPath("HTMLRoot")), filepath + filename));
			
			System.out.println("-----------------------------------");
			System.out.println(image.getOriginalFilename());
			System.out.println("-----------------------------------");
			
			//删除原 文件
			if(StringUtils.hasText(tools.getImg())){
				OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), tools.getImg());
				OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
			}
			
			tools.setImg("http://s.7kzz.com" + filepath + filename);
		}
		
		tools.setName(name);
		tools.setGameid(gameid);
		tools.setIntro(intro);
		tools.setCanexchange(canexchange);
		tools.setCanauction(canauction);
		tools.setFunctiondefine(functiondefine);
		tools.setMoneyid(moneyid);
		tools.setPrice(price);
		int res = ToolsService.editTools(tools);
		

		tools = ToolsService.getTools(gameid, toolsid);

		request.setAttribute("result", "{\"result\":\""+res+"\",\"toolsid\":\""+toolsid+"\",\"img\":\""+tools.getImg()+"\",\"status\":\""+tools.getStatus()+"\"}");

//		return "redirect:/tools/addtip";
//		request.setAttribute("result", "{\"result\":\""+res+"\"}");
		System.out.println("tools edit post page result:"+"{\"result\":\""+res+"\"}");
		return "tools_jsp/edittools_post_ajax";

	}
	
//	/**
//	 * 删除道具
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/tools/del", method = RequestMethod.GET)
//	public String delTools(HttpServletRequest request, HttpServletResponse response) {
//		
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("user", ub);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("pageTitle", "删除道具");
//
//		int gameid = HttpUtil.getInt(request, "gameid",0);
//		if(gameid == 0) {
//			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "tools_jsp/deltools";
//		}
//
//		GameInfo bean = GameInfoService.getGame(gameid);
//		if(bean == null) {
//			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "tools_jsp/deltools";
//		}
//
//		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "tools_jsp/deltools";
//		}
//
//		int toolsid = HttpUtil.getInt(request, "id");
//		Tools tools = ToolsService.getTools(gameid, toolsid);
//		if(tools == null) {
//			//道具不存在
//			request.setAttribute("ERR_CODE", "TOOLS_NOT_EXIST");
//			return "tools_jsp/deltools";
//		}
//		
//		//删除该记录对应文件
//		if(StringUtils.hasText(tools.getImg())){
//			OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), tools.getImg());
//			OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
//		}
//		
//		//删除对应记录
//		ToolsService.delTools(gameid, toolsid);
//
//		return "tools_jsp/deltools";
//
//	}

	
	/**
	 * 删除道具提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tools/del", method = RequestMethod.GET)
	public String doDelTools(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "删除道具");

		System.out.println("tools del 1111111111111111111");
		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			System.out.println("tools del 2222222222222222");
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
			request.setAttribute("result", "{\"result\":\"-2\"}");
			return "tools_jsp/dodeltools_post_ajax";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			System.out.println("tools del 3333333333333333");
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "tools_jsp/dodeltools";
			request.setAttribute("result", "{\"result\":\"-3\"}");
			return "tools_jsp/dodeltools_post_ajax";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			System.out.println("tools del 444444444444444");
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "tools_jsp/dodeltools";
			request.setAttribute("result", "{\"result\":\"-4\"}");
			return "tools_jsp/dodeltools_post_ajax";
		}

		int toolsid = HttpUtil.getInt(request, "id");
		Tools tools = ToolsService.getTools(gameid, toolsid);
		if(tools == null) {
			//道具不存在
			System.out.println("tools del 5555555555555555");
//			request.setAttribute("ERR_CODE", "TOOLS_NOT_EXIST");
//			return "tools_jsp/dodeltools";
			request.setAttribute("result", "{\"result\":\"-5\"}");
			return "tools_jsp/dodeltools_post_ajax";
		}
		
		//删除该记录对应文件
		if(StringUtils.hasText(tools.getImg())){
			OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), tools.getImg());
			OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
		}

		int res = ToolsService.delTools(gameid, toolsid);
		
		System.out.println("tools del 66666666666666666666");
		request.setAttribute("result", "{\"result\":\""+res+"\"}");
		return "tools_jsp/dodeltools_post_ajax";
//		return "redirect:/tools/list"+gameid+"/10/1";

	}

}
