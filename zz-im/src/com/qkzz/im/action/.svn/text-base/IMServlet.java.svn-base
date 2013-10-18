package com.qkzz.im.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.qkzz.common.GetDate;
import com.qkzz.common.HttpUtil;
import com.qkzz.common.TypeTrans;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.bean.GameTools;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameToolsService;
import com.qkzz.im.bean.AttachItem;
import com.qkzz.im.bean.AttachRetBean;
import com.qkzz.im.bean.Inbox;
import com.qkzz.im.bean.ItemBean;
import com.qkzz.im.bean.ItemListBean;
import com.qkzz.im.bean.MailAttach;
import com.qkzz.im.bean.Outbox;
import com.qkzz.im.bean.RetInBean;
import com.qkzz.im.bean.RetOutBean;
import com.qkzz.im.service.AttachService;
import com.qkzz.im.service.InboxService;
import com.qkzz.im.service.OutboxService;
import com.qkzz.user.bean.Member;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.MemberService;
import com.qkzz.user.service.UserService;

public class IMServlet extends HttpServlet {

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
		if(act.equals("send")) {
			
			long senduid = HttpUtil.getLong(request, "senduid");
			String receiveuid = HttpUtil.getString(request, "receiveuid");
			String title = HttpUtil.getString(request, "title");
			String content = HttpUtil.getString(request, "content");
			int system = HttpUtil.getInt(request, "system",0);
//			String gamecode = HttpUtil.getString(request, "gameid");
//			int gameid = GameInfoService.getGameIDByGameCode(gamecode);
			String attach = HttpUtil.getString(request, "attach","");
			int checktype = HttpUtil.getInt(request, "checktype",0);//附件检查类型 0：检查 1：不检查，只负责中转发送 
			
			if(senduid<=0) {
				out.print("{\"result\":\"-1\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			if(receiveuid==null || "".equals(receiveuid)) {
				out.print("{\"result\":\"-2\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			if(content.equals("")) {
				out.print("{\"result\":\"-3\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			if(title.equals("")) {
				if(content.length() < 10) {
					title = content;
				} else {
					title = content.substring(0,10);
				}
			}
			String sendname = "游客";
			User user = UserService.getByUid(senduid);

			//判断发送用户是否存在
			if(user == null) {
				out.print("{\"result\":\"-4\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			sendname = user.getName();
			String datetime = GetDate.getStringDate();
			
			//判断接收方是否为数字
			long _frienduid = 0;
			if(TypeTrans.isNumber(receiveuid)) {
				//info按照uid来看
				_frienduid = Long.parseLong(receiveuid);
				//检查接收用户是否存在
				if(UserService.getByUid(_frienduid)==null){
					out.print("{\"result\":\"-5\"}");//好友不存在
					out.flush();
					out.close();
					return;
				}
			} else {
				Member member = MemberService.getByName(receiveuid);
				if(member == null) {
					out.print("{\"result\":\"-5\"}");//好友不存在
					out.flush();
					out.close();
					return;
				}
				_frienduid = member.getId();
			}
			
			//add INBOX
			Inbox inbox = new Inbox();
			inbox.setSenduid(senduid);
			inbox.setSendname(sendname);
			inbox.setReceiveuid(_frienduid);
			inbox.setTitle(title);
			inbox.setCreatetime(GetDate.getStringDate());
			inbox.setType(system);

			if(attach.equals("")) {
				inbox.setAttachid(0);
			} else {
				int gameid = AttachService.getGameIdFromAttach(attach);
				MailAttach bean = new MailAttach();
				bean.setSenduid(senduid);
				bean.setReceiveuid(_frienduid);
				bean.setGameid(gameid);
				bean.setAttach(attach);
				bean.setChecktype(checktype);
				int attachid = AttachService.addAttach(bean);
				inbox.setAttachid(attachid);
			}

			if(system==1){
				inbox.setHtmlcode(content);
				inbox.setContent("");
			}else{
				inbox.setContent(content);
				inbox.setHtmlcode("");
			}
			int status = InboxService.save(inbox);
			//add OUTBOX
			Outbox outbox = new Outbox();
			outbox.setSenduid(senduid);
			outbox.setReceiveuid(_frienduid);
			outbox.setRecname(UserService.getName(_frienduid));
			outbox.setTitle(title);
			outbox.setCreatetime(datetime);
			if(system==1){
				outbox.setHtmlcode(content);
				outbox.setContent("");
			}else{
				outbox.setContent(content);
				outbox.setHtmlcode("");
			}
			outbox.setAttach(attach);
			OutboxService.save(outbox);
			
			if(status==1){
				out.print("{\"result\":\"1000\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}else{
				out.print("{\"result\":\"-6\"}");//字符串为空
				out.flush();
				out.close();
				return;
			}
			
		}else if(act.equals("list")) {			
			String type = HttpUtil.getString(request, "type","inbox");
			long uid = HttpUtil.getLong(request, "uid");
			int pn = HttpUtil.getInt(request, "page",1);
			int count = HttpUtil.getInt(request, "count",0);
			boolean system = HttpUtil.getInt(request, "system",0)==0?false:true;
			int startIndex = (pn-1)*count;
			
			int result = 1000;//默认为成功状态
			User user = UserService.getByUid(uid);
			if(user == null) {
				if(type.equals("inbox")) {
					RetInBean rib = new RetInBean();
					rib.setList(null);
					rib.setFreshinterval(5);
					rib.setResult(-1);
					//return JSON
					if(format.equals("json")) {
						JSONArray json = JSONArray.fromObject(rib);
						out.print(json.toString());
					}
				} else if(type.equals("outbox")) {
					RetOutBean rob = new RetOutBean();
					rob.setList(null);
					rob.setFreshinterval(5);
					rob.setResult(-1);
					//return JSON
					if(format.equals("json")) {
						JSONArray json = JSONArray.fromObject(rob);
						out.print(json.toString());
					}
				}
				return;
			}

			if(type.equals("outbox")){
				List<Outbox> list = OutboxService.getByList(uid, startIndex, count);
				RetOutBean rob = new RetOutBean();
				rob.setList(list);
				rob.setFreshinterval(5);
				rob.setResult(1000);

				//return JSON
				if(format.equals("json")) {
					JSONArray json = JSONArray.fromObject(rob);
					out.print(json.toString());
				}
			}else{
				List<Inbox> list = InboxService.getByList(uid, system, startIndex, count);
				RetInBean rib = new RetInBean();
				rib.setList(list);
				rib.setFreshinterval(5);
				rib.setResult(1000);
				//return JSON
				if(format.equals("json")) {
					JSONArray json = JSONArray.fromObject(rib);
					out.print(json.toString());
				}
			}
			
		}else if(act.equals("delete")) {
			
			String type = HttpUtil.getString(request, "type","inbox");
			int id = HttpUtil.getInt(request, "id");
			long uid = HttpUtil.getLong(request, "uid");
			int status = 0;
			if(type.equals("outbox")){
				status = OutboxService.delete(uid, id);
			}else{
				status = InboxService.delete(uid, id);
			}
			//return
			out.print("{\"result\":\""+status+"\"}");//字符串为空
			out.flush();
			out.close();
			
		}else if(act.equals("read")) {
			
			String type = HttpUtil.getString(request, "type","inbox");
			int id = HttpUtil.getInt(request, "id");
			long uid = HttpUtil.getLong(request, "uid");
			if(type.equals("outbox")){
				Outbox bean = OutboxService.getById(uid, id);
				//return JSON
				if(format.equals("json")) {
					JSONArray json = JSONArray.fromObject(bean);
					out.print(json.toString());
				}
			}else{
				Inbox bean = InboxService.getById(uid, id);
				if(bean!=null){
					if(bean.getStatus()==0){
						InboxService.updateStatus(uid, id);
					}
				}
				//return JSON
				if(format.equals("json")) {
					JSONArray json = JSONArray.fromObject(bean);
					out.print(json.toString());
				}
			}
			
		}else if(act.equals("newcount")) {
			//新内线数量
			long uid = HttpUtil.getLong(request, "uid");
			
			int count = InboxService.countByStatus(uid);
			
			out.print("{\"count\":\""+count+"\"}");//字符串为空
			out.flush();
			out.close();
			
		}else if(act.equals("getattachlist")) {
			//获取附件列表
			int attachid = HttpUtil.getInt(request, "attachid",0);
			long uid = HttpUtil.getLong(request, "uid",0);
			
			if(attachid == 0) {
				out.print("{\"result\":\"-1\"}");//没有附件列表
				out.flush();
				out.close();
				return;
			}
			
			MailAttach attach = AttachService.getAttach(uid, attachid);
			if(attach == null) {
				out.print("{\"result\":\"-2\"}");//没有附件信息
				out.flush();
				out.close();
				return;
			}
		
			if(attach.getReceiveuid() != uid) {
				out.print("{\"result\":\"-3\"}");//该附件不属于该用户，不能读取
				out.flush();
				out.close();
				return;
			}
			

			List<AttachItem> list = AttachService.getItemList(attachid);
			if(list == null || list.size() == 0) {
				out.print("{\"result\":\"-4\"}");//附件列表已经领取完毕，没有信息
				out.flush();
				out.close();
				return;
			}

			GameInfo gi = GameInfoService.getGame(attach.getGameid());
			if(gi == null) {
				out.print("{\"result\":\"-5\"}");//游戏不存在
				out.flush();
				out.close();
				return;
			}
			
			List<ItemListBean> items = new ArrayList<ItemListBean>();
			List<ItemBean> coins = new ArrayList<ItemBean>();

			for(int i=0;i<list.size();i++) {
				AttachItem item = list.get(i);
				if(item.getType() == 0) {
					ItemListBean bean = new ItemListBean();
					bean.setId(item.getItemid());
					bean.setNum(item.getCount());
					GameTools tools = GameToolsService.getById(attach.getGameid(), item.getItemid());
					if(tools == null) {
						bean.setImg("");
					} else {
						bean.setImg(tools.getImg());
					}
					items.add(bean);
				} else if(item.getType() == 1) {
					ItemBean bean = new ItemBean();
					bean.setId(item.getItemid());
					bean.setNum(item.getCount());
					coins.add(bean);
				}
			}
			
			AttachRetBean retBean = new AttachRetBean();
			retBean.setResult(1);
			retBean.setGameid(gi.getGamecode());
			retBean.setItems(items);
			retBean.setCoins(coins);
			
			JSONArray json = JSONArray.fromObject(retBean);
			out.print(json.toString());
			out.flush();
			out.close();
			
		}else if(act.equals("fetchattach")) {
			//领取附件
			long uid = HttpUtil.getLong(request, "uid");//接受者ID
			int attachid = HttpUtil.getInt(request, "attachid",0);
			int type = HttpUtil.getInt(request, "type",0);//附件类型 0：道具 1：货币
			int itemid = HttpUtil.getInt(request, "itemid",0);//附件ID
			
			String res = AttachService.fetchItem(uid, attachid, type, itemid);
			
			out.print(res);//字符串为空
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
