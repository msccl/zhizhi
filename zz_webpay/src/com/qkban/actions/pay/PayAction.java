package com.qkban.actions.pay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qkban.alipay.CheckURL;
import com.qkban.bean.user.User;
import com.qkban.service.money.AlipayWebLogService;
import com.qkban.service.money.MoneyService;
import com.qkban.service.user.UserService;
import com.qkban.util.DateGet;
import com.qkban.util.HttpUtil;
import com.qkban.util.ToolsKit;
import com.qkban.util.TypeTrans;


@Controller
public class PayAction {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "/index";
	}


	
	@RequestMapping(value = "/payconfirm", method = RequestMethod.POST)
	public String paycofirm(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = HttpUtil.getString(request, "userid");
		String useridconfirm = HttpUtil.getString(request, "useridconfirm","");
		String amount = HttpUtil.getString(request, "money","");
		
		if(userid.equals("") || useridconfirm.equals("")) {
			request.setAttribute("ERR_MSG", "UID_EMPTY");
			return "webpay_jsp/payconfirm";
		}
		
		if(!userid.equals(useridconfirm)) {
			request.setAttribute("ERR_MSG", "UID_NOT_EQUAL");
			return "webpay_jsp/payconfirm";
		}
		
		if(amount.equals("")) {
			request.setAttribute("ERR_MSG", "AMOUNT_EMPTY");
			return "webpay_jsp/payconfirm";
		}
		
		double money = 0;
		try {
			money = Double.parseDouble(amount);
		} catch(Exception e) {
			request.setAttribute("ERR_MSG", "AMOUNT_FORMAT_WRONG");
			return "webpay_jsp/payconfirm";
		}
		
		User user = UserService.getByName(userid);
		if(user == null) {
			request.setAttribute("ERR_MSG", "USER_NOT_EXIST");
			return "webpay_jsp/payconfirm";
		}
		//确认用户身份后，构造一个订单号，方便用户今后查询用
		
		request.setAttribute("userid", userid);
		request.setAttribute("money", amount);
		request.setAttribute("productTitle", "7kban账户充值"+amount+"元");
		request.setAttribute("indentnumber", "WXAM-"+user.getId()+"-"+DateGet.patternPrint("yyyyMMddHHmmss", System.currentTimeMillis())+""+TypeTrans.getRand(10,99));
		
		return "webpay_jsp/payconfirm";
	}

	
	
	
	@RequestMapping(value = "/alipayReturn", method = RequestMethod.GET)
	public String alipayReturn(HttpServletRequest request, HttpServletResponse response) {
		
		
		String partner = "2088002096999840"; //partner合作伙伴id（必须填写）
		String privateKey = "6wxi30wlrrcp5vwo6skt55yss830lu8v"; //partner 的对应交易安全校验码（必须填写）
		//**********************************************************************************
		//如果您服务器不支持https交互，可以使用http的验证查询地址

		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
						+ "&partner="
						+ partner
						+ "&notify_id="
						+ request.getParameter("notify_id");
		String sign=request.getParameter("sign");
		//获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		String responseTxt = CheckURL.check(alipayNotifyURL);

		Map params = new HashMap();
		//获得POST 过来参数设置到新的params中
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
				
		String mysign = com.qkban.alipay.SignatureHelper_return.sign(params, privateKey);
			
		String tradeno = (String)params.get("out_trade_no");
		String money = (String)params.get("total_fee");

		//打印，收到消息比对sign的计算结果和传递来的sign是否匹配
		if (mysign.equals(request.getParameter("sign")) && responseTxt.equals("true") ){
			request.setAttribute("orderstatus", "success");
		} else {
			request.setAttribute("orderstatus", "fail");
		}
		request.setAttribute("indentnumber", tradeno);
		request.setAttribute("money", money);

		
		return "webpay_jsp/paynetres";
	}


	@RequestMapping(value = "/alipayNotify", method = RequestMethod.POST)
	public String alipayNotify(HttpServletRequest request, HttpServletResponse response) {
		
		String partner = "2088002096999840"; //partner合作伙伴id（必须填写）
		String privateKey = "6wxi30wlrrcp5vwo6skt55yss830lu8v"; //partner 的对应交易安全校验码（必须填写）
		//**********************************************************************************
		//如果您服务器不支持https交互，可以使用http的验证查询地址
	    String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
				+ "&partner="
				+ partner
				+ "&notify_id="
				+ request.getParameter("notify_id");

	    //获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		String responseTxt = CheckURL.check(alipayNotifyURL);

		Map params = new HashMap();
		//获得POST 过来参数设置到新的params中
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";				
			}
			params.put(name, valueStr);
		}
		String mysign = com.qkban.alipay.SignatureHelper.sign(params, privateKey);

		String status = "fail";
		if (mysign.equals(request.getParameter("sign")) && responseTxt.equals("true") ){
			String tradeno = (String)params.get("out_trade_no");
			String money = (String)params.get("total_fee");
			String[] tmp = tradeno.split("-");
			if(tmp != null && tmp.length == 3) {
				String userid = tmp[1].trim();
				//将用户的充值增加到对应账户上并添加充值记录
				MoneyService.incMoney(Integer.parseInt(userid), Double.parseDouble(money), 8, "用户:"+userid, this);
				AlipayWebLogService.addLog(tradeno, userid, Double.parseDouble(money), ToolsKit.getRemoteAddr(request));
				
			} else {
				System.out.println("充值订单号传递不完整");
			}
			System.out.println("=======alipay notify success,充值订单号："+tradeno);
			//判断如果该订单号对应的订单状态没有改变，那么调整状态
			status = "success";
		} else {
			System.out.println("充值Notify失败");
		}

		request.setAttribute("status", status);
		
		return "webpay_jsp/alipay_notify";
	}


}
