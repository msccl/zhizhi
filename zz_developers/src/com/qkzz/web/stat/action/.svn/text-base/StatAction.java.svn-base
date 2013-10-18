package com.qkzz.web.stat.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qkzz.util.CookieUtil;
import com.qkzz.util.MDate;
import com.qkzz.util.Pager;
import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.service.GameInfoService;
import com.qkzz.web.stat.bean.StatGame;
import com.qkzz.web.stat.service.StatGameService;

@Controller
public class StatAction {

	/**
	 * 游戏列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/stat/list/{size}/{page}", method = RequestMethod.GET)
	public String list(@PathVariable("size") int size,
			@PathVariable("page") int page, 
			@RequestParam(value="date", required = false) String date,
			HttpServletRequest request,	HttpServletResponse response) {

		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 3);// 用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "游戏列表");

		if(!StringUtils.hasText(date)){
			date = MDate.calYesterday();
		}
		
		List<StatGame> gameList = new ArrayList<StatGame>();
		
		//获取用户游戏总数
		int count = GameInfoService.getGameMaxCount(ub.getId());
		//获取用户游戏列表
		List<GameInfo> list = GameInfoService.getGameList(ub.getId(),(page-1)*size, size);		
		if(!list.isEmpty()){
			for(GameInfo info: list){
				System.out.println(info.getGamecode());
				// 获取game在对应date统计数据
				StatGame obj = StatGameService.getByGamecode(info.getGamecode(), date);
				if(obj==null){
					obj = new StatGame();
					obj.setGamecode(info.getGamecode());
					obj.setGamename(info.getName());
					obj.setCreatedate(date);
					obj.setNum(0);
					obj.setIp_num(0);
					obj.setUserid_num(0);
				}
				gameList.add(obj);
			}
		}
		
		//打造分页
		String url = Pager.pagination(count, size, page, new StringBuffer().append("/stat/list/").append("?date=").append(date).toString());

		request.setAttribute("gameList", gameList);
		request.setAttribute("turnpage", url);
		request.setAttribute("date", date);
		
		return "stat_jsp/list";

	}

	/**
	 * 指定游戏日期段统计列表
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/stat/detail/{size}/{page}", method = RequestMethod.GET)
	public String detail(@PathVariable("size") int size,
			@PathVariable("page") int page,
			@RequestParam(value="begindate", required = false) String begindate,
			@RequestParam(value="enddate", required = false) String enddate,
			@RequestParam(value="code", required = false) String code,
			HttpServletRequest request,	HttpServletResponse response) {

		//无游戏代码时，重定向到统计首页
		if(!StringUtils.hasText(code)){
			return "redirect:/stat/list/10/1";
		}
		
		if(!StringUtils.hasText(begindate)){
			begindate = MDate.calYesterday();
		}
		if(!StringUtils.hasText(enddate)){
			enddate = MDate.calYesterday();
		}
		
		Developers ub = CookieUtil.getCookieLoginUser(request, response);
		request.setAttribute("navid", 3);// 用于页面顶部导航
		request.setAttribute("user", ub);
		request.setAttribute("pageTitle", "游戏列表");
		
		String gamename = "";
		int count = StatGameService.countByList(code, begindate, enddate);
		List<StatGame> gameList = StatGameService.getByList(code, begindate, enddate, (page-1)*size, size);
		if(!gameList.isEmpty()){
			if(gameList.size()>0){
				gamename = gameList.get(0).getGamename();
			}
		}
		
		//打造分页
		String url = Pager.pagination(count, size, page, new StringBuffer().append("/stat/detail/").append("?code=").append(code).append("&begindate=").append(begindate).append("&enddate=").append(enddate).toString());

		request.setAttribute("gameList", gameList);
		request.setAttribute("turnpage", url);
		
		request.setAttribute("begindate", begindate);
		request.setAttribute("enddate", enddate);
		request.setAttribute("gamename", gamename);
		request.setAttribute("gamecode", code);

		return "stat_jsp/list_detail";

	}

}
