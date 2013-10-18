package com.qkzz.game.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameAuctionTools;
import com.qkzz.game.dao.GameAuctionToolsDao;
import com.qkzz.global.DBConn;

public class GameAuctionToolsDaoImpl implements GameAuctionToolsDao {

	public static String dbgame = Constant.dbgame;

	public int countByList(int gameid, int auctionid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_game_auction_tools_list").append(gameid & 0xf).append(" where gameid=? and auctionid=? and status=0").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, auctionid);
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

	public List<GameAuctionTools> getByList(int gameid, int auctionid, int first, int max) {
		List<GameAuctionTools> ret = new ArrayList<GameAuctionTools>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_game_auction_tools_list").append(gameid & 0xf).append(" where gameid=? and auctionid=? and status=0 order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, auctionid);
			ps.setInt(3, first);
			ps.setInt(4, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GameAuctionTools obj = new GameAuctionTools();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setAuctionid(rs.getInt("auctionid"));
	        	obj.setUid(rs.getInt("uid"));
	        	obj.setToolsid(rs.getInt("toolsid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setMoneyid(rs.getInt("moneyid"));
	        	obj.setPrice(rs.getDouble("price"));
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

	public GameAuctionTools getById(int gameid, int auctionid, int id) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_game_auction_tools_list").append(gameid & 0xf).append(" where gameid=? and auctionid=? and id=?").toString();
			System.out.println(sql);
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setInt(2, auctionid);
	    	ps.setInt(3, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				GameAuctionTools obj = new GameAuctionTools();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setAuctionid(rs.getInt("auctionid"));
	        	obj.setUid(rs.getInt("uid"));
	        	obj.setToolsid(rs.getInt("toolsid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setMoneyid(rs.getInt("moneyid"));
	        	obj.setPrice(rs.getDouble("price"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setCreatetime(rs.getString("createtime"));
				return obj;//
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

	public int decToolsNum(int gameid, int auctionid, int id,	int num) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("update t_game_auction_tools_list").append(gameid & 0xf).append(" set num=num-? where gameid=? and auctionid=? and id=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, num);
	    	ps.setInt(2, gameid);
	    	ps.setInt(3, auctionid);
	    	ps.setInt(4, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int removeToolsNumZero(int gameid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from t_game_auction_tools_list").append(gameid & 0xf).append(" where num=0").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int add(GameAuctionTools obj) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into t_game_auction_tools_list").append(obj.getGameid() & 0xf).append("(gameid,auctionid,uid,toolsid,num,moneyid,price,status,createtime) values(?,?,?,?,?,?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, obj.getGameid());
	    	ps.setInt(2, obj.getAuctionid());
	    	ps.setInt(3, obj.getUid());
	    	ps.setInt(4, obj.getToolsid());
	    	ps.setInt(5, obj.getNum());
	    	ps.setInt(6, obj.getMoneyid());
	    	ps.setDouble(7, obj.getPrice());
	    	ps.setInt(8, obj.getStatus());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
