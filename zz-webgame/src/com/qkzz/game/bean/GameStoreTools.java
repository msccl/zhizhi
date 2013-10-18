package com.qkzz.game.bean;

/**
 * 商店道具表 (16表)
 * @author 
 *
 */
public class GameStoreTools {
	
	private int id;
	private int gameid;			/* 游戏ID */
	private int storeid;		/* 商店ID */
	private int toolsid;		/* 道具ID */
	private int num;			/* 数量 */
	private int status;			/* 销售状态 0:正常 1:暂停 2:下架 */
	private String createtime;	/* 创建时间 */
	
	private String name;		/* 道具名称 */
	private String intro;		/* 道具介绍 */
	private String img;			/* 道具图片 */
	private double price;		/* 道具价格 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public int getToolsid() {
		return toolsid;
	}
	public void setToolsid(int toolsid) {
		this.toolsid = toolsid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
