package com.qkzz.web.developer.bean;

/**
 * 道具
 * @author Administrator
 *
 */
public class Tools {
	private int id;
	private int gameid;//游戏ID
	private String name;//道具名称
	private String intro;//道具描述
	private String img;//道具图片
	private int canexchange;//是否可以交易 0：不可以 1：可以
	private int canauction;//是否可以拍卖 0：不可以 1：可以
	private int candrop;//是否可以游戏中掉落 0：不可以 1：可以
	private String functiondefine;//功能数值，键值对
	private int moneyid;//货币ID，如果是0，直接使用吱币
	private double price;//道具价格
	private int status;//道具状态 0：等待审核 1：审核通过 -1：审核未通过
	private String createtime;//创建时间
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
	public int getCanexchange() {
		return canexchange;
	}
	public void setCanexchange(int canexchange) {
		this.canexchange = canexchange;
	}
	public int getCanauction() {
		return canauction;
	}
	public void setCanauction(int canauction) {
		this.canauction = canauction;
	}
	public String getFunctiondefine() {
		return functiondefine;
	}
	public void setFunctiondefine(String functiondefine) {
		this.functiondefine = functiondefine;
	}
	public int getMoneyid() {
		return moneyid;
	}
	public void setMoneyid(int moneyid) {
		this.moneyid = moneyid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public int getCandrop() {
		return candrop;
	}
	public void setCandrop(int candrop) {
		this.candrop = candrop;
	}
}
