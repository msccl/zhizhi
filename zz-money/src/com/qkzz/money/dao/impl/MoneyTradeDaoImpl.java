package com.qkzz.money.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.money.bean.MoneyTradeBean;
import com.qkzz.money.dao.MoneyTradeDao;

public class MoneyTradeDaoImpl implements MoneyTradeDao {

	public static String dbmoney = Constant.dbmoney;

	public int add(MoneyTradeBean moneytrade) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into money_trade(role,transtype,transcontent,description,status,updatetime) values(?,?,?,?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, moneytrade.getRole());
	    	ps.setInt(2, moneytrade.getTranstype());
	    	ps.setString(3, moneytrade.getTranscontent());
	    	ps.setString(4, moneytrade.getDescription());
	    	ps.setInt(5, moneytrade.getStatus());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int del(int id) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "delete from money_trade where id=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public List<MoneyTradeBean> getLockTradeIdList() {
		List<MoneyTradeBean> ret = new ArrayList<MoneyTradeBean>();
		DBConn conn = new DBConn();
		try {
			String sql = "select * from money_trade where status=-1 order by id desc";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	MoneyTradeBean trade = new MoneyTradeBean();
	        	trade.setId(rs.getInt("id"));
	        	trade.setRole(rs.getInt("role"));
	        	trade.setTranstype(rs.getInt("transtype"));
	        	trade.setTranscontent(rs.getString("transcontent"));
	        	trade.setDescription(rs.getString("description"));
	        	trade.setStatus(rs.getInt("status"));
	        	trade.setUpdatetime(rs.getString("updatetime"));
				ret.add(trade);
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

	public MoneyTradeBean getTrade(int id) {
	    MoneyTradeBean trade = null;
		DBConn conn = new DBConn();
		try {
			String sql = "select * from money_trade where id=?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	trade = new MoneyTradeBean();
	        	trade.setId(rs.getInt("id"));
	        	trade.setRole(rs.getInt("role"));
	        	trade.setTranstype(rs.getInt("transtype"));
	        	trade.setTranscontent(rs.getString("transcontent"));
	        	trade.setDescription(rs.getString("description"));
	        	trade.setStatus(rs.getInt("status"));
	        	trade.setUpdatetime(rs.getString("updatetime"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return trade;
	}


	public List<MoneyTradeBean> getTradeList() {
		List<MoneyTradeBean> ret = new ArrayList<MoneyTradeBean>();
		DBConn conn = new DBConn();
		try {
			String sql = "select * from money_trade order by id desc";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	MoneyTradeBean trade = new MoneyTradeBean();
	        	trade.setId(rs.getInt("id"));
	        	trade.setRole(rs.getInt("role"));
	        	trade.setTranstype(rs.getInt("transtype"));
	        	trade.setTranscontent(rs.getString("transcontent"));
	        	trade.setDescription(rs.getString("description"));
	        	trade.setStatus(rs.getInt("status"));
	        	trade.setUpdatetime(rs.getString("updatetime"));
				ret.add(trade);
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

	public int update(MoneyTradeBean moneytrade) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update money_trade set role=?,transtype=?,transcontent=?,description=?,status=?,updatetime=now() where id=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, moneytrade.getRole());
	    	ps.setInt(2, moneytrade.getTranstype());
	    	ps.setString(3, moneytrade.getTranscontent());
	    	ps.setString(4, moneytrade.getDescription());
	    	ps.setInt(5, moneytrade.getStatus());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
