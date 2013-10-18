package com.qkzz.online.bean;

public class Queue
{
  private long id;
  private int uid;
  private String name;
  private long lasttime;
  private String lasturl;
  private int gameid;
  private int status;
  private String sign;

  public long getId()
  {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public int getUid() {
    return this.uid;
  }
  public void setUid(int uid) {
    this.uid = uid;
  }
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public long getLasttime() {
    return this.lasttime;
  }
  public void setLasttime(long lasttime) {
    this.lasttime = lasttime;
  }
  public String getLasturl() {
    return this.lasturl;
  }
  public void setLasturl(String lasturl) {
    this.lasturl = lasturl;
  }
  public int getGameid() {
    return this.gameid;
  }
  public void setGameid(int gameid) {
    this.gameid = gameid;
  }
  public int getStatus() {
    return this.status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getSign() {
    return this.sign;
  }
  public void setSign(String sign) {
    this.sign = sign;
  }
}