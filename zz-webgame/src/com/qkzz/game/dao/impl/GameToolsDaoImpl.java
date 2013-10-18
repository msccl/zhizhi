package com.qkzz.game.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameTools;
import com.qkzz.game.dao.GameToolsDao;
import com.qkzz.global.DBConn;

public class GameToolsDaoImpl implements GameToolsDao {

	public static String dbgame = Constant.dbgame;

	public int countByList(int gameid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_game_tools").append(gameid & 0xf).append(" where gameid=? and status=1").toString();
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

	public List<GameTools> getByList(int gameid, int first, int max) {
		List<GameTools> ret = new ArrayList<GameTools>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_game_tools").append(gameid & 0xf).append(" where gameid=? and status=1 order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	GameTools obj = new GameTools();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setName(rs.getString("name"));
	        	obj.setIntro(rs.getString("intro"));
	        	obj.setImg(rs.getString("img"));
	        	obj.setCanexchange(rs.getInt("canexchange"));
	        	obj.setCanauction(rs.getInt("canauction"));
	        	obj.setCandrop(rs.getInt("candrop"));
	        	obj.setFunctiondefine(rs.getString("functiondefine"));
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

	public GameTools getById(int gameid, int id) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_game_tools").append(gameid & 0xf).append(" where gameid=? and id=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	GameTools obj = new GameTools();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setName(rs.getString("name"));
	        	obj.setIntro(rs.getString("intro"));
	        	obj.setImg(rs.getString("img"));
	        	obj.setCanexchange(rs.getInt("canexchange"));
	        	obj.setCanauction(rs.getInt("canauction"));
	        	obj.setCandrop(rs.getInt("candrop"));
	        	obj.setFunctiondefine(rs.getString("functiondefine"));
	        	obj.setMoneyid(rs.getInt("moneyid"));
	        	obj.setPrice(rs.getDouble("price"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIdingame(rs.getString("idingame"));
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
			String sql = new StringBuffer("select status from t_game_tools").append(gameid & 0xf).append(" where gameid=? and id=?").toString();
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

	public int getByExchange(int gameid, int id) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select canexchange from t_game_tools").append(gameid & 0xf).append(" where gameid=? and id=?").toString();
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

	public int getByAuction(int gameid, int id) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select canauction from t_game_tools").append(gameid & 0xf).append(" where gameid=? and id=?").toString();
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

	@Override
	public boolean isToolsInGameExist(int gameid, String toolidInGame) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from t_game_tools").append(gameid & 0xf).append(" where gameid=? and idingame=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setString(2, toolidInGame);
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
	public GameTools getByUnregisteredToolId(int gameid, String id) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_game_tools").append(gameid & 0xf).append(" where gameid=? and idingame=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	GameTools obj = new GameTools();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setName(rs.getString("name"));
	        	obj.setIntro(rs.getString("intro"));
	        	obj.setImg(rs.getString("img"));
	        	obj.setCanexchange(rs.getInt("canexchange"));
	        	obj.setCanauction(rs.getInt("canauction"));
	        	obj.setCandrop(rs.getInt("candrop"));
	        	obj.setFunctiondefine(rs.getString("functiondefine"));
	        	obj.setMoneyid(rs.getInt("moneyid"));
	        	obj.setPrice(rs.getDouble("price"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIdingame(rs.getString("idingame"));
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

	@Override
	public int addTools(GameTools bean) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("insert into t_game_tools").
			append(bean.getGameid()&0x0f).
			append("(gameid,name,intro,img,canexchange,canauction,candrop,moneyid,price,status,createtime,idingame) values(?,?,?,?,1,1,1,0,0,1,now(),?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, bean.getGameid());
	    	ps.setString(2, bean.getName());
	    	ps.setString(3, bean.getName());
	    	ps.setString(4, bean.getImg());
	    	ps.setString(5, bean.getIdingame());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
