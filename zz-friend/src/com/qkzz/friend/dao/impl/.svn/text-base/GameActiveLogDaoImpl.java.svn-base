package com.qkzz.friend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.friend.bean.GameActiveLog;
import com.qkzz.friend.dao.GameActiveLogDao;
import com.qkzz.global.DBConn;

public class GameActiveLogDaoImpl implements GameActiveLogDao {
	
	public static String dbfriend = Constant.dbfriend;

	@Override
	public int add(GameActiveLog obj) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into game_active_log(uid, suid, sname,gameid,domain,attime) values(?,?,?,?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setLong(1, obj.getUid());
	    	ps.setString(2, obj.getSuid());
	    	ps.setString(3, obj.getSname());
	    	ps.setInt(4, obj.getGameid());
	    	ps.setString(5, obj.getDomain());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public boolean isActiveGame(String suid,String domain,int gameid) {
		DBConn conn = new DBConn();
		try {
			String sql = "select * from game_active_log where suid=? and domain=? and gameid=?";
			PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
			ps.setString(1, suid);
			ps.setString(2, domain);
			ps.setInt(3, gameid);
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

	
	@Override
	public boolean isActiveSocialSite(String suid,String domain) {
		DBConn conn = new DBConn();
		try {
			String sql = "select * from game_active_log where suid=? and domain=?";
			PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
			ps.setString(1, suid);
			ps.setString(2, domain);
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

	
	@Override
	public GameActiveLog getLog(String suid, String domain) {
		DBConn conn = new DBConn();
		try {
			String sql = "select * from game_active_log where suid=? and domain=?";
			PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
			ps.setString(1, suid);
			ps.setString(2, domain);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				GameActiveLog log = new GameActiveLog();
				log.setUid(rs.getLong("uid"));
				log.setSuid(suid);
				log.setSname(rs.getString("sname"));
				log.setGameid(rs.getInt("gameid"));
				log.setDomain(domain);
				log.setAttime(rs.getString("attime"));
				return log;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

	@Override
	public int updateZhiZhiUid(long zhizhiuid, String suid, String domain) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update game_active_log set uid=? where suid=? and domain=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setLong(1, zhizhiuid);
	    	ps.setString(2, suid);
	    	ps.setString(3, domain);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
