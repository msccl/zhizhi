package com.qkzz.web.stat.bean;

public class StatAll {

	private int id; 			/* 自增编号 */
	private String createdate; 	/* 日志日期 */
	private int num; 			/* 总数 */
	private int ip_num; 		/* IP唯一数 */
	private int userid_num; 	/* 用户唯一数 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getIp_num() {
		return ip_num;
	}

	public void setIp_num(int ip_num) {
		this.ip_num = ip_num;
	}

	public int getUserid_num() {
		return userid_num;
	}

	public void setUserid_num(int userid_num) {
		this.userid_num = userid_num;
	}

	public String toString() {
		return " id=" + id + "\n createdate=" + createdate + "\n num=" + num
				+ "\n ip_num=" + ip_num + "\n userid_num=" + userid_num;
	}

}
