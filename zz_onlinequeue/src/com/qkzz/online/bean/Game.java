package com.qkzz.online.bean;

public class Game
{
  private long uid;
  private String name;
  private long lasttime;
  private String lasturl;
  private int lastgame;

  public long getUid()
  {
    return this.uid;
  }
  public void setUid(long uid) {
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
  public int getLastgame() {
    return this.lastgame;
  }
  public void setLastgame(int lastgame) {
    this.lastgame = lastgame;
  }
}