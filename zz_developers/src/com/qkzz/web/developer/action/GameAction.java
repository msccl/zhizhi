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
import com.qkzz.web.developer.service.GameInfoService;

@Controller
public class GameAction {
	
	private String imgpath = "/img";

	/**
	 * 添加游戏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/add", method = RequestMethod.GET)
	public String addGame(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);

		request.setAttribute("pageTitle", "添加游戏");
		
		return "game_jsp/addgame";

	}


	/**
	 * 提交并处理新增游戏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/add", method = RequestMethod.POST)
	public String doAddGame(@RequestParam("image") MultipartFile image,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);

		request.setAttribute("pageTitle", "添加游戏");
		
		String name = HttpUtil.getString(request, "name","");
		String intro = HttpUtil.getString(request, "intro","");
		String strategy = HttpUtil.getString(request, "strategy","");

		String url = HttpUtil.getString(request, "url","");
		String host = HttpUtil.getString(request,"host","");

		int width = HttpUtil.getInt(request, "width",0);
		int height = HttpUtil.getInt(request, "height",0);
		
		String uiurl = HttpUtil.getString(request, "uiurl","");//UI地址
		
		String _img = "";
		//需要重新上传处理
		if(!"".equals(image.getOriginalFilename())){

			// create file name
			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(image.getOriginalFilename());
			
			// create file path
			String filepath = imgpath + LoadResour.getFilePath();
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
		
		GameInfo bean = new GameInfo();
		bean.setName(name);
		bean.setIntro(intro);
		bean.setStrategy(strategy);
		bean.setLogo(_img);
		bean.setUrl(url);
		bean.setHost("");
		bean.setDeveloperid(ub.getId());
		String gamecode = GameInfoService.generateGameCode(1);
		bean.setGamecode(gamecode);
		bean.setWidth(width);
		bean.setHeight(height);
		bean.setUiurl(uiurl);
		
		int res = GameInfoService.addGame(bean);
		
//		int gameid = GameInfoService.getIDByGameCode(gamecode);
		
		request.setAttribute("result", "{\"result\":\""+res+"\",\"gameid\":\""+gamecode+"\"}");
		return "game_jsp/addgame_post_ajax";
	
//		return "redirect:/game/addtip";
	}
	
	/**
	 * 申请成功提示页，防止刷新
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/addtip", method = RequestMethod.GET)
	public String doAddAuctionTip(HttpServletRequest request, HttpServletResponse response) {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "增加新道具");
		
		return "game_jsp/addtip";
	}

	/**
	 * 游戏列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/list/{size}/{page}", method = RequestMethod.GET)
	public String listGame(@PathVariable("size") int size,@PathVariable("page") int page,HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "游戏列表");
		
		List<GameInfo> gameList = GameInfoService.getGameList(ub.getId(),(page-1)*size, size);
		request.setAttribute("gameList", gameList);
		
		int total = GameInfoService.getGameMaxCount(ub.getId());
		request.setAttribute("turnpage", Pager.pagination(total, size, page, "/game/list/"));
		
		return "game_jsp/list";

	}
	
	
	/**
	 * 游戏详情
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/detail", method = RequestMethod.GET)
	public String itemDetail(HttpServletRequest request, HttpServletResponse response) {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "游戏详情");
		
		int id = HttpUtil.getInt(request, "id",0);
		if(id == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "game_jsp/detail";
		}

		GameInfo bean = GameInfoService.getGame(id);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "game_jsp/detail";
		}

		request.setAttribute("bean", bean);
		
		return "game_jsp/detail";
	}
	
	
	/**
	 * 修改游戏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/edit", method = RequestMethod.GET)
	public String editGame(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "编辑游戏");
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "game_jsp/editgame";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "game_jsp/editgame";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "game_jsp/editgame";
		}
		
		request.setAttribute("bean", bean);
		
		return "game_jsp/editgame";
	}


	/**
	 * 提交并确认修改游戏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/edit", method = RequestMethod.POST)
	public String doEditGame(@RequestParam("image") MultipartFile image,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "编辑游戏");

		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "game_jsp/editgame";
			request.setAttribute("result", "{\"result\":\"-2\"}");
			return "game_jsp/editgame_post_ajax";
		}
		
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "game_jsp/editgame";
			request.setAttribute("result", "{\"result\":\"-3\"}");
			return "game_jsp/editgame_post_ajax";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "game_jsp/editgame";
			request.setAttribute("result", "{\"result\":\"-4\"}");
			return "game_jsp/editgame_post_ajax";
		}
		
		String name = HttpUtil.getString(request, "name","");
		String intro = HttpUtil.getString(request, "intro","");
		String strategy = HttpUtil.getString(request, "strategy","");

		String url = HttpUtil.getString(request, "url","");
		String host = HttpUtil.getString(request,"host","");

		int width = HttpUtil.getInt(request, "width",0);
		int height = HttpUtil.getInt(request, "height",0);
		
		String uiurl = HttpUtil.getString(request, "uiurl","");//UI地址
		
		//需要重新上传处理
		if(!"".equals(image.getOriginalFilename())){

			// create file name
			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(image.getOriginalFilename());
			
			// create file path
			String filepath = imgpath + LoadResour.getFilePath();
			//System.out.println(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			LoadResour.mkdir(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
			
			// upload file
			//FileCopyUtils.copy(swf.getBytes(), new File(LoadResour.getMMSRootPath("HTMLRoot")	+ filepath + filename));
			image.transferTo(new File(new File(LoadResour.getMMSRootPath("HTMLRoot")), filepath + filename));
			
			System.out.println("-----------------------------------");
			System.out.println(image.getOriginalFilename());
			System.out.println("-----------------------------------");
			
			//删除原 文件
			if(StringUtils.hasText(bean.getLogo())){
				OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), bean.getLogo());
				OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
			}
			
			bean.setLogo("http://s.7kzz.com" + filepath + filename);
		}
		
		bean.setName(name);
		bean.setIntro(intro);
		bean.setStrategy(strategy);
		bean.setUrl(url);
		bean.setHost("");
		bean.setWidth(width);
		bean.setHeight(height);
		bean.setUiurl(uiurl);
		
		int res = GameInfoService.editGame(bean);
		request.setAttribute("result", "{\"result\":\""+res+"\"}");

//		return "redirect:/game/addtip";
		return "game_jsp/editgame_post_ajax";

	}

	/**
	 * 删除游戏页面
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(value = "/game/del", method = RequestMethod.GET)
//	public String delGame(HttpServletRequest request, HttpServletResponse response) {
//		
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("user", ub);
//		request.setAttribute("pageTitle", "删除游戏");
//		
//		System.out.println("del game get page");
//		int gameid = HttpUtil.getInt(request, "id",0);
//		if(gameid == 0) {
//			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "game_jsp/delgame";
//		}
//
//		GameInfo bean = GameInfoService.getGame(gameid);
//		if(bean == null) {
//			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "game_jsp/delgame";
//		}
//
//		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "game_jsp/delgame";
//		}
//		
//		//删除原 文件
//		if(StringUtils.hasText(bean.getLogo())){
//			OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), bean.getLogo());
//			OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
//		}
//		
//		GameInfoService.delGame(gameid);
//		
//		return "game_jsp/delgame";
//
//	}

	/**
	 * 确认并删除游戏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/del", method = RequestMethod.GET)
	public String doDelGame(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "删除游戏");
		System.out.println("del game get submit page");

		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
			System.out.println("111111111111111");
			request.setAttribute("result", "{\"result\":\"-2\"}");
			return "game_jsp/dodelgame_post_ajax";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			System.out.println("2222222222222222");
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			request.setAttribute("result", "{\"result\":\"-3\"}");
			return "game_jsp/dodelgame_post_ajax";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
			System.out.println("33333333333333333");
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			request.setAttribute("result", "{\"result\":\"-4\"}");
			return "game_jsp/dodelgame_post_ajax";
		}
		
		int res = GameInfoService.delGame(gameid);
		System.out.println("444444444444");

		request.setAttribute("result", "{\"result\":\""+res+"\"}");
		return "game_jsp/dodelgame_post_ajax";
		
//		return "redirect:/game/list/10/1";
	}

	

	/**
	 * 修改游戏状态
	 * 上线，下线
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/game/changeStatus", method = RequestMethod.GET)
	public String changeStatus(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "修改游戏状态");
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "game_jsp/changeStatus";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "game_jsp/changeStatus";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "game_jsp/changeStatus";
		}
		
		int status = HttpUtil.getInt(request, "status",0);
		
		GameInfoService.editGameStatus(gameid, status);
		
		return "game_jsp/changeStatus";

	}


}
