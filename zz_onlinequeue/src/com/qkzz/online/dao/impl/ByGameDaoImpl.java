package com.qkzz.online.dao.impl;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.ByGameDao;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ByGameDaoImpl
  implements ByGameDao
{

  @Resource
  private JdbcTemplate onlineTemplate;

  public boolean add(Queue obj)
  {
    String sql = "insert into game_bygameid_online" + (obj.getGameid() & 0xFF) + "(uid,name,lasttime,lasturl,lastgame,status) values(?,?,?,?,?,?)";
    return this.onlineTemplate.update(sql, new Object[] { 
      Integer.valueOf(obj.getUid()), 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      Integer.valueOf(obj.getStatus()) }) > 0;
  }

  public boolean isExist(Queue obj)
  {
    String sql = "select count(*) from game_bygameid_online" + (obj.getGameid() & 0xFF) + " where uid=?";
    return this.onlineTemplate.queryForInt(sql, new Object[] { Integer.valueOf(obj.getUid()) }) > 0;
  }

  public boolean update(Queue obj) {
    String sql = "update game_bygameid_online" + (obj.getGameid() & 0xFF) + " set name=?,lasttime=?,lasturl=?,lastgame=?,status=? where uid=?";
    return this.onlineTemplate.update(sql, new Object[] { 
      obj.getName(), 
      Long.valueOf(System.currentTimeMillis()), 
      obj.getLasturl(), 
      Integer.valueOf(obj.getGameid()), 
      Integer.valueOf(obj.getStatus()), 
      Integer.valueOf(obj.getUid()) }) > 0;
  }
}