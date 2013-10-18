package com.qkzz.money.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.money.dao.PointDao;

public class PointDaoImpl implements PointDao {

	public static String dbmoney = Constant.dbmoney;

	public int addPointAccount(long uid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into point(uid,point) values(?,0)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setLong(1, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int decPoint(long uid, int point) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update point set point=point-? where uid=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, point);
	    	ps.setLong(2, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int getPoint(long uid) {
		int point = 0;
		DBConn conn = new DBConn();
		try {
			String sql = "select point from point where uid=?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				point = rs.getInt("point");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return point;
	}

	public int incPoint(long uid, int point) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update point set point=point+? where uid=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, point);
	    	ps.setLong(2, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int modifyPoint(long uid, int point) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update point set point=? where uid=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, point);
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
