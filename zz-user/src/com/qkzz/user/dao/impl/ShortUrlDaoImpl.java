package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.global.DBConn;
import com.qkzz.user.dao.ShortUrlDao;

public class ShortUrlDaoImpl implements ShortUrlDao {
	private static String dbname = "misc";

	/**
	 * 添加段地址信息
	 * @param shortCode
	 * @param url
	 * @return
	 */
	public int add(String shortCode, String url) {
		DBConn conn = new DBConn();
	    try {
			String sql = "insert into mapurl(shortcode,longurl,attime) values(?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbname,sql);
	    	ps.setString(1, shortCode);
	    	ps.setString(2, url);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	/**
	 * 通过短代码获取真实的url
	 * @param shortCode
	 * @return
	 */
	public String get(String shortCode) {
		DBConn conn = new DBConn();
		try {
			String sql = "select longurl from mapurl where shortcode=?";
			PreparedStatement ps = conn.getPreparedStmt(dbname,sql);
			ps.setString(1, shortCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("longurl");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

	/**
	 * 判断短地址是否存在，如果存在，不再添加
	 * @param shortCode
	 * @return
	 */
	public boolean isExist(String shortCode) {
		DBConn conn = new DBConn();
		try {
			String sql = "select * from mapurl where shortcode=?";
			PreparedStatement ps = conn.getPreparedStmt(dbname,sql);
			ps.setString(1, shortCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return false;
	}

}
