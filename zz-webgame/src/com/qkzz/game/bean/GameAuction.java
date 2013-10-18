package com.qkzz.game.bean;


public class GameAuction {

	private int id;
	private int gameid;			/* 游戏ID */
	private String name;		/* 拍卖行名称 */
	private String swfurl;		/* 拍卖行SWF地址 */
	private int status;			/* 拍卖行状态 0:等待审核 1:审核通过 -1:审核未通过 */
	private String createtime;	/* 创建时间 */
    
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
	public String getSwfurl() {
		return swfurl;
	}
	public void setSwfurl(String swfurl) {
		this.swfurl = swfurl;
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
	
}
