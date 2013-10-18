package com.qkzz.online.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.OnlineDao;



@Service
public class OnlineDaoImpl implements OnlineDao {
	
	@Resource
	private JdbcTemplate onlineTemplate;
	
	public boolean addAll(Queue obj) {
        String sql = "insert into online(uid,name,lasttime,lasturl,lastgame) values(?,?,?,?,?)";
		return onlineTemplate.update(sql, new Object[]{
			obj.getUid(),
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid()
		})>0?true:false;
	}

	public boolean isExistAll(int uid){
		String sql = "select count(*) from online where uid=?";
		return onlineTemplate.queryForInt(sql, new Object[]{uid})>0?true:false;
	}
	
	public boolean updateAll(Queue obj) {
        String sql = "update online set name=?,lasttime=?,lasturl=?,lastgame=? where uid=?";
		return onlineTemplate.update(sql, new Object[]{
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getUid()
		})>0?true:false;
	}

	public boolean add(Queue obj) {
        String sql = "insert into online"+(obj.getUid() & 0xff)+"(uid,name,lasttime,lasturl,lastgame,sign) values(?,?,?,?,?,?)";
		return onlineTemplate.update(sql, new Object[]{
			obj.getUid(),
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getSign()
		})>0?true:false;
	}

	public boolean isExist(int uid){
		String sql = "select count(*) from online"+(uid & 0xff)+" where uid=?";
		return onlineTemplate.queryForInt(sql, new Object[]{uid})>0?true:false;
	}
	
	public boolean update(Queue obj) {
        String sql = "update online"+(obj.getUid() & 0xff)+" set name=?,lasttime=?,lasturl=?,lastgame=?,sign=? where uid=?";
		return onlineTemplate.update(sql, new Object[]{
			obj.getName(),
			System.currentTimeMillis(),
			obj.getLasturl(),
			obj.getGameid(),
			obj.getSign(),
			obj.getUid()
		})>0?true:false;
	}
	
}
