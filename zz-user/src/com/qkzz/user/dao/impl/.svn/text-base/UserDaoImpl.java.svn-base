package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.User;
import com.qkzz.user.dao.UserDao;

public class UserDaoImpl implements UserDao {
	public static String dbuser = Constant.dbuser;
	
	public User getByUid(long uid){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from user").append(uid&0x0f).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	User obj = new User();
	        	obj.setUid(rs.getLong("uid"));
	        	obj.setNickname(rs.getString("nickname"));
	        	obj.setSex(rs.getInt("sex"));
	        	obj.setProvinceid(rs.getInt("provinceid"));
	        	obj.setCityid(rs.getInt("cityid"));
	        	obj.setAreaid(rs.getInt("areaid"));
	        	obj.setBirth(rs.getString("birth"));
	        	obj.setFaceurl(rs.getString("faceurl"));
	        	obj.setType(rs.getInt("type"));
	        	obj.setEmail(rs.getString("email"));
	        	obj.setVisitnum(rs.getInt("visitnum"));
	        	obj.setRegtime(rs.getString("regtime"));
	        	obj.setLasttime(rs.getString("lasttime"));
	        	obj.setLastip(rs.getString("lastip"));
	        	obj.setMood(rs.getString("mood"));
	        	obj.setPhone(rs.getString("phone"));
	            return obj;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	    
	}
	
	public int add(User obj){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into user").append(obj.getUid()&0x0f).append("(uid,regtime,lasttime,lastip,email,mood,nickname) values(?,now(),now(),?,?,'',?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, obj.getUid());
	    	ps.setString(2, obj.getLastip());
	    	ps.setString(3, obj.getEmail());
	    	ps.setString(4, obj.getNickname());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int update(User obj){
		DBConn conn = new DBConn();
	    try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("update user").append(obj.getUid()&0x0f).append(" set ");
	        sql.append("provinceid='").append(obj.getProvinceid()).append("',");
	        sql.append("cityid='").append(obj.getCityid()).append("',");
	        sql.append("areaid='").append(obj.getAreaid()).append("',");
	        sql.append("type='").append(obj.getType()).append("',");
	        sql.append("lasttime=now(),");
	        if (obj.getSex() != -1) {
	        	sql.append("sex='").append(obj.getSex()).append("',");
	        }
	        if (obj.getBirth()!=null && !"".equals(obj.getBirth())) {
	          sql.append("birth='").append(obj.getBirth()).append("',");
	        }
	        if (obj.getLastip()!=null && !"".equals(obj.getLastip())) {
	          sql.append("lastip='").append(obj.getLastip()).append("',");
	        }
	        if (obj.getEmail()!=null && !"".equals(obj.getEmail())) {
	          sql.append("email='").append(obj.getEmail()).append("',");
	        }
	        if (obj.getPhone()!=null && !"".equals(obj.getPhone())) {
		          sql.append("phone='").append(obj.getPhone()).append("',");
		    }
	        if (obj.getNickname()!=null && !"".equals(obj.getNickname())) {
		          sql.append("nickname='").append(obj.getNickname()).append("',");
		    }
	        
	        sql.delete(sql.length() - 1, sql.length());
	        sql.append(" where uid='").append(obj.getUid()).append("'");

	        System.out.println("update Sql:"+sql.toString());
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql.toString());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int updateMood(long uid, String mood){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update user").append(uid &0x0f).append(" set mood=? where uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setString(1, mood);
	    	ps.setLong(2, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int updateFace(long uid, String face){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update user").append(uid &0x0f).append(" set faceurl=? where uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setString(1, face);
	    	ps.setLong(2, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
}
