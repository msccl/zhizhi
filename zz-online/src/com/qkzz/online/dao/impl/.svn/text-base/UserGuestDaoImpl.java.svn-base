package com.qkzz.online.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.online.bean.UserGuest;
import com.qkzz.online.dao.UserGuestDao;

public class UserGuestDaoImpl implements UserGuestDao {
	public static String dbonline = Constant.dbonline;
	
	public long getById(){
		DBConn conn = new DBConn();
		try {
			String sql = "select id from userguest order by id limit 1";
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	            return rs.getLong("id");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return 0;
	    
	}
	
	public int add(UserGuest obj){
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into userguest(id,status,addtime) values(?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
	    	ps.setLong(1, obj.getId());
	    	ps.setInt(2, obj.getStatus());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int delete(long id){
		DBConn conn = new DBConn();
	    try {
	    	String sql = "delete from userguest where id=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
	    	ps.setLong(1, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	
}
