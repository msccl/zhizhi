package com.qkzz.web.developer.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qkzz.web.developer.bean.Auction;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.service.AuctionService;
import com.qkzz.web.developer.service.GameInfoService;

@Controller
public class AuctionAction {
	
	private String swfpath = "/swf/";
	private String imgpath = "/img/";

	/**
	 * 申请增加拍卖行
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/applyNew", method = RequestMethod.GET)
	public String addAuction(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "申请拍卖行");
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/addauction";
		}
		
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/addauction";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/addauction";
		}
		
		request.setAttribute("gameid", gameid);
		
		return "auction_jsp/addauction";
	}

	/**
	 * 申请添加拍卖行提交
	 * @param valicode
	 * @param obj
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/applyNew", method = RequestMethod.POST)
	public String doAddAuction(//@RequestParam("swfurl") MultipartFile swf,
			Auction obj,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "申请拍卖行");
		
		String name = HttpUtil.getString(request, "name","");

		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/addauction";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/addauction";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/addauction";
		}
		
		String _swf = "";
		//需要重新上传处理
//		if(!"".equals(swf.getOriginalFilename())){
//
//			// create file name
//			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(swf.getOriginalFilename());
//			
//			// create file path
//			String filepath = swfpath + gameid + LoadResour.getFilePath();
//			//System.out.println(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
//			LoadResour.mkdir(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
//			
//			// upload file
//			//FileCopyUtils.copy(swf.getBytes(), new File(LoadResour.getMMSRootPath("HTMLRoot")	+ filepath + filename));
//			swf.transferTo(new File(new File(LoadResour.getMMSRootPath("HTMLRoot")), filepath + filename));
//			
//			System.out.println("-----------------------------------");
//			System.out.println(swf.getOriginalFilename());
//			System.out.println("-----------------------------------");
//			
//			_swf = filepath + filename;
//		}
		
		Auction auc = new Auction();
		auc.setName(obj.getName());
		auc.setSwfurl(_swf);
		auc.setGameid(obj.getId());
		int res = AuctionService.addAuction(auc);
		
		return "redirect:/auction/applytip";
	}

	
	/**
	 * 申请成功提示页，方式刷新
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/applytip", method = RequestMethod.GET)
	public String doAddAuctionTip(HttpServletRequest request, HttpServletResponse response) {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "申请拍卖行");
		
		return "auction_jsp/applytip";
	}

	
	/**
	 * 拍卖行列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/list/{gameid}/{size}/{page}", method = RequestMethod.GET)
	public String listAuction(@PathVariable("gameid") int gameid,@PathVariable("size") int size,@PathVariable("page") int page,HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "拍卖行列表");
		
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/list";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/list";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/list";
		}

		
		List<Auction> auctionList = AuctionService.getGameAuction(gameid, (page-1)*size, size);
		request.setAttribute("auctionList", auctionList);
		request.setAttribute("bean", bean);
		
		int total = AuctionService.getGameAuctionMaxCount(gameid);
		request.setAttribute("turnpage", Pager.pagination(total, size, page, "/auction/list/"+gameid+"/"));
		
		return "auction_jsp/list";
	}
	
	
	/**
	 * 编辑拍卖行
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/edit", method = RequestMethod.GET)
	public String editAuction(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑拍卖行");

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/editauction";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/editauction";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/editauction";
		}

		int auctionid = HttpUtil.getInt(request, "id");
		Auction auc = AuctionService.getAuction(auctionid);
		if(auc == null) {
			//拍卖行不存在
			request.setAttribute("ERR_CODE", "AUCTION_NOT_EXIST");
			return "auction_jsp/editauction";
		}
		
		request.setAttribute("auctionbean", auc);
		request.setAttribute("gameid", gameid);
		
		return "auction_jsp/editauction";
	}


	/**
	 * 拍卖行编辑提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/edit", method = RequestMethod.POST)
	public String doEditAuction(//@RequestParam("swfurl") MultipartFile swf,
			Auction obj,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑拍卖行");

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/editauction";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/editauction";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/editauction";
		}

		int auctionid = HttpUtil.getInt(request, "id");
		Auction auc = AuctionService.getAuction(auctionid);
		if(auc == null) {
			//拍卖行不存在
			request.setAttribute("ERR_CODE", "AUCTION_NOT_EXIST");
			return "auction_jsp/editauction";
		}

		String name = HttpUtil.getString(request, "name");

		//需要重新上传处理
//		if(!"".equals(swf.getOriginalFilename())){
//
//			// create file name
//			String filename = System.currentTimeMillis() + "." + LoadResour.getExtention(swf.getOriginalFilename());
//			
//			// create file path
//			String filepath = swfpath + gameid + LoadResour.getFilePath();
//			//System.out.println(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
//			LoadResour.mkdir(LoadResour.getMMSRootPath("HTMLRoot") + filepath);
//			
//			// upload file
//			//FileCopyUtils.copy(swf.getBytes(), new File(LoadResour.getMMSRootPath("HTMLRoot")	+ filepath + filename));
//			swf.transferTo(new File(new File(LoadResour.getMMSRootPath("HTMLRoot")), filepath + filename));
//			
//			System.out.println("-----------------------------------");
//			System.out.println(swf.getOriginalFilename());
//			System.out.println("-----------------------------------");
//			
//			//删除原 FLASH 文件
//			if(StringUtils.hasText(auc.getSwfurl())){
//				OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), auc.getSwfurl());
//				OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
//			}
//			
//			auc.setSwfurl(filepath + filename);
//		}
		
		auc.setName(obj.getName());
		AuctionService.editAuction(auc);
		
		return "redirect:/auction/applytip";
	}

	
	/**
	 * 删除拍卖行
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/del", method = RequestMethod.GET)
	public String delAuction(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "删除拍卖行");

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/delauction";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/delauction";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/delauction";
		}

		int auctionid = HttpUtil.getInt(request, "id");
		Auction auc = AuctionService.getAuction(auctionid);
		if(auc == null) {
			//拍卖行不存在
			request.setAttribute("ERR_CODE", "AUCTION_NOT_EXIST");
			return "auction_jsp/delauction";
		}
		
		//删除该记录对应文件
		if(StringUtils.hasText(auc.getSwfurl())){
			OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), auc.getSwfurl());
			OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
		}
		
		//删除对应记录
		AuctionService.delAuction(auctionid);

		return "auction_jsp/delauction";
	}

	
	/**
	 * 拍卖行删除处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auction/del", method = RequestMethod.POST)
	public String doDelAuction(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "删除拍卖行");

		int gameid = HttpUtil.getInt(request, "gameid",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "auction_jsp/dodelauction";
		}

		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "auction_jsp/dodelauction";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "auction_jsp/dodelauction";
		}
		
		int auctionid = HttpUtil.getInt(request, "id");
		Auction auc = AuctionService.getAuction(auctionid);
		if(auc == null) {
			//拍卖行不存在
			request.setAttribute("ERR_CODE", "AUCTION_NOT_EXIST");
			return "auction_jsp/dodelauction";
		}
		
		AuctionService.delAuction(auctionid);
		
		return "redirect:/auction/list"+gameid+"/10/1";

	}
	
}
