package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.common.WordsFilter;
import com.qkzz.global.DBConn;
import com.qkzz.user.dao.WordsFilterDao;


public class WordsFilterDaoImpl implements WordsFilterDao {

	public static String dbuser = Constant.dbuser;
	
	public String[][] queryFilterWords(int type) {
		String[][] ret = null;
		int stat = getTypeNum(type);

		DBConn conn = new DBConn();
		try {
			if (stat > 0) {
				stat = stat > WordsFilter.maxNum ? WordsFilter.maxNum : stat;

				String sql = new StringBuffer("select word,replaceword,connectionuid,attri from word_filter where sort=").append(type).append(" order by updatetime desc limit ").append(stat).toString();
				PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
				ResultSet rs = ps.executeQuery();
				ret = new String[stat][4];
				int index = 0;
				while (rs.next()) {
					ret[index][0] = rs.getString("word") != null ? WordsFilter.tranCompile(WordsFilter.SBC2DBCcase(rs.getString("word"))) : "";
					ret[index][1] = rs.getString("replaceword") != null ? rs.getString("replaceword") : "";
					ret[index][2] = rs.getString("connectionuid") != null ? rs.getString("connectionuid") : "";
					ret[index][3] = rs.getString("attri") != null ? rs.getString("attri") : "";
					index++;
				}
				rs.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	public int getTypeNum(int type) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from word_filter where sort=").append(type).toString();
			PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
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
