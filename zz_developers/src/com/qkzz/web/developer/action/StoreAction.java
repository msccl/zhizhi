package com.qkzz.web.developer.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qkzz.util.CookieUtil;
import com.qkzz.util.HttpUtil;
import com.qkzz.util.LoadResour;
import com.qkzz.util.OperationFile;
import com.qkzz.util.Pager;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.bean.Store;
import com.qkzz.web.developer.bean.StoreRetBean;
import com.qkzz.web.developer.service.GameInfoService;
import com.qkzz.web.developer.service.StoreService;

@Controller
public class StoreAction {
	
	private String swfpath = "/swf/";
	private String imgpath = "/img/";

	/**
	 * 为游戏申请新的商店
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/store/applyNew", method = RequestMethod.GET)
	public String applyNewStore(HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "申请商店");
		
		int gameid = HttpUtil.getInt(request, "id",0);
		if(gameid == 0) {
			//游戏ID丢失
			request.setAttribute("ERR_CODE", "PARAM_MISS");
			return "store_jsp/addstore";
		}
		
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "store_jsp/addstore";
		}

		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "store_jsp/addstore";
		}
		
		request.setAttribute("gameid", gameid);
				
		return "store_jsp/addstore";
	}

	
	/**
	 * 申请商店提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/store/applyNew", method = RequestMethod.POST)
	public void doApplyNewStore(//@RequestParam("swfurl") MultipartFile swf,
			Store obj,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "申请商店");
		
		String name = HttpUtil.getString(request, "name","");
		PrintWriter out = response.getWriter();

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
		
		Store store = new Store();
		store.setName(obj.getName());
		store.setSwfurl(_swf);
		store.setGameid(obj.getId());
		store.setCanrecycle(obj.getCanrecycle());
		store.setRecyclerate(obj.getRecyclerate());
		int res = StoreService.addStore(store);
		if(res != -1) {
			int storeid = StoreService.getLastInsertID(obj.getId(), obj.getName());
			out.println("{\"result\":\""+res+"\",\"storeid\":\""+storeid+"\"}");
	        out.flush();    
	        out.close();    
			return;
		}
		out.println("{\"result\":\""+res+"\"}");
        out.flush();    
        out.close();    
	}

	
//	/**
//	 * 申请成功提示页，防止刷新
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/store/applytip", method = RequestMethod.GET)
//	public String doAddStoreTip(HttpServletRequest request, HttpServletResponse response) {
//
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("user", ub);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("pageTitle", "申请商店");
//		
//		return "store_jsp/applytip";
//	}

	
	/**
	 * 商店列表，可翻页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/store/list/{gameid}/{size}/{page}", method = RequestMethod.GET)
	public String listStore(@PathVariable("gameid") int gameid,@PathVariable("size") int size,@PathVariable("page") int page,HttpServletRequest request, HttpServletResponse response) {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "商店列表");
		
		GameInfo bean = GameInfoService.getGame(gameid);
		if(bean == null) {
			//游戏不存在
			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
			return "store_jsp/list";
		}
		
		if(bean.getDeveloperid() != ub.getId()) {
			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
			return "store_jsp/list";
		}

		List<Store> storeList = StoreService.getGameStore(gameid, (page-1)*size, size);
		request.setAttribute("storeList", storeList);
		request.setAttribute("bean", bean);
		
		int total = StoreService.getGameStoreMaxCount(gameid);
		request.setAttribute("turnpage", Pager.pagination(total, size, page, "/store/list/"+gameid+"/"));
		
		return "store_jsp/list";
	}



	/**
	 * 编辑商店信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/store/edit", method = RequestMethod.GET)
	public void editStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑商店");
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

		int storeid = HttpUtil.getInt(request, "id");
		Store store = StoreService.getStore(storeid);
		if(store == null) {
			//商店不存在
			out.println("{\"result\":\"-4\"}");
	        out.flush();    
	        out.close();    
			return;
		}
		
		StoreRetBean retBean = new StoreRetBean();
		retBean.setResult(1);
		retBean.setStore(store);

		JSONArray json = JSONArray.fromObject(retBean);
		out.println(json.toString());
        out.flush();
        out.close();
	}

	
	/**
	 * 编辑商店提交处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/store/edit", method = RequestMethod.POST)
	public void doEditStore(//@RequestParam("swfurl") MultipartFile swf,
			Store obj,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "编辑商店");
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

		int storeid = HttpUtil.getInt(request, "id");
		Store store = StoreService.getStore(storeid);
		if(store == null) {
			//商店不存在
			out.println("{\"result\":\"-5\"}");
	        out.flush();    
	        out.close();
	        return;
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
//			//删除该记录对应文件
//			if(StringUtils.hasText(store.getSwfurl())){
//				OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), store.getSwfurl());
//				OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
//			}
//			
//			store.setSwfurl(filepath + filename);
//		}
				
		store.setName(obj.getName());
		store.setCanrecycle(obj.getCanrecycle());
		store.setRecyclerate(obj.getRecyclerate());
		int res = StoreService.editStore(store);
		if(res == -1){
			out.println("{\"result\":\""+res+"\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		store = StoreService.getStore(storeid);
		out.println("{\"result\":\""+res+"\",\"storeid\":\""+storeid+"\",\"status\":\""+store.getStatus()+"\"}");
        out.flush();    
        out.close();
	}
	

	/**
	 * 删除商店
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/store/del", method = RequestMethod.GET)
	public void delStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("user", ub);
		request.setAttribute("navid", 2);//用于页面顶部导航
		request.setAttribute("pageTitle", "删除商店");
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

		int storeid = HttpUtil.getInt(request, "id");
		Store store = StoreService.getStore(storeid);
		if(store == null) {
			//商店不存在
			out.println("{\"result\":\"-5\"}");
	        out.flush();    
	        out.close();
	        return;
		}

		//删除该记录对应文件
		if(StringUtils.hasText(store.getSwfurl())){
			OperationFile.delFile(LoadResour.getMMSRootPath("HTMLRoot"), store.getSwfurl());
			OperationFile.delDir(LoadResour.getMMSRootPath("HTMLRoot"));
		}
		
		//删除对应记录
		int res = StoreService.delStore(storeid);

		out.println("{\"result\":\""+res+"\"}");
        out.flush();    
        out.close();
	}


//	/**
//	 * 删除商店提交处理
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/store/del", method = RequestMethod.POST)
//	public String doDelStore(HttpServletRequest request, HttpServletResponse response) {
//		
//		Developers ub = CookieUtil.getCookieLoginUser(request, response);
//		request.setAttribute("user", ub);
//		request.setAttribute("navid", 2);//用于页面顶部导航
//		request.setAttribute("pageTitle", "删除商店");
//
//		int gameid = HttpUtil.getInt(request, "gameid",0);
//		if(gameid == 0) {
//			//游戏ID丢失
//			request.setAttribute("ERR_CODE", "PARAM_MISS");
//			return "store_jsp/dodelstore";
//		}
//
//		GameInfo bean = GameInfoService.getGame(gameid);
//		if(bean == null) {
//			//游戏不存在
//			request.setAttribute("ERR_CODE", "GAME_NOT_EXIST");
//			return "store_jsp/dodelstore";
//		}
//
//		if(bean.getDeveloperid() != ub.getId()) {
//			request.setAttribute("ERR_CODE", "NOT_VALID_USER");
//			return "store_jsp/dodelstore";
//		}
//
//		int storeid = HttpUtil.getInt(request, "id");
//		Store store = StoreService.getStore(storeid);
//		if(store == null) {
//			//商店不存在
//			request.setAttribute("ERR_CODE", "STORE_NOT_EXIST");
//			return "store_jsp/dodelstore";
//		}
//		
//		StoreService.delStore(storeid);
//		
//		return "redirect:/store/list"+gameid+"/10/1";
//	}

	
}
