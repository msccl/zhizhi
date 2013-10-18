package com.qkzz.game.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameAuction;
import com.qkzz.game.dao.GameAuctionDao;
import com.qkzz.global.DBConn;

public class GameAuctionDaoImpl implements GameAuctionDao {

	public static String dbgame = Constant.dbgame;

	public int countByList(int gameid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = "select count(*) from t_game_auction where gameid=? and status=1";
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
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

	public List<GameAuction> getByList(int gameid, int first, int max) {
		List<GameAuction> ret = new ArrayList<GameAuction>();
		DBConn conn = new DBConn();
		try {
			String sql = "select * from t_game_auction where gameid=? and status=1 order by createtime desc limit ?,?";
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GameAuction obj = new GameAuction();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setName(rs.getString("name"));
	        	obj.setSwfurl(rs.getString("swfurl"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setCreatetime(rs.getString("createtime"));
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

	public GameAuction getById(int gameid, int id) {
		DBConn conn = new DBConn();
		try {
			String sql = "select * from t_game_auction where gameid=? and id=?";
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				GameAuction obj = new GameAuction();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setName(rs.getString("name"));
	        	obj.setSwfurl(rs.getString("swfurl"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setCreatetime(rs.getString("createtime"));
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

	public int getByStatus(int gameid, int id) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = "select status from t_game_auction where gameid=? and id=?";
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
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
	
}
