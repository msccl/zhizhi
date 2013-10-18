package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.Icon;
import com.qkzz.user.dao.IconDao;

public class IconDaoImpl implements IconDao {
	
	public static String dbuser = Constant.dbuser;

	public Icon getById(int id){
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from icon where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Icon obj = new Icon();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setUrl(rs.getString("url"));
				obj.setRank(rs.getInt("rank"));
				return obj;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}
	
	public List<Icon> getByList(int first, int max) {
		List<Icon> ret = new ArrayList<Icon>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from icon order by rank desc limit ?,?");
			ps.setInt(1, first);
			ps.setInt(2, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Icon obj = new Icon();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setUrl(rs.getString("url"));
				obj.setRank(rs.getInt("rank"));
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

	
}
