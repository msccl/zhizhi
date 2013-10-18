package com.qkzz.online.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.ByGameDao;



@Service
public class ByGameDaoImpl implements ByGameDao {
	
	@Resource
	private JdbcTemplate onlineTemplate;
	
	public boolean add(Queue obj) {
        String sql = "insert into game_bygameid_online"+(obj.getGameid() & 0xff)+"(uid,name,lasttime,lasturl,lastgame,status) values(?,?,?,?,?,?)";
		return onlineTemplate.update(sql, new Object[]{
			obj.getUid(),
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getStatus()
		})>0?true:false;
	}
	
	public boolean isExist(Queue obj){
		String sql = "select count(*) from game_bygameid_online"+(obj.getGameid() & 0xff)+" where uid=?";
		return onlineTemplate.queryForInt(sql, new Object[]{obj.getUid()})>0?true:false;
	}
	
	public boolean update(Queue obj) {
        String sql = "update game_bygameid_online"+(obj.getGameid() & 0xff)+" set name=?,lasttime=?,lasturl=?,lastgame=?,status=? where uid=?";
		return onlineTemplate.update(sql, new Object[]{
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getStatus(),
			obj.getUid()
		})>0?true:false;
	}
	
}
