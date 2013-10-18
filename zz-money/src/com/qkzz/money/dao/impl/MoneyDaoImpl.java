package com.qkzz.money.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.money.dao.MoneyDao;


public class MoneyDaoImpl implements MoneyDao {

	
	public static String dbmoney = Constant.dbmoney;

	public int addMoneyAccount(long uid, int moneyInfoId, double initMoney) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into money").append(uid&0x0f).append("(uid,moneyinfoid,money,createtime) values(?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setLong(1, uid);
	    	ps.setInt(2, moneyInfoId);
	    	ps.setDouble(3, initMoney);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int changeStatus(long uid, int moneyInfoId, int status) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update money").append(uid&0x0f).append(" set status=? where uid=? and moneyinfoid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setInt(1, status);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, moneyInfoId);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int decMoney(long uid, int moneyInfoId, double money) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update money").append(uid&0x0f).append(" set money=money-?,lasttime=now() where uid=? and moneyinfoid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setDouble(1, money);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, moneyInfoId);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public double getMoney(long uid, int moneyInfoId) {
		double ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select money from money").append(uid&0x0f).append(" where uid=? and moneyinfoid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getDouble("money");
			} else {
				this.addMoneyAccount(uid, moneyInfoId, 0);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	public String getMoneyPayPassword(long uid, int moneyInfoId) {
		String ret = "";
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select paypassword from money").append(uid&0x0f).append(" where uid=? and moneyinfoid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getString("paypassword");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	public int incMoney(long uid, int moneyInfoId, double money) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update money").append(uid&0x0f).append(" set money=money+?,lasttime=now() where uid=? and moneyinfoid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setDouble(1, money);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, moneyInfoId);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public boolean isMoneyBlack(long uid, int moneyInfoId) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select status from money").append(uid&0x0f).append(" where uid=? and moneyinfoid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("status") == 1;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return false;
	}

	public boolean isValidatePassword(long uid, int moneyInfoId, String password) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select paypassword from money").append(uid&0x0f).append(" where uid=? and moneyinfoid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("paypassword").equals(password);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return false;
	}

	public int modifyMoney(long uid, int moneyInfoId, double money) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update money").append(uid&0x0f).append(" set money=?,lasttime=now() where uid=? and moneyinfoid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setDouble(1, money);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, moneyInfoId);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int modifyPayPassword(long uid, int moneyInfoId, String newpassword) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update money").append(uid&0x0f).append(" set paypassword=? where uid=? and moneyinfoid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setString(1, newpassword);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, moneyInfoId);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
