package com.qkzz.online.dao.impl;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.OnlineDao;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OnlineDaoImpl
  implements OnlineDao
{

  @Resource
  private JdbcTemplate onlineTemplate;

  public boolean addAll(Queue obj)
  {
    String sql = "insert into online(uid,name,lasttime,lasturl,lastgame) values(?,?,?,?,?)";
    return this.onlineTemplate.update(sql, new Object[] { 
      Integer.valueOf(obj.getUid()), 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()) }) > 0;
  }

  public boolean isExistAll(int uid)
  {
    String sql = "select count(*) from online where uid=?";
    return this.onlineTemplate.queryForInt(sql, new Object[] { Integer.valueOf(uid) }) > 0;
  }

  public boolean updateAll(Queue obj) {
    String sql = "update online set name=?,lasttime=?,lasturl=?,lastgame=? where uid=?";
    return this.onlineTemplate.update(sql, new Object[] { 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      Integer.valueOf(obj.getUid()) }) > 0;
  }

  public boolean add(Queue obj)
  {
    String sql = "insert into online" + (obj.getUid() & 0xFF) + "(uid,name,lasttime,lasturl,lastgame,sign) values(?,?,?,?,?,?)";
    return this.onlineTemplate.update(sql, new Object[] { 
      Integer.valueOf(obj.getUid()), 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      obj.getSign() }) > 0;
  }

  public boolean isExist(int uid)
  {
    String sql = "select count(*) from online" + (uid & 0xFF) + " where uid=?";
    return this.onlineTemplate.queryForInt(sql, new Object[] { Integer.valueOf(uid) }) > 0;
  }

  public boolean update(Queue obj) {
    String sql = "update online" + (obj.getUid() & 0xFF) + " set name=?,lasttime=?,lasturl=?,lastgame=?,sign=? where uid=?";
    return this.onlineTemplate.update(sql, new Object[] { 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      obj.getSign(), 
      Integer.valueOf(obj.getUid()) }) > 0;
  }
}