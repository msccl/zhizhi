package cn.yicha.ad.blance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.global.DBConn;

public class Dao {
	
	private static String dbname = "misc";
	
	public String get(String shortCode){
		DBConn conn = new DBConn();
		try {
			String sql = "select lognurl from mapurl where shortcode=?";
			PreparedStatement ps = conn.getPreparedStmt(dbname,sql);
			ps.setString(1, shortCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("lognurl");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}
	
	public int add(String shortCode,String url) {
		DBConn conn = new DBConn();
	    try {
			String sql = "insert into mapurl(shortcode,lognurl) values(?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbname,sql);
	    	ps.setString(1, shortCode);
	    	ps.setString(2, url);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	
}
