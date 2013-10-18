package com.qkzz.user.bean;

public class ReportList {
	private long reportuid;  
	private long uid;  
	private String ip;
	private String reason;
	private String attime;

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAttime() {
		return attime;
	}
	public void setAttime(String attime) {
		this.attime = attime;
	}
	public long getReportuid() {
		return reportuid;
	}
	public void setReportuid(long reportuid) {
		this.reportuid = reportuid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
}
