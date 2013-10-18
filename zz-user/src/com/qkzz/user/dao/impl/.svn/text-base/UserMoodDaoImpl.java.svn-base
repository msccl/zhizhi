package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.UserMood;
import com.qkzz.user.dao.UserMoodDao;

public class UserMoodDaoImpl implements UserMoodDao {
	public static String dbuser = Constant.dbuser;
	
	public int add(long uid, String mood){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into user_mood").append(uid & 0x0f).append("(uid,createtime,mood) values(?,now(),?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, uid);
	    	ps.setString(2, mood);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int delete(long uid, long id){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from user_mood").append(uid &0x0f).append(" where uid=? and id=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, uid);
	    	ps.setLong(2, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int countByList(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from user_mood").append(uid &0x0f).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	ret = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}
	
	public List<UserMood> getByList(long uid, long startIndex,long count) {
		List<UserMood> ret = new ArrayList<UserMood>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from user_mood").append(uid &0x0f).append(" where uid=? order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
			ps.setLong(1, uid);
			ps.setLong(2, startIndex);
			ps.setLong(3, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserMood obj = new UserMood();
				obj.setId(rs.getLong("id"));
				obj.setUid(rs.getInt("uid"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setMood(rs.getString("mood"));
	        	ret.add(obj);
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

}
