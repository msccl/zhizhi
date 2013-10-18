package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.Member;
import com.qkzz.user.bean.PlayLog;
import com.qkzz.user.dao.PlayLogDao;

public class PlayLogDaoImpl implements PlayLogDao {
	
	public static String dbuser = Constant.dbuser;

	/**
	 * 添加记录
	 * @param log
	 * @return
	 */
	public int add(PlayLog log) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into playlog").append(log.getUid()&0xff).append("(uid,gameid,url,attime) values(?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, log.getUid());
	    	ps.setInt(2, log.getGameid());
	    	ps.setString(3, log.getUrl());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	/**
	 * 获取最近的记录
	 * @param uid
	 * @return
	 */
	public PlayLog getLog(long uid) {
		PlayLog log = null;
		DBConn conn = new DBConn();
		try {
	    	String sql = new StringBuffer("select * from playlog").append(uid&0xff).append(" where uid=? order by id desc limit 1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log = new PlayLog();
				log.setId(rs.getLong("id"));
				log.setUid(uid);
				log.setGameid(rs.getInt("gameid"));
				log.setUrl(rs.getString("url"));
				log.setAttime(rs.getString("attime"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return log;
	}

}
