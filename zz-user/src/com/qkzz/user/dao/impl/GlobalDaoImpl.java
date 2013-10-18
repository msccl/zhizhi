package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.dao.GlobalDao;

public class GlobalDaoImpl implements GlobalDao {
	
	public static String dbuser = Constant.dbuser;

	/**
	 * 获取应用刷新调用时间
	 * @param appid
	 * @param subid
	 * @return
	 */
	public int getAppFreshInterval(int appid, int subid) {
		int ret = Constant.FRESH_INTERVAL_DEFAULT;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select freshinterval from t_fresh_config where appid=? and subid=?");
			ps.setInt(1, appid);
			ps.setInt(2, subid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getInt("freshinterval");
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
