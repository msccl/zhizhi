package com.qkzz.money.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.money.bean.MoneyInfo;
import com.qkzz.money.dao.MoneyInfoDao;

public class MoneyInfoDaoImpl implements MoneyInfoDao {

	public static String dbmoney = Constant.dbmoney;

	public List<MoneyInfo> getGameMoneyInfo(int gameid) {
		List<MoneyInfo> ret = new ArrayList<MoneyInfo>();
		DBConn conn = new DBConn();
		try {
			String sql = "select * from t_game_money_info where gameid=? order by id desc";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setInt(1, gameid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MoneyInfo bean = new MoneyInfo();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setCanexchange(rs.getInt("canexchange"));
				bean.setExchangerate(rs.getDouble("exchangerate"));
				bean.setGameid(rs.getInt("gameid"));
				bean.setStatus(rs.getInt("status"));
				bean.setCreatetime(rs.getString("createtime"));
				bean.setIdingame(rs.getString("idingame"));
				ret.add(bean);
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

	public MoneyInfo getMoneyInfo(int id) {
		MoneyInfo bean = null;
		DBConn conn = new DBConn();
		try {
			String sql = "select * from t_game_money_info where id=?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean = new MoneyInfo();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setCanexchange(rs.getInt("canexchange"));
				bean.setExchangerate(rs.getDouble("exchangerate"));
				bean.setGameid(rs.getInt("gameid"));
				bean.setStatus(rs.getInt("status"));
				bean.setCreatetime(rs.getString("createtime"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return bean;
	}

	public int addMoneyInfo(MoneyInfo bean) {
		DBConn conn = new DBConn();
	    try {
			String sql = "insert into t_game_money_info(name,canexchange,exchangerate,gameid,status,createtime,idingame) values(?,0,1,?,1,now(),?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setString(1, bean.getName());
	    	ps.setInt(2, bean.getGameid());
	    	ps.setString(3, bean.getIdingame());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public boolean isMoneyInGameExist(int gameid, String moneyidInGame) {
		DBConn conn = new DBConn();
		try {
			String sql = "select id from t_game_money_info where gameid=? and idingame=?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setInt(1, gameid);
	    	ps.setString(2, moneyidInGame);
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

	public MoneyInfo getByUnregisteredMoneyId(int gameid, String id) {
		MoneyInfo bean = null;
		DBConn conn = new DBConn();
		try {
			String sql = "select * from t_game_money_info where gameid=? and idingame=?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setInt(1, gameid);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean = new MoneyInfo();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setCanexchange(rs.getInt("canexchange"));
				bean.setExchangerate(rs.getDouble("exchangerate"));
				bean.setGameid(rs.getInt("gameid"));
				bean.setStatus(rs.getInt("status"));
				bean.setCreatetime(rs.getString("createtime"));
				bean.setIdingame(rs.getString("idingame"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return bean;
	}

}
