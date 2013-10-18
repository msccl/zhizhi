package com.qkzz.online.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.online.bean.OnlineQueue;
import com.qkzz.online.dao.OnlineQueueDao;

public class OnlineQueueDaoImpl implements OnlineQueueDao {

	public static String dbqueue = Constant.dbqueue;
	

	public int addToQueue(OnlineQueue bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into online_queue(uid,name,lasttime,lasturl,gameid,status,sign) values(?,?,?,?,?,?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbqueue,sql);
	    	ps.setLong(1, bean.getUid());
	    	ps.setString(2, bean.getName());
	    	ps.setLong(3, bean.getLasttime());
	    	ps.setString(4, bean.getLasturl());
	    	ps.setInt(5, bean.getGameid());
	    	ps.setInt(6, bean.getStatus());
	    	ps.setString(7, bean.getSign());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
