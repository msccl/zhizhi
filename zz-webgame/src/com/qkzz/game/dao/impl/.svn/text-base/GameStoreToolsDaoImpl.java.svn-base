package com.qkzz.game.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameStoreTools;
import com.qkzz.game.dao.GameStoreToolsDao;
import com.qkzz.global.DBConn;

public class GameStoreToolsDaoImpl implements GameStoreToolsDao {

	public static String dbgame = Constant.dbgame;

	public int countByList(int gameid, int storeid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_game_store_tools_list").append(gameid & 0xf).append(" where gameid=? and storeid=? and status=0").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, storeid);
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

	public List<GameStoreTools> getByList(int gameid, int storeid, int first, int max) {
		List<GameStoreTools> ret = new ArrayList<GameStoreTools>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select t.id,t.gameid,t.storeid,t.toolsid,t.num,t.status,t.createtime,s.name,s.intro,s.img,s.price from t_game_store_tools_list").append(gameid & 0xf).append(" t ,t_game_tools").append(gameid & 0xf).append(" s where t.toolsid=s.id and t.gameid=? and t.storeid=? and t.status=0 order by t.createtime desc limit ?,?").toString();
			System.out.println(sql);
			System.out.println(gameid);
			System.out.println(storeid);
			System.out.println(first);
			System.out.println(max);
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
			ps.setInt(2, storeid);
			ps.setInt(3, first);
			ps.setInt(4, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GameStoreTools obj = new GameStoreTools();
	        	obj.setId(rs.getInt("t.id"));
	        	obj.setGameid(rs.getInt("t.gameid"));
	        	obj.setStoreid(rs.getInt("t.storeid"));
	        	obj.setToolsid(rs.getInt("t.toolsid"));
	        	obj.setNum(rs.getInt("t.num"));
	        	obj.setStatus(rs.getInt("t.status"));
	        	obj.setCreatetime(rs.getString("t.createtime"));
	        	obj.setName(rs.getString("s.name"));
	        	obj.setIntro(rs.getString("s.intro"));
	        	obj.setImg(rs.getString("s.img"));
	        	obj.setPrice(rs.getDouble("s.price"));
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

	public GameStoreTools getById(int gameid, int storeid, int id) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_game_store_tools_list").append(gameid & 0xf).append(" where gameid=? and storeid=? and id=?").toString();
			System.out.println(sql);
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setInt(1, gameid);
	    	ps.setInt(2, storeid);
	    	ps.setInt(3, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				GameStoreTools obj = new GameStoreTools();
	        	obj.setId(rs.getInt("id"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setStoreid(rs.getInt("storeid"));
	        	obj.setToolsid(rs.getInt("toolsid"));
	        	obj.setNum(rs.getInt("num"));
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

	public int decToolsNum(int gameid, int storeid, int id, int num) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("update t_game_store_tools_list").append(gameid & 0xf).append(" set num=num-? where gameid=? and storeid=? and id=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, num);
	    	ps.setInt(2, gameid);
	    	ps.setInt(3, storeid);
	    	ps.setInt(4, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
