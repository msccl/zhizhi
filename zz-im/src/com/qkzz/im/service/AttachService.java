package com.qkzz.im.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.qkzz.game.bean.GameTools;
import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameToolsService;
import com.qkzz.game.service.UserToolboxService;
import com.qkzz.im.bean.AttachBean;
import com.qkzz.im.bean.AttachItem;
import com.qkzz.im.bean.ItemBean;
import com.qkzz.im.bean.MailAttach;
import com.qkzz.im.dao.AttachItemDao;
import com.qkzz.im.dao.MailAttachDao;
import com.qkzz.im.dao.impl.AttachItemDaoImpl;
import com.qkzz.im.dao.impl.MailAttachDaoImpl;
import com.qkzz.money.bean.MoneyInfo;
import com.qkzz.money.service.MoneyService;
import com.qkzz.user.service.UserService;

public class AttachService {
	private static MailAttachDao attachDao = new MailAttachDaoImpl();
	private static AttachItemDao itemDao = new AttachItemDaoImpl();

	/**
	 * 添加附件，成功后返回附件记录ID
	 * @param attach 字段格式：{"gameid":1,"coin":[{"id":4,"num":40}],"item":[{"id":1,"num":10},{"id":2,"num":20},{"id":3,"num":30}]}";
	 * @return
	 */
	public static int addAttach(MailAttach bean) {

		int attachid = 0;//附件ID
		
		//执行记录插入操作
		int res = attachDao.add(bean);
		System.out.println("add attach reslut:"+res);
		if(res != -1) {
			System.out.println("attach 1111111111111===rec:"+bean.getReceiveuid() + "====send:"+bean.getSenduid());
			attachid = attachDao.getLastInsertID(bean.getReceiveuid());
			
			System.out.println("attach stirng==="+bean.getAttach());
			//解析json信息
			JSONObject json = JSONObject.fromObject(bean.getAttach());
			Map classMap = new HashMap();
			classMap.put( "coin", ItemBean.class );
			classMap.put("item", ItemBean.class);
			AttachBean beans = (AttachBean)JSONObject.toBean(json, AttachBean.class,classMap);
			System.out.println(beans.getGameid());

			List<ItemBean> items = beans.getItems();
			if(items != null && items.size() > 0) {
				System.out.println("items list size:"+items.size());
				for(int i=0;i<items.size();i++) {
					ItemBean item = (ItemBean)items.get(i);
					System.out.println(item.getId()+"==="+item.getNum());
					
					//判断附件的不同检测类型，如果是不检测类型，只需直接添加即可，不需要判断数量问题，只负责中转
					if(bean.getChecktype() == 1) {
						//不检测
						
						//直接添加到附件中
						System.out.println("==checktype:不检测==add item====send:"+bean.getSenduid()+"=====gameid:"+bean.getGameid()+"===attachid:"+attachid);
						AttachItem attachItem = new AttachItem();
						attachItem.setAttachid(attachid);
						attachItem.setType(0);
						attachItem.setItemid(item.getId());
						attachItem.setCount(item.getNum());
						itemDao.addItem(attachItem);

					} else {
						//检测

						//判断道具是否存在已经数量是否充足，判断后从用户账户总扣除，到期后如果仍然没有领取自动返还
						UserToolbox toolbox = UserToolboxService.getByInfo(bean.getSenduid(), bean.getGameid(), item.getId());

						if(toolbox == null) {
							continue;
						}

						//判断道具数量是否充足
						if(toolbox.getNum() < item.getNum()) {
							continue;
						}

						//从发送者道具箱中减少道具数量，添加到附件中
						System.out.println("==checktype:检测==add item====send:"+bean.getSenduid()+"=====gameid:"+bean.getGameid());
						int decres = UserToolboxService.decToolsNum(bean.getSenduid(), bean.getGameid(), item.getId(), (int)item.getNum());
						if(decres != -1) {
							System.out.println("555555555555====attachid:"+attachid);
							AttachItem attachItem = new AttachItem();
							attachItem.setAttachid(attachid);
							attachItem.setType(0);
							attachItem.setItemid(item.getId());
							attachItem.setCount(item.getNum());
							itemDao.addItem(attachItem);
						}

					}
				}
			}
			
			List<ItemBean> coins = beans.getCoins();
			if(coins != null && coins.size() > 0) {
				System.out.println("coins list size:"+coins.size());
				for(int i=0;i<coins.size();i++) {
					ItemBean item = (ItemBean)coins.get(i);
					System.out.println(item.getId()+"==="+item.getNum());

					
					//判断附件的不同检测类型，如果是不检测类型，只需直接添加即可，不需要判断数量问题，只负责中转
					if(bean.getChecktype() == 1) {
						//不检测

						AttachItem attachItem = new AttachItem();
						attachItem.setAttachid(attachid);
						attachItem.setType(1);
						attachItem.setItemid(item.getId());
						attachItem.setCount(item.getNum());
						itemDao.addItem(attachItem);
						
					} else {
						//检测
						
						//判断货币是否存在以及数量是否充足，判断后从用户账户总扣除，到期后如果仍然没有领取自动返还
						double sendMoney = item.getNum();
						double money = MoneyService.getMoney(bean.getSenduid(), item.getId());
						if(money == 0) {
							continue;
						} else {
							if(money < item.getNum()) {
								sendMoney = money;  
							}
						}

						String receivename = UserService.getName(bean.getReceiveuid());
						int decres = MoneyService.decMoney(bean.getSenduid(), item.getId(), sendMoney, 9, receivename, AttachService.class);

						if(decres >=0) {
							AttachItem attachItem = new AttachItem();
							attachItem.setAttachid(attachid);
							attachItem.setType(1);
							attachItem.setItemid(item.getId());
							attachItem.setCount(sendMoney);
							itemDao.addItem(attachItem);
						}

					}
				}
			}
		}
		System.out.println("attach end......"+attachid);
		return attachid;
	}
	

	public static MailAttach getAttach(long receiveuid,int attachid) {
		return attachDao.getAttach(receiveuid, attachid);
	}
	
	
	public static List<AttachItem> getItemList(int attachid) {
		return itemDao.getItemList(attachid);
	}
	
	public static int changeItemStatus(int attachid,int id,int status) {
		return itemDao.changeStatus(attachid, id, status);
	}

	/**
	 * 从附件json格式中获取gameid
	 * @param attach
	 * @return
	 */
	public static int getGameIdFromAttach(String attach) {
		JSONObject json = JSONObject.fromObject(attach);
		String gamecode = json.getString("gameid");
		if(gamecode.equals("")) {
			return 0;
		}
		return GameInfoService.getGameIDByGameCode(gamecode);
	}
	
	
	/**
	 * 接收者领取附件中的道具和货币
	 * @param receiveuid ：接收者ID
	 * @param attachid   ：附件ID
	 * @param type       ：附件类型 0：道具  1：货币
	 * @param itemid     ：附件内容ID
	 * @return
	 */
	public static String fetchItem(long receiveuid,int attachid,int type,int itemid) {

		if(type != 0 && type != 1) {
			//类型错误，目前只有道具和货币两种类型
			return "{\"result\":\"-1\"}";
		}
		
		//判断附件是否存在
		MailAttach attach = attachDao.getAttach(receiveuid, attachid);
		if(attach == null) {
			//附件记录不存在，不能领取
			return "{\"result\":\"-2\"}";
		}
		
		if(attach.getReceiveuid() != receiveuid) {
			//接受者不匹配，不能领取
			return "{\"result\":\"-3\"}";
		}
		
		//判断附件中要领取的内容是否存在或者是否已经被领取
		AttachItem item = itemDao.getItem(attachid, type, itemid);
		if(item == null) {
			//附件不存在，无法领取
			return "{\"result\":\"-4\"}";
		}
		
		if(item.getStatus() == 1) {
			//已经状态为已经领取，不能再次领取
			return "{\"result\":\"-5\"}";
		}
		
		//改变附件状态为已经领取
		int res = itemDao.changeStatus(attachid, item.getId(), 1);
		
		//将附件增加到用户道具包或者货币账户上
		if(res != -1) {
			
			if(attach.getChecktype() == 1) {
				//附件不检测，返回json格式的附件内容，包括道具名称，图片，附件数量等等
				String result = "";
				if(type == 0) {
					//提取道具信息
					GameTools tools = GameToolsService.getById(attach.getGameid(), item.getItemid());
					if(tools == null) {
						//该道具信息没有被添加到数据库中，无法提取
						return "{\"result\":\"-8\"}";
					}
					
					String toolname = tools.getName();
					String toolimg = tools.getImg();
					String toolidingame = tools.getIdingame();
					
					//道具ID 道具名 道具图片 提取类型 是必须要给我的
					result = "{\"result\":\"2\",\"checktype\":\"1\",\"item\":[{\"id\":\""+toolidingame+"\",\"num\":\""+item.getCount()+"\",\"name\":\""+toolname+"\",\"img\":\""+toolimg+"\"}]}";
				} else {
					//提取货币信息
					MoneyInfo mi = MoneyService.getMoneyInfo(item.getItemid());
					if(mi == null) {
						//该货币信息没有被添加到数据库中，无法提取
						return "{\"result\":\"-9\"}";
					}

					//货币ID 货币名 提取类型，还需要确定货币的需求
					String moneyname = mi.getName();
					result = "{\"result\":\"3\",\"checktype\":\"1\",\"coin\":[{\"id\":\""+item.getItemid()+"\",\"num\":\""+item.getCount()+"\",\"name\":\""+moneyname+"\"}]}";
				}

				return result;
				
			} else {
				//检测类型
				
				if(type == 0) {
					//增加道具
					int incres = UserToolboxService.incToolsNum(attach.getReceiveuid(), attach.getGameid(), item.getItemid(), (int)item.getCount());
					if(incres != -1) {
						//领取道具成功
						return "{\"result\":\"0\"}";
					} else {
						//领取道具失败
						return "{\"result\":\"-6\"}";
					}
				} else {
					//增加货币
					String sendername = UserService.getName(attach.getSenduid());
					int incres = MoneyService.incMoney(attach.getReceiveuid(), item.getItemid(), item.getCount(), 10, sendername, AttachService.class);

					if(incres < 0 ) {
						//货币领取失败
						return "{\"result\":\"-7\"}";
					} else {
						//货币领取成功
						return "{\"result\":\"1\"}";
					}
				}
				
			}
			
		} 
		
		return "{\"result\":\"-1000\"}";
	}

	public static void main(String[] args) {
//		AttachBean beans = new AttachBean();
//		
//		beans.setGameid(1);
//		
//		List<ItemBean> item = new ArrayList<ItemBean>();
//		ItemBean bean = new ItemBean();
//		bean.setId(1);
//		bean.setNum(10);
//		item.add(bean);
//		
//		bean.setId(2);
//		bean.setNum(20);
//		item.add(bean);
//
//		bean.setId(3);
//		bean.setNum(30);
//		item.add(bean);
//		
//		beans.setItem(item);
//
//		List<ItemBean> coin = new ArrayList<ItemBean>();
//		bean.setId(4);
//		bean.setNum(40);
//		coin.add(bean);
//		
//		beans.setCoin(coin);
//		
//		JSONArray json = JSONArray.fromObject(beans);
//		System.out.print(json.toString());


//		String attach = "{\"coin\":[{\"id\":4,\"num\":40}],\"gameid\":1,\"item\":[{\"id\":1,\"num\":10},{\"id\":2,\"num\":20},{\"id\":3,\"num\":30}]}";
		String attach = "{\"gameid\":\"BE7DF095C0F754EE0AFB91239B046878\",\"item\":[{\"id\":19,\"num\":8}],\"coin\":[{\"id\":4,\"num\":40}]}";
		JSONObject json = JSONObject.fromObject(attach);
		System.out.println("gameid:"+ json.getString("gameid"));
		Map classMap = new HashMap();  
		classMap.put( "coin", ItemBean.class );
		classMap.put("item", ItemBean.class);
		AttachBean beans = (AttachBean)JSONObject.toBean(json, AttachBean.class,classMap);
		System.out.println(beans.getGameid());
		System.out.println(beans.getItems());
		List<ItemBean> items = beans.getItems();
		for(int i=0;i<items.size();i++) {
			ItemBean item = (ItemBean)items.get(i);
			System.out.println(item.getId()+"==="+item.getNum());
		}
		
		List<ItemBean> coins = beans.getCoins();
		for(int i=0;i<coins.size();i++) {
			ItemBean item = (ItemBean)coins.get(i);
			System.out.println(item.getId()+"==="+item.getNum());
		}

	
	}
}
