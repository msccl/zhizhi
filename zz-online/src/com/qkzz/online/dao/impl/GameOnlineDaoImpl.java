package com.qkzz.online.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.online.bean.GameOnline;
import com.qkzz.online.dao.GameOnlineDao;

public class GameOnlineDaoImpl implements GameOnlineDao {
	public static String dbonline = Constant.dbonline;

	/**
	 * 获取用户打开的游戏ID列表
	 * @param uid
	 * @return
	 */
	public List<Integer> getUserOpenningGameIDList(long uid, int startIndex, int count) {
		List<Integer> ret = new ArrayList<Integer>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select lastgame from game_online").append(uid&0xff).append(" where uid=? order by lasttime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setLong(1, uid);
			ps.setInt(2, startIndex);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	ret.add(rs.getInt("lastgame"));
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

	/**
	 * 获取用户打开的游戏ID总量
	 * @param uid
	 * @return
	 */
	public int getUserOpenningGameIDMaxCount(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from game_online").append(uid&0xff).append(" where uid=? and status=1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
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

	/**
	 * 获取游戏的在线列表
	 * @param gameid
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public List<GameOnline> getGameUserList(int gameid, int startIndex,int count) {
		List<GameOnline> ret = new ArrayList<GameOnline>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from game_bygameid_online").append(gameid&0xff).append(" where lastgame=? order by lasttime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, startIndex);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GameOnline online = new GameOnline();
	        	online.setUid(rs.getInt("uid"));
	        	online.setName(rs.getString("name"));
	        	online.setLasttime(rs.getLong("lasttime"));
	        	online.setLasturl(rs.getString("lasturl"));
	        	online.setLastgame(rs.getInt("lastgame"));
	        	ret.add(online);
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

	/**
	 * 获取游戏在线用户总量
	 * @param gameid
	 * @return
	 */
	public int getGameUserMaxCount(int gameid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from game_bygameid_online").append(gameid&0xff).append(" where lastgame=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setInt(1, gameid);
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

	/**
	 * 判断用户在某个游戏中是否在线
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public boolean isGameOnline(long uid, int gameid) {
		boolean ret = false;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from game_online").append(gameid&0xff).append(" where uid=? and lastgame=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setLong(1,uid);
			ps.setInt(2, gameid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	ret = true;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	/**
	 * 获取用户正在玩的游戏id
	 * @param uid
	 * @return
	 */
	public int getPlayingGameID(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select lastgame from game_online").append(uid&0xff).append(" where uid=? and status=0").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setLong(1,uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	ret = rs.getInt("lastgame");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	
	/**
	 * 主动清除游戏在线记录
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public int clearGameOnline(long uid) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("delete from game_online").append(uid&0xff).append(" where uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
	    	ps.setLong(1, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	/**
	 * 主动清除按照游戏ID区分的在线记录
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public int clearGameByGameIDOnline(long uid,int gameid) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("delete from game_bygameid_online").append(gameid&0xff).append(" where lastgame=? and uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
	    	ps.setInt(1, gameid);
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
