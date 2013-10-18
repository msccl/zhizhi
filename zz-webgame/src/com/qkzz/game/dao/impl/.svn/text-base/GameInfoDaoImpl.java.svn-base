package com.qkzz.game.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.dao.GameInfoDao;
import com.qkzz.global.DBConn;

public class GameInfoDaoImpl implements GameInfoDao {

	public static String dbgame = Constant.dbgame;

	public GameInfo getGame(int id) {
		DBConn conn = new DBConn();
		try {
			String sql = "select * from t_game_info where id=?";
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	GameInfo bean = new GameInfo();
	        	bean.setId(rs.getInt("id"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setStrategy(rs.getString("strategy"));
	        	bean.setLogo(rs.getString("logo"));
	        	bean.setDeveloperid(rs.getInt("developerid"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setUrl(rs.getString("url"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setWidth(rs.getInt("width"));
	        	bean.setHeight(rs.getInt("height"));
	        	bean.setUiurl(rs.getString("uiurl"));
	        	bean.setShowlink(rs.getInt("showlink"));
	        	bean.setGamecode(rs.getString("gamecode"));
	        	return bean;
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
	 * 通过游戏编号获取对应的数字ID
	 * @param gamecode
	 * @return
	 */
	public int getIDByGameCode(String gamecode) {
		DBConn conn = new DBConn();
		try {
			String sql = "select id from t_game_info where gamecode=?";
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setString(1, gamecode);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	return rs.getInt("id");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return 0;
	}

}
