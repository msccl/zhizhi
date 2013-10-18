package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.BlackUser;
import com.qkzz.user.dao.BlackUserDao;

public class BlackUserDaoImpl implements BlackUserDao {
	public static String dbuser = Constant.dbuser;

	public int addBlack(BlackUser bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "replace into blackuser(uid,starttime,endtime,type,ip) values(?,?,?,0,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, bean.getUid());
	    	ps.setString(2, bean.getStarttime());
	    	ps.setString(3, bean.getEndtime());
	    	ps.setString(4, bean.getIp());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}


	public int delBlack(int uid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "delete from blackuser where uid=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}


	public List<BlackUser> getList() {
		List<BlackUser> ret = new ArrayList<BlackUser>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from blackuser");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BlackUser bean = new BlackUser();
				bean.setUid(rs.getLong("uid"));
				bean.setType(rs.getInt("type"));
				bean.setStarttime(rs.getString("starttime"));
				bean.setEndtime(rs.getString("endtime"));
				bean.setIp(rs.getString("ip"));
				ret.add(bean);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		
		if(ret.isEmpty()) {
			return null;
		}
		return ret;
	}


	public int clearOutOfDate() {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "delete from blackuser where endtime<now()";
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
