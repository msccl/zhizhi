package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.ReportList;
import com.qkzz.user.dao.ReportListDao;

public class ReportListDaoImpl implements ReportListDao {
	public static String dbuser = Constant.dbuser;

	public int addReport(ReportList bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into reportlist(reportuid,uid,ip,reason,attime) values(?,?,?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setLong(1, bean.getReportuid());
	    	ps.setLong(2, bean.getUid());
	    	ps.setString(3, bean.getIp());
	    	ps.setString(4, bean.getReason());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public boolean canAddReport(long reportuid, long uid, String ip) {
		boolean ret = true;
		DBConn conn = new DBConn();
		try {
			String sql = "select * from reportlist where reportuid=? and uid=? and ip=? and attime>date_sub(now(),interval 10 minute) limit 1";
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
			ps.setLong(1, reportuid);
			ps.setLong(2, uid);
			ps.setString(3, ip);
			ResultSet rs = ps.executeQuery();
			System.out.println("reportuid:"+reportuid+"====uid:"+uid+"=====ip:"+ip);
			if (rs.next()) {
				ret = false;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		System.out.println("22222222222222");
		return ret;
	}

	public int getReportNum(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = "select count(*) from reportlist where uid=?";
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
			ps.setLong(1, uid);
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
