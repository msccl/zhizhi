package com.qkzz.money.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.money.bean.UserMoneyLog;
import com.qkzz.money.dao.MoneyLogDao;


public class MoneyLogDaoImpl implements MoneyLogDao {

	public static String dbmoney = Constant.dbmoney;

	public int add(UserMoneyLog log) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into user_moneylog").append(log.getUid()&0x0f).append("(uid,moneyinfoid,money,tradeid,remark,createtime) values(?,?,?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setLong(1, log.getUid());
	    	ps.setInt(2, log.getMoneyinfoid());
	    	ps.setDouble(3, log.getMoney());
	    	ps.setInt(4, log.getTradeid());
	    	ps.setString(5, log.getRemark());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public List<UserMoneyLog> getMoneyLogList(long uid, int moneyInfoId, int start, int size) {
		List<UserMoneyLog> ret = new ArrayList<UserMoneyLog>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from user_moneylog").append(uid&0x0f).append(" where uid=? and moneyinfoid=? order by id desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
			ps.setInt(3, start);
			ps.setInt(4, size);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	UserMoneyLog log = new UserMoneyLog();
	        	log.setId(rs.getInt("id"));
	        	log.setUid(rs.getInt("uid"));
	        	log.setMoneyinfoid(rs.getInt("moneyinfoid"));
	        	log.setMoney(rs.getDouble("money"));
	        	log.setTradeid(rs.getInt("tradeid"));
	        	log.setRemark(rs.getString("remark"));
	        	log.setCreatetime(rs.getString("createtime"));
				ret.add(log);
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

	public int logMaxCount(long uid, int moneyInfoId) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from user_moneylog").append(uid&0x0f).append(" where uid=? and moneyinfoid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
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
	 * 充值记录列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserMoneyLog> getAddMoneyLogList(long uid, int moneyInfoId, int start, int size) {
		List<UserMoneyLog> ret = new ArrayList<UserMoneyLog>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from user_moneylog").append(uid&0x0f).append(" where uid=? and moneyinfoid=? and tradeid in (5,6,7,8) order by id desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
			ps.setInt(3, start);
			ps.setInt(4, size);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	UserMoneyLog log = new UserMoneyLog();
	        	log.setId(rs.getInt("id"));
	        	log.setUid(rs.getInt("uid"));
	        	log.setMoneyinfoid(rs.getInt("moneyinfoid"));
	        	log.setMoney(rs.getDouble("money"));
	        	log.setTradeid(rs.getInt("tradeid"));
	        	log.setRemark(rs.getString("remark"));
	        	log.setCreatetime(rs.getString("createtime"));
				ret.add(log);
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
	 * 充值记录总量
	 * @param uid
	 * @return
	 */
	public int addMoneyLogMaxCount(long uid, int moneyInfoId) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from user_moneylog").append(uid&0x0f).append(" where uid=? and moneyinfoid=? and tradeid in (5,6,7,8)").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, moneyInfoId);
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

}
