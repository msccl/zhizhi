package com.qkzz.online.dao.impl;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.QueueDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

@Service
public class QueueDaoImpl
  implements QueueDao
{

  @Resource
  private JdbcTemplate userTemplate;

  public int delete(long id)
  {
    String sql = "delete from online_queue where id=?";
    return this.userTemplate.update(sql, new Object[] { 
      Long.valueOf(id) });
  }

  public long countByList()
  {
    String sql = "select count(*) from online_queue";
    return this.userTemplate.queryForLong(sql);
  }

  public List<Queue> getByList(long first, long max) {
    String sql = "select * from online_queue order by lasttime asc limit ?,?";
    ParameterizedRowMapper<Queue> mapper = new ParameterizedRowMapper<Queue>() {
      public Queue mapRow(ResultSet rs, int rowNum) throws SQLException {
        Queue obj = new Queue();
        obj.setId(rs.getLong("id"));
        obj.setUid(rs.getInt("uid"));
        obj.setName(rs.getString("name"));
        obj.setLasturl(rs.getString("lasturl"));
        obj.setGameid(rs.getInt("gameid"));
        obj.setStatus(rs.getInt("status"));
        obj.setSign(rs.getString("sign"));
        return obj;
      }
    };
    return this.userTemplate.query(sql, new Object[] { Long.valueOf(first), Long.valueOf(max) }, mapper);
  }
}