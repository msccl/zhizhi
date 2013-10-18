package com.qkzz.online.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.online.bean.Online;
import com.qkzz.online.dao.OnlineDao;
import com.qkzz.user.bean.User;

public class OnlineDaoImpl implements OnlineDao {
	public static String dbonline = Constant.dbonline;

	/**
	 * 判断用户是否全局在线，如果是返回在线信息
	 * 不包含游客
	 * @param uid
	 * @return
	 */
	public boolean isGlobalOnlne(long uid) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from online").append(uid&0xff).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();			
			if (rs.next()) {
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
	
	
	/**
	 * 判断用户是否全局在线，需要判断用户签名
	 * @param uid
	 * @param sign
	 * @return
	 */
	public boolean isGlobalOnlne(long uid,String sign){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select sign from online").append(uid&0xff).append(" where uid=?").toString();
			System.out.println(sql);
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();			
			if (rs.next()) {
				System.out.println("=========="+rs.getString("sign")+"=========sign:"+sign);
				if(rs.getString("sign").equals(sign)) {
					System.out.println("====online");
					return true;
				} 
				System.out.println("====not online");
				return false;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		System.out.println("====not online end");
		return false;
	}

	
	
	/**
	 * 获取随机玩家列表
	 * @param count
	 * @return
	 */
	public List<Online> getRandUserList(int count){
		List<Online> ret = new ArrayList<Online>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbonline,"select * from online order by rand() limit ?");
			ps.setInt(1, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	Online online = new Online();
	        	online.setUid(rs.getInt("uid"));
	        	online.setName(rs.getString("name"));
	        	online.setLasttime(rs.getLong("lasttime"));
	        	online.setLasturl(rs.getString("lasturl"));
	        	online.setLastgame(rs.getInt("lastgame"));
	        	ret.add(online);
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
	
	public User getByUser(long uid) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from online").append(uid&0xff).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();			
			if (rs.next()) {
				User obj = new User();
				obj.setUid(rs.getLong("uid"));
				obj.setName(rs.getString("name"));
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
	
	
	/**
	 * 主动清除在线记录
	 * @param uid
	 * @return
	 */
	public int clearOnline(long uid) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("delete from online").append(uid&0xff).append(" where uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbonline,sql);
	    	ps.setLong(1, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
