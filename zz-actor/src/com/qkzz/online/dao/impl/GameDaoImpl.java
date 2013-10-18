package com.qkzz.online.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.GameDao;



@Service
public class GameDaoImpl implements GameDao {
	
	@Resource
	private JdbcTemplate onlineTemplate;
	
	public boolean addAll(Queue obj) {
        String sql = "insert into game_online(uid,name,lasttime,lasturl,lastgame) values(?,?,?,?,?)";
		return onlineTemplate.update(sql, new Object[]{
			obj.getUid(),
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid()
		})>0?true:false;
	}

	public boolean isExistAll(int uid){
		String sql = "select count(*) from game_online where uid=?";
		return onlineTemplate.queryForInt(sql, new Object[]{uid})>0?true:false;
	}
	
	public boolean updateAll(Queue obj) {
        String sql = "update game_online set name=?,lasttime=?,lasturl=?,lastgame=? where uid=?";
		return onlineTemplate.update(sql, new Object[]{
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getUid()
		})>0?true:false;
	}

	public boolean add(Queue obj) {
        String sql = "insert into game_online"+(obj.getUid() & 0xff)+"(uid,name,lasttime,lasturl,lastgame,status) values(?,?,?,?,?,?)";
		return onlineTemplate.update(sql, new Object[]{
			obj.getUid(),
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getStatus()
		})>0?true:false;
	}

	public boolean isExist(int uid){
		String sql = "select count(*) from game_online"+(uid & 0xff)+" where uid=?";
		return onlineTemplate.queryForInt(sql, new Object[]{uid})>0?true:false;
	}
	
	public boolean update(Queue obj) {
        String sql = "update game_online"+(obj.getUid() & 0xff)+" set name=?,lasttime=?,lasturl=?,lastgame=?,status=? where uid=?";
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
