package com.qkzz.friend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.friend.bean.FriendGroup;
import com.qkzz.friend.bean.SocialFriendListQueue;
import com.qkzz.friend.dao.SocialFriendListQueueDao;
import com.qkzz.global.DBConn;

public class SocialFriendListQueueDaoImpl implements SocialFriendListQueueDao {
	
	public static String dbfriend = Constant.dbfriend;

	
	@Override
	public int add(SocialFriendListQueue obj) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into social_friend_list_queue(uid, suid, gameid,domain,frdlist,attime,status) values(?,?,?,?,?,now(),0)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setLong(1, obj.getUid());
	    	ps.setString(2, obj.getSuid());
	    	ps.setInt(3, obj.getGameid());
	    	ps.setString(4, obj.getDomain());
	    	ps.setString(5, obj.getFrdlist());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public int chgStatus(long id, int status) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update social_friend_list_queue set status=? where id=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setInt(1, status);
	    	ps.setLong(2, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public List<SocialFriendListQueue> getList(int number) {
		List<SocialFriendListQueue> ret = new ArrayList<SocialFriendListQueue>();
		DBConn conn = new DBConn();
		try {
			String sql = "select * from social_friend_list_queue where status=0 order by id limit ?";
			PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
			ps.setInt(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SocialFriendListQueue bean = new SocialFriendListQueue();
				bean.setId(rs.getLong("id"));
				bean.setUid(rs.getLong("uid"));
				bean.setSuid(rs.getString("suid"));
				bean.setDomain(rs.getString("domain"));
				bean.setGameid(rs.getInt("gameid"));
				bean.setFrdlist(rs.getString("frdlist"));
				bean.setStatus(rs.getInt("status"));
				bean.setAttime(rs.getString("attime"));
				ret.add(bean);
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
