package com.qkzz.online.dao.impl;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.GameDao;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameDaoImpl
  implements GameDao
{

  @Resource
  private JdbcTemplate onlineTemplate;

  public boolean addAll(Queue obj)
  {
    String sql = "insert into game_online(uid,name,lasttime,lasturl,lastgame) values(?,?,?,?,?)";
    return this.onlineTemplate.update(sql, new Object[] { 
      Integer.valueOf(obj.getUid()), 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()) }) > 0;
  }

  public boolean isExistAll(int uid)
  {
    String sql = "select count(*) from game_online where uid=?";
    return this.onlineTemplate.queryForInt(sql, new Object[] { Integer.valueOf(uid) }) > 0;
  }

  public boolean updateAll(Queue obj) {
    String sql = "update game_online set name=?,lasttime=?,lasturl=?,lastgame=? where uid=?";
    return this.onlineTemplate.update(sql, new Object[] { 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      Integer.valueOf(obj.getUid()) }) > 0;
  }

  public boolean add(Queue obj)
  {
    String sql = "insert into game_online" + (obj.getUid() & 0xFF) + "(uid,name,lasttime,lasturl,lastgame,status) values(?,?,?,?,?,?)";
    return this.onlineTemplate.update(sql, new Object[] { 
      Integer.valueOf(obj.getUid()), 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      Integer.valueOf(obj.getStatus()) }) > 0;
  }

  public boolean isExist(int uid)
  {
    String sql = "select count(*) from game_online" + (uid & 0xFF) + " where uid=?";
    return this.onlineTemplate.queryForInt(sql, new Object[] { Integer.valueOf(uid) }) > 0;
  }

  public boolean update(Queue obj) {
    String sql = "update game_online" + (obj.getUid() & 0xFF) + " set name=?,lasttime=?,lasturl=?,lastgame=?,status=? where uid=?";
    return this.onlineTemplate.update(sql, new Object[] { 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      Integer.valueOf(obj.getStatus()), 
      Integer.valueOf(obj.getUid()) }) > 0;
  }
}