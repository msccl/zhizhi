package com.qkzz.money.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.money.bean.PointLogBean;
import com.qkzz.money.dao.PointLogDao;

public class PointLogDaoImpl implements PointLogDao {

	public static String dbmoney = Constant.dbmoney;

	public int add(PointLogBean log) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into pointlog(uid,amount,remark,transtype,attime) values(?,?,?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
	    	ps.setLong(1, log.getUid());
	    	ps.setInt(2, log.getAmount());
	    	ps.setString(3, log.getRemark());
	    	ps.setInt(4, log.getTranstype());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;

	}

	public List<PointLogBean> getPointLogList(long uid, int start, int size) {
		List<PointLogBean> ret = new ArrayList<PointLogBean>();
		DBConn conn = new DBConn();
		try {
			String sql = "select * from pointlog where uid=? order by id desc limit ?,?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ps.setInt(2, start);
			ps.setInt(3, size);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	PointLogBean log = new PointLogBean();
	        	log.setId(rs.getInt("id"));
	        	log.setUid(rs.getInt("uid"));
	        	log.setAmount(rs.getInt("amount"));
	        	log.setTranstype(rs.getInt("transtype"));
	        	log.setRemark(rs.getString("remark"));
	        	log.setAttime(rs.getString("attime"));
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

	public int logMaxCount(long uid) {
		int count = 0;
		DBConn conn = new DBConn();
		try {
			String sql = "select count(*) from pointlog where uid=?";
			PreparedStatement ps = conn.getPreparedStmt(dbmoney,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return count;
	}

}
