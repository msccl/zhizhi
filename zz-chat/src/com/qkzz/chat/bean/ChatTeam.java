package com.qkzz.chat.bean;

public class ChatTeam {
	private long id;//队伍ID
	private String name;//队伍名称
	private long leaderuid;//创建者ID，默认为队长'
	private String password;//加入密码，默认为空
	private int gameid;//游戏ID
	private String createtime;//创建时间
	private long lasttime;//最后聊天时间，用于清理过期队伍
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLeaderuid() {
		return leaderuid;
	}
	public void setLeaderuid(long leaderuid) {
		this.leaderuid = leaderuid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public long getLasttime() {
		return lasttime;
	}
	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	
}
